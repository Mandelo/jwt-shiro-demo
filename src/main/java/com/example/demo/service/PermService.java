package com.example.demo.service;

import java.util.List;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/22
 */

public interface PermService {

    List<String> getPermListByUsername(String username);

}
