package com.fred.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;

/**
 * @author liuxiaokun
 * 测试认证用户名和密码
 */
public class TestAuthentication {

    public static void main(String[] args) {

        SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
        simpleAccountRealm.addAccount("fred", "123");

        //1. 构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //2. 主体提交认证请求
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("fred", "123");
        subject.login(usernamePasswordToken);

        System.out.println(subject.isAuthenticated());

    }
}
