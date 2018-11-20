package com.itmk.Realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/*
 *@ClassName:UserRealm
 *@Authos:许杰
 *@Date:2018/11/19  17:46
 */
public class UserRealm extends AuthorizingRealm {

   //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("--------授权");
        SimpleAuthorizationInfo AuthorizationInfo=new SimpleAuthorizationInfo();
        //根据登录用户的信息（id）,查询用户的角色
        //根据角色id查询用户的权限
        List<String> role=new ArrayList();
        role.add("admin");
        role.add("student");
        role.add("teacher");
        //权限
        List<String> perms=new ArrayList();
        perms.add("sys:user:*");
        perms.add("sys:project:*");
        AuthorizationInfo.addRoles(role);
        AuthorizationInfo.addStringPermissions(perms);

        return AuthorizationInfo;
    }
   //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("--------认证");
        String username = (String) authenticationToken.getPrincipal();
        String password =new String((char[])authenticationToken.getCredentials());
        System.out.println(username + ":" + password);
        //----------------------------
        //数据库假定信息
        String dbusername = "admin";
        String dbpassword = "df655ad8d3229f3269fad2a8bab59b6c";
        int state = 1;//状态信息 1：正常 2：锁定
        if (username.equals(dbusername)) {
            if (password.equals(dbpassword)){
                if (state == 1) {
                   //通过带三个参数的构造方法实例化对象 把用户信息存储到shiro中
                    SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(username,password,this.getName());
                            return authenticationInfo;
                }else {
                    throw new LockedAccountException("账号锁定");
                }
            } else {
                throw new IncorrectCredentialsException("密码错误");
            }
        }else{
            throw new UnknownAccountException("账号不存在");
        }
    }
}
