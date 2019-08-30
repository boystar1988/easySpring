package com.jianzhong.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jianzhong.demo.constant.ResultConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthLogoutSuccessHandler implements LogoutSuccessHandler
{
    /**Json转化工具*/
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Map<String,Object> map=new HashMap<>(2);
        map.put("code", ResultConstant.CODE_SUCCESS);
        map.put("msg", "登出成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }
}