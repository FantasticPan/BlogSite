package com.pan.blog.service.impl;

import com.pan.blog.entity.Blog;
import com.pan.blog.entity.User;
import com.pan.blog.repository.BlogRepository;
import com.pan.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by FantasticPan on 2018/11/25.
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public void removeBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable) {
        //模糊查询
        title = "%" + title + "%";
        Page<Blog> blogs = blogRepository.findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(user, title, pageable);
        return blogs;
    }

    @Override
    public Page<Blog> listBlogsByTitleVoteAndSort(User user, String title, Pageable pageable) {
        //模糊查询
        title = "%" + title + "%";
        return blogRepository.findByUserAndTitleLike(user, title, pageable);
    }

    @Override
    public void readingIncrease(Long id) {
        Blog blog = blogRepository.getOne(id);
        blog.setReadSize(blog.getCommentSize() + 1);
        this.saveBlog(blog);
    }
}
