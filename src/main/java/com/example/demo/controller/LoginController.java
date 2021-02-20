package com.example.demo.controller;

import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/20
 */

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public String notLogin() {
        return ("您尚未登陆！");
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public String notRole() {
        return ("您没有权限！");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return ("成功注销！");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username,String password){
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //执行登录
        subject.login(token);
        List<String> roleList = roleService.getRoleListByUsername(username);
        if(roleList.contains("super_admin")){
            return "欢迎超级管理员";
        }
        if(roleList.contains("user")){
            return "欢迎普通用户";
        }
        return "权限错误";
    }

}
