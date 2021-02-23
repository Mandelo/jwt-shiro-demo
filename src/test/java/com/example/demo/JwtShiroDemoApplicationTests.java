package com.example.demo;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.PermService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JwtShiroDemoApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermService permService;

    @Autowired
    RedisUtil redisUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void testSelectByUsername(){
        UserEntity admin = userService.selectByUsername("admin");
        System.out.println(admin);
    }

    @Test
    void selectRoleList(){
        List<String> roleList = roleService.getRoleListByUsername("admin");
        System.out.println(roleList);
    }

    @Test
    void selectPermList(){
        List<String> permList = permService.getPermListByUsername("admin");
        System.out.println(permList);
    }

    @Test
    void testRedis(){
        redisUtil.set("213","123");
    }

}
