package com.example.config;

import com.example.pojo.Perms;
import com.example.pojo.Users;
import com.example.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author 14431
 */
public class UserRealm extends AuthorizingRealm {
    @Resource(name="userService")
    UsersService usersService;
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("Shiro授权方法执行");
        //给用户授予权限
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //拿到当前的用户信息对象
        Subject subject = SecurityUtils.getSubject();
        Users user = (Users) subject.getPrincipal();
        //创建一个hashSet来封装用户的权限
        HashSet<String> permsSet=new HashSet<>();
        //便利返回的用户信息并且添加到permSet中
        for (Perms perms : user.getPermsList()) {
            permsSet.add(perms.getPermMsg());
        }
        System.out.println(Arrays.toString(permsSet.toArray()));
        //给当前的用户赋予权限
        info.addStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证,sql动态查询
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("Shiro认证方法执行");
        //拿到controller送过来的token并且强转型
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        //如果token的用户名与我们的不一致则返回null
        Users user = usersService.getUsersByName(token.getUsername());
        if(user==null){
            return null;
        }
        //密码认证，只能交给shiro来做
        return new SimpleAuthenticationInfo(user,user.getUserpass(),"");
    }
}
