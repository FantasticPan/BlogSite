package com.pan.blog.service.impl;

import com.pan.blog.dao.TagRepository;
import com.pan.blog.entity.Tag;
import com.pan.blog.entity.User;
import com.pan.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by FantasticPan on 2018/11/25.
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> findTagsByUser(User user) {
        return tagRepository.findTagsByUser(user);
    }

    @Override
    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }
}
