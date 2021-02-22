package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("permission")
public class PermissionEntity {

    @TableId
    private Integer id;
    private String permCode;
    private String permName;

}
