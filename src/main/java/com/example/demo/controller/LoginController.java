package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.UserEntity;
import com.example.demo.result.ResultMap;
import com.example.demo.service.PermService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.token.MyAuth;
import com.example.demo.util.JWTUtil;
import com.example.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/22
 */

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermService permService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/login")
    public ResultMap login(@RequestParam("username") String username,
                           @RequestParam("password") String password){
        ResultMap resultMap = new ResultMap();
        UserEntity userEntity = userService.selectByUsername(username);
        if(userEntity == null){
            return resultMap.fail().code(401).message("用户不存在");
        }else{
            if(!userEntity.getPassword().equals(password)){
                return resultMap.fail().code(401).message("密码错误");
            }else{
                //用户名密码正确
                List<String> roleList = roleService.getRoleListByUsername(username);
                List<String> permList = permService.getPermListByUsername(username);
                MyAuth myAuth = new MyAuth();
                myAuth.setId(userEntity.getId());
                myAuth.setUsername(username);
                myAuth.setRoleList(roleList);
                myAuth.setPermList(permList);
                String token = JWTUtil.createToken(JSON.toJSONString(myAuth));
                redisUtil.set(username,JSON.toJSONString(myAuth),300);
                return resultMap.success().code(200).message(token);
            }
        }
    }

    @RequestMapping(path = "/unauthorized/{message}")
    public ResultMap unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        ResultMap resultMap = new ResultMap();
        return resultMap.success().code(401).message(message);
    }

}
