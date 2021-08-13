package com.jiang.scriptservice.controller;

import com.jiang.scriptservice.dto.PageDto;
import com.jiang.scriptservice.entity.User;
import com.jiang.scriptservice.mapper.UserMapper;
import com.jiang.scriptservice.service.ScriptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author jiang
 */
@Controller
public class PersonalController {
    @Resource
    private ScriptService scriptService;

    @Resource
    private UserMapper userMapper;

    @GetMapping("/personal/{action}")
    public String personal(@PathVariable(name = "action")String action,
                           Model model,
                           HttpServletRequest request,
                           @RequestParam(name = "page",defaultValue = "1")int page,
                           @RequestParam(name = "size",defaultValue = "5")int size){

        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return "login";
        }

        User user = null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }

        if(action.equals("scripts")){
            model.addAttribute("section","scripts");
            model.addAttribute("sectionName","我的脚本");
        }

        PageDto pagination=scriptService.list(user.getId(),page,size);
        model.addAttribute("pagination", pagination);

        return "personal";
    }

}
