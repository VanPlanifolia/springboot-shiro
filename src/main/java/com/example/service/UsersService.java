package com.example.service;

import com.example.pojo.Users;

/**
 * @author 14431
 */
public interface UsersService {
    /**
     * 根据用户name获取用户信息的service
     * @return
     */
    Users getUsersByName(String name);
}
