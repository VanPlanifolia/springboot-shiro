package com.example.service;

import com.example.mapper.UsersMapper;
import com.example.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 14431
 */
@Service("userService")
public class UsersServiceImpl implements UsersService{
    /**
     * 注入dao实体类
     */
    @Resource(name="userMapper")
    private UsersMapper usersMapper;
    @Override
    public Users getUsersByName(String name) {
        return usersMapper.getUsersByName(name);
    }
}
