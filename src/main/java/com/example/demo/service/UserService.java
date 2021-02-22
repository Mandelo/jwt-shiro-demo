package com.example.demo.service;

import com.example.demo.entity.UserEntity;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/19
 */

public interface UserService {

    UserEntity selectByUsername(String username);

    String getPassword(String username);

}
