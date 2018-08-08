package com.fred.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class TestIniRealm {


    public static void main(String[] args) {

        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        //1. 构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //2. 主体提交认证请求
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("wills", "123456");
        //登陆， 用户名不对：UnknownAccountException，密码不对：IncorrectCredentialsException
        subject.login(usernamePasswordToken);

        //true
        System.out.println(subject.isAuthenticated());

        subject.checkRoles("admin", "user");

        //权限验证
        subject.checkPermissions("delete", "update");


        //登出
        subject.logout();
        //false
        System.out.println(subject.isAuthenticated());


    }
}
