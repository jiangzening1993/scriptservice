package com.jiang.scriptservice.controller;

import com.jiang.scriptservice.entity.Files;
import com.jiang.scriptservice.response.Result;
import com.jiang.scriptservice.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author jiang
 */
@RestController
public class FileController {
    private static final Logger LOGGER= LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public Object uploadFile(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            LOGGER.error("上传失败，请选择文件！");
            return "上传失败";
        }
        try {
            Result result = fileService.uploadFile(file);
            LOGGER.info("文件上传成功！");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件上传失败！");
            return "上传失败";
        }
    }
}
