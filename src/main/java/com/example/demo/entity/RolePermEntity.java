package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role_perm")
public class RolePermEntity {

    private Integer roleId;
    private Integer permId;

}
