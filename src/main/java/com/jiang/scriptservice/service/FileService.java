package com.jiang.scriptservice.service;

import com.jiang.scriptservice.entity.Files;
import com.jiang.scriptservice.response.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @author jiang
 */
public interface FileService {

    /**
     * 上传文件
     * @param file 文件
     * @return 返回上传状态
     */
    Result uploadFile(MultipartFile file);

    /**
     * 查询全部脚本文件
     * @return 返回文件List
     */
    List<Files> findAllFiles();

    /**
     * 根据ID查询脚本文件
     * @param id 脚本Id
     * @return 返回文件
     */
    Files findFileById(int id);

    /**
     * 文件转换InputStream
     * @param files 文件
     * @return 返回输入流
     */
    InputStream getFileInputStream(Files files);
}
