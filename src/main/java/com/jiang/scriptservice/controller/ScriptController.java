package com.jiang.scriptservice.controller;

import com.jiang.scriptservice.dto.ScriptDto;
import com.jiang.scriptservice.service.ScriptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @author jiang
 */
@Controller
public class ScriptController {
    @Resource
    private ScriptService scriptService;

    @GetMapping("/script/{id}")
    public String script(@PathVariable(name = "id")int id,
                         Model model){
        ScriptDto scriptDto = scriptService.getById(id);
        model.addAttribute("scriptDto",scriptDto);
        return "script";
    }
}
