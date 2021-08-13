package com.jiang.scriptservice.service.impl;

import com.jiang.scriptservice.entity.Files;
import com.jiang.scriptservice.mapper.FileMapper;
import com.jiang.scriptservice.response.ResponseCode;
import com.jiang.scriptservice.response.Result;
import com.jiang.scriptservice.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @author jiang
 */
@Service
public class FileServiceImpl implements FileService {
    @Value("${file.save-path}")
    private String savePath;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public Result uploadFile(MultipartFile file){
        Files files=new Files();
        String fileName = file.getOriginalFilename();
        if(StringUtils.isEmpty(fileName)){
            return new Result(ResponseCode.FILE_NAME_EMPTY.getCode(),ResponseCode.FILE_NAME_EMPTY.getMsg(),null);
        }
        String suffixName = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : null;

        //文件的保存重新按照时间戳命名
        String newName = System.currentTimeMillis() + suffixName;
        File newFile=new File(savePath,newName);
        if (!newFile.getParentFile().exists()){
            newFile.getParentFile().mkdirs();
        }
        try {
            //文件写入
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //将这些文件的信息写入到数据库中
        files.setFilePath(newFile.getPath());
        files.setFileName(fileName);
        files.setFileSuffix(suffixName);
        fileMapper.insert(files);

        return new Result(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),newFile.getPath());
    }

    @Override
    public List<Files> findAllFiles(){
        return fileMapper.list();
    }

    @Override
    public Files findFileById(int id){
        return fileMapper.findById(id);
    }

    @Override
    public InputStream getFileInputStream(Files files){
        File file=new File(files.getFilePath());
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
