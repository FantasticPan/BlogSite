package com.pan.blog.dao;

import com.pan.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by FantasticPan on 2018/11/25.
 */
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
