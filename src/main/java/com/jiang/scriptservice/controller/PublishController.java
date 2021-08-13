package com.jiang.scriptservice.controller;

import com.jiang.scriptservice.entity.Files;
import com.jiang.scriptservice.entity.Script;
import com.jiang.scriptservice.entity.User;
import com.jiang.scriptservice.mapper.FileMapper;
import com.jiang.scriptservice.mapper.ScriptMapper;
import com.jiang.scriptservice.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author jiang
 */
@Controller
public class PublishController {
    @Resource
    private UserMapper userMapper;

    @Resource
    private ScriptMapper scriptMapper;

    @Resource
    private FileMapper fileMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String publishScript(@RequestParam("title") String title,
                                @RequestParam("description") String description,
                                @RequestParam("tag") String tag,
                                @RequestParam("filePath") String filePath,
                                HttpServletRequest request,
                                Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if(title==null || title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null || description==""){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }
        if(tag==null || tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        //获取当前用户
        User user = null;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
            }
        }

        Files files = null;
        files = fileMapper.findByPath(filePath);

        Script script = new Script();
        script.setTitle(title);
        script.setDescription(description);
        script.setTag(tag);
        script.setCreatorId(user.getId());
        script.setCreateTime(System.currentTimeMillis());
        script.setFileId(files.getId());

        scriptMapper.createScript(script);

        return "redirect:/index";
    }
}
