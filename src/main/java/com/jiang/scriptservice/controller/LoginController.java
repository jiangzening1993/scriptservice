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

/**
 * @author jiang
 */
@Controller
public class LoginController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @PostMapping("/loginCheck")
    public String loginCheck(HttpServletRequest request, HttpServletResponse response){
        User user = new User();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        user.setName(username);
        user.setPassword(password);
        User newUser = userMapper.select(user);

        if(newUser != null){
            String token = newUser.getToken();
            response.addCookie(new Cookie("token", token));
        }
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/index";
    }
}
