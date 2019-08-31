package com.jianzhong.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.swaggerbootstrapui.util.CommonUtils;
import com.jianzhong.demo.domain.User;
import com.jianzhong.demo.service.UserService;
import com.jianzhong.demo.utils.IdUtil;
import com.jianzhong.demo.utils.RedisUtil;
import com.jianzhong.demo.utils.StringUtil;
import com.jianzhong.demo.vo.ResultVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "用户模块",value = "用户接口")
@RestController
@RequestMapping("/auth")
@SuppressWarnings("unchecked")
@Slf4j
public class AuthController extends CommonController
{
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    StringUtil stringUtil;

    @ApiOperation(value = "登录" ,  notes="用户登录授权")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username",value = "用户名",defaultValue = "",dataType = "int",required = true),
        @ApiImplicitParam(name = "password",value = "密码",defaultValue = "",dataType = "int",required = true)
    })
    @ApiResponses({
        @ApiResponse(code = 200,message = "操作成功"),
        @ApiResponse(code = 201,message = "创建成功"),
        @ApiResponse(code = 401,message = "未经授权"),
        @ApiResponse(code = 403,message = "你没有权限访问该页面"),
        @ApiResponse(code = 404,message = "页面未找到")
    })
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResultVo login(
        @RequestParam(value = "username",defaultValue = "") String username,
        @RequestParam(value = "password",defaultValue = "") String password
    ) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pa = encoder.encode("123456");
        log.info(pa);
        if(username.equals("") || password.equals("")){
            return this.error("账号密码不能为空",1);
        }
        Map tokenRes = this.generateToken(username,password);
        if(tokenRes.get("code").toString().equals("1")){
            return this.error(tokenRes.get("msg").toString(),1);
        }
        String token = tokenRes.get("data").toString();
        Map res = new HashMap();
        res.put("access_token",token);
        log.info("access_token="+token);
        return this.success(res,"success");
    }

    private Map generateToken(String username, String password) {
        Map res = new HashMap();
        try{
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
            // Perform the security
            final Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Reload password post-security so we can generate token
            final User userDetails = userService.loadUserByUsername(username);
            // 持久化的redis
            String token = StringUtil.getRandomString(32);
            redisUtil.set(token, userDetails.getUsername(),0);
            res.put("code",0);
            res.put("msg","success");
            res.put("data",token);
            return res;
        }catch (Exception e){
            res.put("code",1);
            res.put("msg",e.getMessage());
            res.put("data",null);
            log.error(e.getMessage());
            return res;
        }
    }
}