package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.PermMapper;
import com.example.demo.entity.PermissionEntity;
import com.example.demo.service.PermService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/22
 */

@Service
public class PermServiceImpl extends ServiceImpl<PermMapper, PermissionEntity> implements PermService {

    @Override
    public List<String> getPermListByUsername(String username) {
        return baseMapper.getPermListByUsername(username);
    }

}
