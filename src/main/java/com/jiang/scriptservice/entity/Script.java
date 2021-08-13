package com.jiang.scriptservice.entity;

import lombok.Data;

/**
 * @author jiang
 */
@Data
public class Script {
    private int id;
    private String title;
    private String description;
    private int creatorId;
    private String tag;
    private long createTime;
    private int fileId;
}
