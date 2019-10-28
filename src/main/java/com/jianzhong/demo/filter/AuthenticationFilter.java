package com.jianzhong.demo.filter;

import com.jianzhong.demo.constant.AuthConstant;
import com.jianzhong.demo.service.UserService;
import com.jianzhong.demo.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Slf4j
@Component
public class AuthenticationFilter extends OncePerRequestFilter
{

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authToken = request.getHeader(AuthConstant.AUTH_HEADER_FIELD_TOKEN);
        if (authToken != null) {
            if (!authToken.equals("") && redisUtil.exists(AuthConstant.AUTH_REDIS_FIELD_TOKEN+authToken)) {
                String username = redisUtil.get(AuthConstant.AUTH_REDIS_FIELD_TOKEN+authToken,0);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userService.loadUserByUsername(username);
                    //可以校验token和username是否有效，目前由于token对应username存在redis，都以默认都是有效的
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}