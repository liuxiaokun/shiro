package com.fred.shiro.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class TestJdbcRealm {


    public static void main(String[] args) {

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/jdbcRealm");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("mysql");


        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(druidDataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);

        //1. 构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //2. 主体提交认证请求
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("wills", "1234");
        //登陆， 用户名不对：UnknownAccountException，密码不对：IncorrectCredentialsException
        subject.login(usernamePasswordToken);

        //true
        System.out.println(subject.isAuthenticated());

        subject.checkRoles("admin", "user");

        //权限验证, jdbcReal默认权限是false
        subject.checkPermissions("delete", "update");


        //登出
        subject.logout();
        //false
        System.out.println(subject.isAuthenticated());


    }
}
