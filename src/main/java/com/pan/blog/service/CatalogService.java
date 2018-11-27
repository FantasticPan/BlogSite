package com.pan.blog.service;

import com.pan.blog.entity.Catalog;
import com.pan.blog.entity.User;

import java.util.List;

/**
 * Created by FantasticPan on 2018/11/27.
 */
public interface CatalogService {

    /**
     * 保存分类
     * @param catalog
     * @return
     */
    Catalog saveCatalog(Catalog catalog);

    /**
     * 删除分类
     * @param id
     */
    void removeCatalog(Long id);

    /**
     * 根据id获取分类
     * @param id
     * @return
     */
    Catalog getCatalogById(Long id);

    /**
     * 获取分类列表
     * @param user
     * @return
     */
    List<Catalog> listCatalogs(User user);

    /**
     *
     * @param catalog
     * @return
     */
    //Catalog insertAnonymousCatalog(Catalog catalog);
}
