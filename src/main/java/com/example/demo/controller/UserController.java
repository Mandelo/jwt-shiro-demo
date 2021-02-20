package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/20
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public String getMessage(){
        return "您拥有用户权限，可以获得该接口的信息！";
    }

}
