package com.jianzhong.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jianzhong.demo.domain.User;
import com.jianzhong.demo.service.UserService;
import com.jianzhong.demo.utils.RedisUtil;
import com.jianzhong.demo.vo.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "用户模块",value = "用户接口",description = "用户接口")
@RestController
@RequestMapping("/user")
@SuppressWarnings("unchecked")
public class UserController extends CommonController{

    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;


    @ApiOperation(value = "用户列表" ,  notes="获取用户列表")
    @ApiImplicitParams({
       @ApiImplicitParam(name = "pageNum",value = "页码",defaultValue = "1",dataType = "int",required = false,paramType = "query"),
       @ApiImplicitParam(name = "pageSize",value = "页寸",defaultValue = "20",dataType = "int",required = false,paramType = "query")
    })
    @ApiResponses({
        @ApiResponse(code = 200,message = "success")
    })
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @ResponseBody
    public Result<List<User>> list(
        @RequestParam(value = "pageNum",defaultValue = "1") String pageNum,
        @RequestParam(value = "pageSize",defaultValue = "20") String pageSize
    ) {
        List<User> data = userService.select();
        PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
        PageInfo<User> pageInfo = new PageInfo<>(data);
        redisUtil.set("spring:userIndex:"+pageNum,pageInfo.toString(),0);
        return this.success(pageInfo,"success");
    }

    @ApiOperation(value = "用户详情" ,  notes="获取用户详情")
    @ApiImplicitParam(name = "uid",value = "用户ID",required = true,paramType = "query")
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    @ResponseBody
    public Result<User> detail(@RequestParam(value = "uid",defaultValue = "0") String uid )
    {
        int intUid = Integer.parseInt(uid);
        if(intUid == 0){
            return this.error("缺少uid参数",404);
        }
        User data = userService.selectByPrimaryKey(intUid);
        if(data == null){
            return this.error("未找到该用户",404);
        }else{
            return this.success(data,"success");
        }
    }

    @ApiOperation(value = "删除用户" ,  notes="删除用户")
    @ApiImplicitParam(name = "uid",value = "用户ID",required = true)
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestParam(value = "uid",defaultValue = "0") String uid )
    {
        int intUid = Integer.parseInt(uid);
        if(intUid == 0){
            return this.error("缺少uid参数",404);
        }
        int data = userService.deleteByPrimaryKey(intUid);
        if(data == 0){
            return this.error("未找到该用户",404);
        }else{
            return this.success(data,"success");
        }
    }

    @ApiOperation(value = "更新用户" ,  notes="新增，修改用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "uid",value = "用户ID",defaultValue = "0",dataType = "int",required = false),
        @ApiImplicitParam(name = "username",value = "用户名",defaultValue = "20",dataType = "int",required = true)
    })
    @Transactional
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestParam(value = "uid",defaultValue = "0") String uid,@RequestParam(value = "username",defaultValue = "") String username )
    {
        int intUid = Integer.parseInt(uid);
        if(intUid == 0){
            //新增
            User insertUser = new User();
            insertUser.setUsername(username);
            int insertUid = userService.insertSelective(insertUser);
            if(insertUid > 0){
                Map res = new HashMap();
                res.put("uid",insertUid);
                return this.success(res,"新增成功");
            }else{
                return this.error("新增失败",1);
            }
        }else{
            //更新
            User user = userService.selectByPrimaryKey(intUid);
            user.setUsername(username);
            int res = userService.updateByPrimaryKeySelective(user);
            if(res > 0){
                return this.success(null,"更新成功");
            }else{
                return this.error("更新失败",1);
            }
        }
    }

}