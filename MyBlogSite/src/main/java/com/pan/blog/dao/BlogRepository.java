package com.pan.blog.dao;

import com.pan.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by FantasticPan on 2018/11/25.
 */
public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findBlogsByCatalog(String catalog);

    List<Blog> findBlogByTags(String tag);
}
