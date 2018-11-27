package com.pan.blog.vo;

import com.pan.blog.entity.Catalog;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by FantasticPan on 2018/11/27.
 */
@Data
public class CatalogVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private Catalog catalog;
}
