package com.pan.blog.dao;

import com.pan.blog.entity.Tag;
import com.pan.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by FantasticPan on 2018/11/25.
 */
public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findTagsByUser(User user);

    List<Tag> findAll();

    List<Tag> findTagsByTagName(String tagName);
}
