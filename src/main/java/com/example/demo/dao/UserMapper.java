package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/19
 */

@Mapper
@Component
public interface UserMapper extends BaseMapper<UserEntity> {

    UserEntity selectByUsername(String username);

}
