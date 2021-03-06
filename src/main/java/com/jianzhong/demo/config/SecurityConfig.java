package com.jianzhong.demo.config;

import com.jianzhong.demo.filter.AuthenticationFilter;
import com.jianzhong.demo.security.AuthPasswordEncoder;
import com.jianzhong.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    /**
     * token过滤器.
     */
    @Autowired
    AuthenticationFilter authenticationFilter;
    @Autowired
    UserService userService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers(
                        "/",
                        "/test/**",
                        "/favicon.ico",
                        "/auth/login",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/actuator/**",
                        "/doc.html",
                        "/swagger-ui.html",
                        "/websocket/**"
                )
                .permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        httpSecurity
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new AuthPasswordEncoder();
    }

}