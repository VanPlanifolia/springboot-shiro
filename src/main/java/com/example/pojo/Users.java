package com.example.pojo;

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
public class Users {
    private int id;
    private String username;
    private String userpass;
    private List<Perms> permsList;
}
