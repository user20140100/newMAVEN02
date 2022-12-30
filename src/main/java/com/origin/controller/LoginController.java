package com.origin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.origin.common.R;
import com.origin.entity.Login;
import com.origin.service.service01.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService l1;

    @PostMapping("/login")
    @CrossOrigin
    public R<Login> Logincon(HttpServletRequest request, @RequestBody Login login){
        String password = login.getPassword();
        if(Objects.equals(login.getUsername(), "") || Objects.equals(password, "")){
            return R.error("账号或密码不能为空");
        }
        System.out.println(password + login.getUsername() +"姓名"+ login.getXingming()+"id:"+login.getId());
        LambdaQueryWrapper<Login> qw = new LambdaQueryWrapper<>();
        qw.eq(Login::getUsername,login.getUsername());
        Login lg = l1.getOne(qw);
        if(lg!=null) {
            System.out.println("数据库返回内容：" + lg.getPassword() + lg.getUsername() + lg.getId());
        }
        if(lg==null){
            return R.error("账号错误，登录失败");
        }
        if(!lg.getPassword().equals(password)){
            return R.error("密码错误，登录失败");
        }
        else{
            request.getSession().setAttribute("Loginuserid",lg.getId());
            request.getSession().setAttribute("Loginusername",lg.getUsername());
            request.getSession().setAttribute("Loginxingming",lg.getXingming());
            System.out.println("输出lg:"+lg.getUsername()+lg.getPassword());
            return R.success(lg);
        }

    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){

        request.getSession().removeAttribute("Loginuserid");

        return R.success("退出成功");
    }

}
