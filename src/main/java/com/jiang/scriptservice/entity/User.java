package com.jiang.scriptservice.entity;

import lombok.Data;

/**
 * @author jiang
 */
@Data
public class User {
    private int id;
    private String name;
    private String password;
    private String token;
}
