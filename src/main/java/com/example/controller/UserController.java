package com.example.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 14431
 */
@Controller
public class UserController {
    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/user/toadd")
    public String toAdd(){
        return "user/add";
    }

    /**
     * 跳转到更新页面
     * @return
     */
    @RequestMapping("/user/toupdate")
    public String toUpdate(){
        return "user/update";
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * 使用shiro来进行登陆验证的Controller
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password, Boolean remember,Model model){
        //获取当前用户
        Subject subject= SecurityUtils.getSubject();
        //封装用户的登录信息,令牌
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        /*执行当前用户的登录操作，执行到这里之后shiro会交给UserRealm来完成剩下的验证操作，
        我们可以通过处理异常来给前端传递一些消息
        */
        try {
            //设置记住我
            token.setRememberMe(remember);
            //登录操作
            subject.login(token);
            //登录成功后添加一个session
            subject.getSession().setAttribute("userLogin",token.getUsername());
            return "index";
        }catch (UnknownAccountException unknownAccountException){//处理用户不存在异常
            model.addAttribute("msg","用户名不存在！");
            return "/login";
        }catch (IncorrectCredentialsException incorrectCredentialsException){//处理密码不正确异常
            model.addAttribute("msg","用户密码错误！");
            return "login";
        }
    }

    /**
     * 登出操作
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        return "index";
    }
}
