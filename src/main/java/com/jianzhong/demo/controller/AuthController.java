package com.jianzhong.demo.controller;

import com.jianzhong.demo.domain.User;
import com.jianzhong.demo.service.AuthService;
import com.jianzhong.demo.service.UserService;
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
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户模块",value = "用户接口")
@RestController
@RequestMapping("/auth")
@SuppressWarnings("unchecked")
@Slf4j
public class AuthController extends CommonController
{
    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManager authenticationManager;

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
        if(username.equals("") || password.equals("")){
            return this.error("账号密码不能为空",1);
        }
        Map tokenRes = authService.login(username,password);
        if(tokenRes.get("code").toString().equals("1")){
            return this.error(tokenRes.get("msg").toString(),1);
        }
        String token = tokenRes.get("data").toString();
        Map res = new HashMap();
        res.put("access_token",token);
        log.info("用户{"+username+"}登录成功，token："+token);
        return this.success(res,"success");
    }

    @ApiOperation(value = "注销" ,  notes="用户注销")
    @ApiResponses({
            @ApiResponse(code = 200,message = "操作成功"),
            @ApiResponse(code = 201,message = "创建成功"),
            @ApiResponse(code = 401,message = "未经授权"),
            @ApiResponse(code = 403,message = "你没有权限访问该页面"),
            @ApiResponse(code = 404,message = "页面未找到")
    })
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResultVo logout() {
        authService.logout();
        return this.success(null,"success");
    }
}