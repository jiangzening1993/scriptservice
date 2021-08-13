package com.jiang.scriptservice.mapper;

import com.jiang.scriptservice.entity.Script;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jiang
 */
@Mapper
@Repository
public interface ScriptMapper {

    /**
     * 新增脚本
     * @param script 脚本项目
     */
    void createScript(Script script);

    /**
     * 查询全部脚本
     * @param offset 当前脚本
     * @param size 每页脚本
     * @return 返回脚本List
     */
    List<Script> list(@Param("offset") int offset, @Param("size") int size);

    /**
     * 计算脚本数量
     * @return 返回脚本数量
     */
    int count();

    /**
     * 根据用户Id计算脚本数量
     * @param userid 用户Id
     * @return 脚本数量
     */
    int countById(int userid);

    /**
     * 根据用户Id列出脚本
     * @param userid 用户Id
     * @param offset 当前脚本
     * @param size 每页脚本
     * @return 脚本List
     */
    List<Script> listById(@Param("userid")int userid, @Param("offset") int offset, @Param("size") int size);

    /**
     * 根据脚本Id查找脚本
     * @param id 脚本Id
     * @return 脚本
     */
    Script findById(int id);
}
