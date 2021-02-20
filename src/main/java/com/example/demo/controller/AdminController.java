package com.example.demo.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/20
 */

@RestController
@RequestMapping("/admin")
@RequiresRoles("super_admin")
public class AdminController {

    @GetMapping("/getMessage")
    public String getMessage(){
        return " 您拥有管理员权限，可以获得该接口的信息！";
    }

}
