package com.example.demo.service;

import com.example.demo.entity.UserEntity;

import java.io.Serializable;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/19
 */

public interface UserService {

    UserEntity selectByUsername(String username);

    String getPassword(String username);

    UserEntity getById(Serializable id);
}
