package com.example.demo.controller;

import com.example.demo.result.ResultMap;
import com.example.demo.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户相关
 * @author: luox
 * @date： 2021/2/22
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final ResultMap resultMap;

    public UserController (UserService userService,ResultMap resultMap){
        this.userService = userService;
        this.resultMap = resultMap;
    }

    @GetMapping("/getMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user"})
    public ResultMap getMessage() {
        return resultMap.success().code(200).message("成功获得信息！");
    }

}
