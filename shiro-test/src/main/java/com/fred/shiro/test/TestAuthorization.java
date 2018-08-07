package com.fred.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;

/**
 * @author liuxiaokun
 * 测试授权
 */
public class TestAuthorization {

    public static void main(String[] args) {

        SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
        simpleAccountRealm.addAccount("fred", "123", "admin", "user");

        //1. 构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //2. 主体提交认证请求
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("fred", "123");
        //登陆， 用户名不对：UnknownAccountException，密码不对：IncorrectCredentialsException
        subject.login(usernamePasswordToken);

        //true
        System.out.println(subject.isAuthenticated());
        //校验不通过：UnauthorizedException: Subject does not have role [admin1]
        subject.checkRoles("admin", "user");



    }
}
