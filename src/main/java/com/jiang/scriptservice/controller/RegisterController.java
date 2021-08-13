package com.jiang.scriptservice.controller;

import com.jiang.scriptservice.entity.User;
import com.jiang.scriptservice.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author jiang
 */
@Controller
public class RegisterController {
    @Resource
    private UserMapper userMapper;

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/registerCheck")
    public String registerCheck(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //随机生成token
        String token= UUID.randomUUID().toString();

        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setToken(token);

        userMapper.insert(user);
        if(userMapper.select(user) != null){
            response.addCookie(new Cookie("token",token));
            return "redirect:/index";
        }
        else{
            return "register";
        }
    }
}
