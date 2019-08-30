package com.jianzhong.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jianzhong.demo.constant.ResultConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthAccessDeniedHandler implements AccessDeniedHandler
{
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        Map<String,Object> map=new HashMap<>(2);
        map.put("code", ResultConstant.CODE_NOPERMISSION);
        map.put("msg", e.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }
}