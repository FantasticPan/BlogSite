package com.pan.blog.service;

import com.pan.blog.entity.Tag;
import com.pan.blog.entity.User;

import java.util.List;

/**
 * Created by FantasticPan on 2018/11/25.
 */
public interface TagService {

    Tag saveTag(Tag tag);

    List<Tag> findTagsByUser(User user);

    List<Tag> findAllTags();
}
