package com.jiang.scriptservice.service;

import com.jiang.scriptservice.dto.PageDto;
import com.jiang.scriptservice.dto.ScriptDto;

/**
 * @author jiang
 */
public interface ScriptService {

    /**
     * 列出特定脚本文档
     * @param page 当前页面
     * @param size 每页脚本数量
     * @return
     */
    public PageDto list(int page, int size);

    /**
     * 根据用户Id列出脚本文档
     * @param userid 用户Id
     * @param page 当前页面
     * @param size 每页脚本数量
     * @return
     */
    public PageDto list(int userid, int page, int size);

    /**
     * 根据Id返回ScriptDto
     * @param id 脚本Id
     * @return 返回ScriptDto
     */
    public ScriptDto getById(int id);
}
