package com.pan.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by FantasticPan on 2018/11/28.
 */
@Data
public class TagVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Long count;

    public TagVO() {
    }

    public TagVO(String name, Long count) {
        this.name = name;
        this.count = count;
    }
}
