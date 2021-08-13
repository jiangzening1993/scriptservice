package com.jiang.scriptservice.service.impl;

import com.jiang.scriptservice.dto.PageDto;
import com.jiang.scriptservice.dto.ScriptDto;
import com.jiang.scriptservice.entity.Files;
import com.jiang.scriptservice.entity.Script;
import com.jiang.scriptservice.entity.User;
import com.jiang.scriptservice.mapper.FileMapper;
import com.jiang.scriptservice.mapper.ScriptMapper;
import com.jiang.scriptservice.mapper.UserMapper;
import com.jiang.scriptservice.service.ScriptService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiang
 */
@Service
public class ScriptServiceImpl implements ScriptService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private ScriptMapper scriptMapper;

    @Resource
    private FileMapper fileMapper;

    @Override
    public PageDto list(int page, int size) {
        PageDto pageDto = new PageDto();
        int totalCount = scriptMapper.count();
        pageDto.setPagination(totalCount, page, size);
        //size*{page-1}
        int offset = size * (page - 1);
        //每页只展示5条
        List<Script> scripts = scriptMapper.list(offset, size);
        List<ScriptDto> scriptDtoList = new ArrayList<>();
        for (Script script : scripts) {
            User user = userMapper.findById(script.getCreatorId());
            Files files = fileMapper.findById(script.getFileId());
            ScriptDto scriptDto = new ScriptDto();
            //把第一个对象的所有属性拷贝到第二个对象中
            BeanUtils.copyProperties(script, scriptDto);
            scriptDto.setUser(user);
            scriptDto.setFiles(files);
            scriptDtoList.add(scriptDto);
        }
        pageDto.setData(scriptDtoList);
        return pageDto;
    }

    @Override
    public PageDto list(int userid, int page, int size) {
        PageDto pageDto = new PageDto();
        int totalCount = scriptMapper.countById(userid);
        pageDto.setPagination(totalCount, page, size);
        //size*{page-1}
        int offset = size * (page - 1);
        //每页只展示5条
        List<Script> scripts = scriptMapper.listById(userid, offset, size);
        List<ScriptDto> scriptDtoList = new ArrayList<>();
        for (Script script : scripts) {
            User user = userMapper.findById(script.getCreatorId());
            Files files = fileMapper.findById(script.getFileId());
            ScriptDto scriptDto = new ScriptDto();
            //把第一个对象的所有属性拷贝到第二个对象中
            BeanUtils.copyProperties(script, scriptDto);
            scriptDto.setUser(user);
            scriptDto.setFiles(files);
            scriptDtoList.add(scriptDto);
        }
        pageDto.setData(scriptDtoList);
        return pageDto;
    }

    @Override
    public ScriptDto getById(int id) {
        ScriptDto scriptDto=new ScriptDto();
        Script script=scriptMapper.findById(id);
        //把第一个对象的所有属性拷贝到第二个对象中
        BeanUtils.copyProperties(script, scriptDto);
        User user = userMapper.findById(script.getCreatorId());
        Files files = fileMapper.findById(script.getFileId());
        scriptDto.setUser(user);
        scriptDto.setFiles(files);
        return scriptDto;
    }

}
