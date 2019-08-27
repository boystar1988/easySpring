package com.jianzhong.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jianzhong.demo.domain.User;
import com.jianzhong.demo.service.UserService;
import com.jianzhong.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends CommonController{

    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @ResponseBody
    public Map list(@RequestParam(value = "pageNum",defaultValue = "1") String pageNum,@RequestParam(value = "pageSize",defaultValue = "20") String pageSize)
    {
        List<User> data = userService.select();
        PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
        PageInfo<User> pageInfo = new PageInfo<>(data);
        return this.success(pageInfo,"success");
    }

    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Map detail(@RequestParam(value = "uid",defaultValue = "0") String uid )
    {
        int intUid = Integer.parseInt(uid);
        if(intUid == 0){
            return this.error("缺少uid参数",404);
        }
        Map result = new HashMap();
        User data = userService.selectByPrimaryKey(intUid);
        if(data == null){
            return this.error("未找到该用户",404);
        }else{
            result.put("code",200);
            result.put("msg","success");
            result.put("data",data);
            return result;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Map delete(@RequestParam(value = "uid",defaultValue = "0") String uid )
    {
        int intUid = Integer.parseInt(uid);
        if(intUid == 0){
            return this.error("缺少uid参数",404);
        }
        Map result = new HashMap();
        int data = userService.deleteByPrimaryKey(intUid);
        if(data == 0){
            return this.error("未找到该用户",404);
        }else{
            result.put("code",200);
            result.put("msg","success");
            result.put("data",data);
            return result;
        }
    }

    @Transactional
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Map update(@RequestParam(value = "uid",defaultValue = "0") String uid,@RequestParam(value = "username",defaultValue = "") String username )
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