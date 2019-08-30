package com.jianzhong.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jianzhong.demo.security.AuthPasswordEncoder;
import com.jianzhong.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private AuthPasswordEncoder authPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
        .authenticationProvider(authenticationProvider())
        .httpBasic()
        //未登录时，进行json格式的提示，很喜欢这种写法，不用单独写一个又一个的类
        .authenticationEntryPoint((request,response,authException) -> {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("code",403);
            map.put("message","未登录");
            out.write(objectMapper.writeValueAsString(map));
            out.flush();
            out.close();
        })

        .and()
        .authorizeRequests()
        .anyRequest().authenticated() //必须授权才能范围

        .and()
        .formLogin() //使用自带的登录
        .permitAll()
        //登录失败，返回json
        .failureHandler((request,response,ex) -> {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter out = response.getWriter();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("code",401);
            if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
                map.put("message","用户名或密码错误");
            } else if (ex instanceof DisabledException) {
                map.put("message","账户被禁用");
            } else {
                map.put("message","登录失败!");
            }
            out.write(objectMapper.writeValueAsString(map));
            out.flush();
            out.close();
        })
        //登录成功，返回json
        .successHandler((request,response,authentication) -> {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("code",200);
            map.put("message","登录成功");
            map.put("data",authentication);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(objectMapper.writeValueAsString(map));
            out.flush();
            out.close();
        })
        .and()
        .exceptionHandling()
        //没有权限，返回json
        .accessDeniedHandler((request,response,ex) -> {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("code",403);
            map.put("message", "权限不足");
            out.write(objectMapper.writeValueAsString(map));
            out.flush();
            out.close();
        })
        .and()
        .logout()
        //退出成功，返回json
        .logoutSuccessHandler((request,response,authentication) -> {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("code",200);
            map.put("message","退出成功");
            map.put("data",authentication);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(objectMapper.writeValueAsString(map));
            out.flush();
            out.close();
        })
        .permitAll();
        //开启跨域访问
        http.cors().disable();
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(authPasswordEncoder);
        return authenticationProvider;
    }
}