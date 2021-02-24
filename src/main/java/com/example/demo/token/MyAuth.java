package com.example.demo.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/24
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyAuth {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 角色列表
     */
    private List<String> roleList;

    /**
     * 权限列表
     */
    private List<String> permList;

}
