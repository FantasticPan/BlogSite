package com.pan.blog.service;

import com.pan.blog.entity.Blog;

import java.util.List;

/**
 * Created by FantasticPan on 2018/11/25.
 */
public interface BlogService {

    /**
     * 保存博客
     *
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * 删除博客
     *
     * @param id
     * @return
     */
    void deleteBlog(Long id);

    /**
     * 根据id获取博客
     *
     * @param id
     * @return
     */
    Blog getBlogById(Long id);

    List<Blog> getAllBlog();

    Long blogNum();

    List<Blog> findBlogByTag(String tag);

    List<Blog> findBlogByCatalog(String catalog);
}