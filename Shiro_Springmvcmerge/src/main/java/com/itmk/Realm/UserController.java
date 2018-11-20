package com.itmk.Realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *@ClassName:UserController
 *@Authos:18505
 *@Date:2018/11/19  19:13
 */
@Controller
public class UserController {

    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String user, String pass, Model model){
        System.out.println(user+":"+pass);
       //spring+shiro+springmvc后
        //MD5盐值加密
        Md5Hash md5Hash=new Md5Hash(pass,user,1024);
        pass=md5Hash.toString();
        System.out.println(pass);
        //1、用户名密码封装成UsernamePasswordToken对象
        UsernamePasswordToken Token=new UsernamePasswordToken(user,pass);
        //2、得到subject对象
        Subject subject= SecurityUtils.getSubject();
        //3、登录
        try {
            //注意：
            //javase环境下（无前端页面），跟ini文件中users信息比较
            //javaee整合后，调用自定义realm中的方法进行认证和授权
            subject.login(Token);

            //编程式判断权限
            System.out.println(subject.hasRole("admin"));
            System.out.println(subject.hasRole("student"));
            return "index";
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
        }
            return "login";
    }
}
