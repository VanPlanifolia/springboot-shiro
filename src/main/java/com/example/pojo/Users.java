package com.example.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 14431
 * pojo类Users
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//这个注解用于标注model的名字
@ApiModel("用户实体类")
public class Users {
    //这个注解用于标注pojo类的属性是什么
    @ApiModelProperty("用户的id")
    private int id;
    @ApiModelProperty("用户的名字")
    private String username;
    @ApiModelProperty("用户的密码")
    private String userpass;
    @ApiModelProperty("用户的权限列表")
    private List<Perms> permsList;
}
