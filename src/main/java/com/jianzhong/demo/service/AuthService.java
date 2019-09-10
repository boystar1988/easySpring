package com.jianzhong.demo.service;

import com.jianzhong.demo.constant.AuthConstant;
import com.jianzhong.demo.domain.User;
import com.jianzhong.demo.repository.UserMapper;
import com.jianzhong.demo.utils.RedisUtil;
import com.jianzhong.demo.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Slf4j
@SuppressWarnings("unchecked")
public class AuthService
{
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    HttpServletRequest request;

    public Map login(String username ,String password){
        Map res = new HashMap();
        try{
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
            // Perform the security
            final Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Reload password post-security so we can generate token
            final User userDetails = userService.loadUserByUsername(username);
            if(userDetails==null){
                throw new Exception("用户不存在");
            }
            String key = AuthConstant.AUTH_REDIS_FIELD_USER_TOKEN+userDetails.getUid();
            Set tokenSet = redisUtil.smembers(key);
            for (Object i : tokenSet){
                log.info("清除用户{"+username+"}的token");
                redisUtil.srem(key,String.valueOf(i));
                redisUtil.del(AuthConstant.AUTH_REDIS_FIELD_TOKEN+String.valueOf(i));
            }
            // 持久化的redis
            String token = StringUtil.getRandomString(128);
            redisUtil.sadd(key,token);
            redisUtil.set(AuthConstant.AUTH_REDIS_FIELD_TOKEN+token, username,0);
            res.put("code",0);
            res.put("msg","success");
            res.put("data",token);
            return res;
        }catch (Exception e){
            res.put("code",1);
            res.put("msg",e.getMessage());
            res.put("data",null);
            log.error(e.toString());
            return res;
        }
    }

    public boolean logout(){
        String key = AuthConstant.AUTH_REDIS_FIELD_USER_TOKEN+this.getUid();
        Set tokenSet = redisUtil.smembers(key);
        for (Object i : tokenSet){
            log.info("清除用户{"+this.getUser().getUsername()+"}的token");
            redisUtil.srem(key,String.valueOf(i));
            redisUtil.del(AuthConstant.AUTH_REDIS_FIELD_TOKEN+String.valueOf(i));
        }
        return true;
    }


    public User getUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public User getUserByToken(String token){
        String username = redisUtil.get(AuthConstant.AUTH_REDIS_FIELD_TOKEN+token,0);
        if(username != null){
            return userService.loadUserByUsername(username);
        }
        return null;
    }

    public long getUid(){
        return this.getUser().getUid();
    }

    public String getAccessToken(HttpServletRequest request){
        return request.getHeader(AuthConstant.AUTH_HEADER_FIELD_TOKEN);
    }

    public String getDevice(){
        return String.valueOf(request.getHeader(AuthConstant.AUTH_HEADER_FIELD_DEVICE));
    }

    public String getDeviceNameByDevice(String device){
        return AuthConstant.deviceMap.get(device);
    }

    public String getVersion(){
        return String.valueOf(request.getHeader(AuthConstant.AUTH_HEADER_FIELD_VERSION));
    }

    public String getUuid(){
        return String.valueOf(request.getHeader(AuthConstant.AUTH_HEADER_FIELD_UUID));
    }

}