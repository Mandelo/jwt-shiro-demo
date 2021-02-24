package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/19
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public UserEntity selectByUsername(String username) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUsername,username);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public String getPassword(String username) {
        LambdaQueryWrapper<UserEntity> condition = new LambdaQueryWrapper<>();
        condition.eq(UserEntity::getUsername,username);
        UserEntity userEntity = baseMapper.selectOne(condition);
        return userEntity == null ? null : userEntity.getPassword();
    }

    @Override
    public UserEntity getById(Serializable id) {
        return baseMapper.selectById(id);
    }
}
