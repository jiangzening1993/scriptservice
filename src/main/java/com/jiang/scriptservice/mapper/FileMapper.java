package com.jiang.scriptservice.mapper;

import com.jiang.scriptservice.entity.Files;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jiang
 */
@Mapper
@Repository
public interface FileMapper {

    /**
     * 新增脚本文件
     * @param file 脚本文件
     */
    void insert(Files file);

    /**
     * 查询所有脚本
     * @return 返回全部脚本文件
     */
    List<Files> list();

    /**
     * 根据Id查找脚本
     * @param id 脚本ID
     * @return 返回脚本文件
     */
    Files findById(int id);

    /**
     * 根据绝对名字查找脚本
     * @param filePath 脚本绝对路径
     * @return
     */
    Files findByPath(String filePath);
}
