package com.example.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作为一个配置类，用来配置Shiro的一些必要的信息,使用配置类一定不要忘了添加注解
 * @author planifolia
 */
@Configuration
public class ShiroConfig {
    /**
     * ShiroFilterFactoryBean,需要shiroWebSecurityManager是它必须要设定的属性值,赋值完毕之后交给Spring处理<br>
     * 设置应用程序 SecurityManager 实例以供构造的 Shiro 过滤器使用。这是一个必需的属性
     * - 设置失败将引发初始化异常。
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);
        /**
         * tip：用户权限的类型
         * 1.anon，任何人
         * 2.authc，需要认证了
         * 3.user，需要记住我
         * 4.perms，需要拥有某个资源
         * 5.role，需要拥有某个角色
         */
        //我们在这里面进行用户的拦截
        //创建拦截的url以及对应的权限用户，用map来保存
        Map<String,String> filterMap=new LinkedHashMap<>();
        //设置只有add权限的才能继续操作
        filterMap.put("/user/toadd","perms[user:add]");
        filterMap.put("/user/toupdate","perms[user:update]");
        //设定user下的网页只有认证之后才能访问
        filterMap.put("/user/*","authc");
        //将属性放到ShiroFilterFactoryBean中
        bean.setFilterChainDefinitionMap(filterMap);
        bean.setLoginUrl("/toLogin");
        return bean;
    }
    /**
     * DefaultWebSecurityManager,它需要UserRealm作为参数，然后再将它当作bean交给Spring
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager bean=new DefaultWebSecurityManager();
        bean.setRealm(userRealm);
        return bean;

    }
    /**
     * realm对象，只需要返回一个bean对象交给Spring容器就可以了
     */
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    /**
     * 创建shiro方言
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
