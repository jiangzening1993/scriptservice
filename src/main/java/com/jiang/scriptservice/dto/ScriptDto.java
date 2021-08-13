package com.jiang.scriptservice.dto;

import com.jiang.scriptservice.entity.Files;
import com.jiang.scriptservice.entity.User;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jiang
 */
@Getter
@Setter
public class ScriptDto {
    private int id;
    private String title;
    private String description;
    private int creatorId;
    private String tag;
    private long createTime;
    private int fileId;

    private User user;
    private Files files;
}
