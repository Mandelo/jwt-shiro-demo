package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/19
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class UserEntity {

    private Integer id;
    private String username;
    private String password;

}
