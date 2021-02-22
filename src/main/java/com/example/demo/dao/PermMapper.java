package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.PermissionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/22
 */

@Mapper
@Component
public interface PermMapper extends BaseMapper<PermissionEntity> {

    List<String> getPermListByUsername(String username);

}
