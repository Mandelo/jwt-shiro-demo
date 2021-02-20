package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/19
 */

@Mapper
@Component
public interface RoleMapper extends BaseMapper<RoleEntity> {

    List<String> getRoleListByUsername(String username);

}
