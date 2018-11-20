package com.itmk.Realm;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/*
 *@ClassName:ShiroTest
 *@Authos:18505
 *@Date:2018/11/19  15:44
 */
public class ShiroTest {

    public static void main(String[] args) {
        //1、加载配置文件
        Factory<SecurityManager> factory =new IniSecurityManagerFactory("classpath:shiro.ini");
        //2、解析配置文件
        SecurityManager securityManager=factory.getInstance();
        //3、同过SecurityUtils设置安全管理
        SecurityUtils.setSecurityManager(securityManager);
        //4、获取用户
        Subject subject=SecurityUtils.getSubject();

        if (!subject.isAuthenticated()){
            UsernamePasswordToken token=new UsernamePasswordToken("jack","jack");

            try{
                subject.login(token);
                System.out.println(subject.isAuthenticated()?"登录成功":"登录失败");

                System.out.println(subject.hasRole("admin")?"存在":"不存在");

            }catch(UnknownAccountException c){
                System.out.println("账号错误");
            }catch (IncorrectCredentialsException c){
                System.out.println("密码错误");
            }catch(LockedAccountException c){
                System.out.println("账号锁定");
            }


        }
    }
}
