package com.jianzhong.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Configuration
@Slf4j
@WebFilter(filterName = "urlFilter", urlPatterns = "/user/*")
public class UrlFilter implements Filter
{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器被创建成功");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        String requestURI = req.getRequestURI();
//        if (!requestURI.contains("index") && !requestURI.contains("detail")) {
//            servletRequest.getRequestDispatcher("/401").forward(servletRequest, servletResponse);
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("过滤器已销毁");
    }
}