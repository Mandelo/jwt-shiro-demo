package com.example.demo.service;

import java.util.List;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/19
 */

public interface RoleService {

    List<String> getRoleListByUsername(String username);

}
