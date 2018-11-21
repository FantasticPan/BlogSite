package com.pan.blog.entity;

import lombok.Data;

/**
 * User实体
 * Created by FantasticPan on 2018/11/21.
 */
@Data
public class User {

    private Long id;
    private String name;
    private String email;

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
