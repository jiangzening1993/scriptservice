package com.jiang.scriptservice.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jiang
 */
@Data
public class Files implements Serializable {
    private static final long serialVersionUID=1L;

    private int id;
    private String filePath;
    private String fileName;
    private String fileSuffix;
}
