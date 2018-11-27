package com.pan.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜单值对象
 * Created by FantasticPan on 2018/11/23.
 */
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String url;

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
