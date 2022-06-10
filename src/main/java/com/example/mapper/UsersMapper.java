package com.example.mapper;

import com.example.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 14431
 */
@Mapper
@Repository("userMapper")
public interface UsersMapper {
    /**
     * 根据name获取用户信息
     * @param name 姓名
     * @return Users
     */
   Users getUsersByName(String name);
}
