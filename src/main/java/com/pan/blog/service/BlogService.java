package com.pan.blog.service;

import com.pan.blog.entity.Blog;
import com.pan.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    void removeBlog(Long id);

    ///**
    // * 更新博客
    // */
    //Blog updateBlog(Blog blog);

    /**
     * 根据id获取博客
     *
     * @param id
     * @return
     */
    Blog getBlogById(Long id);

    /**
     * 根据用户名进行分页模糊查询（最新）
     *
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable);

    /**
     * 根据用户名进行分页模糊查询（最热）
     *
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByTitleVoteAndSort(User user, String title, Pageable pageable);

    /**
     * 阅读量递增
     *
     * @param id
     */
    void readingIncrease(Long id);

    /**
     * 发表评论
     *
     * @param blogId
     * @param commentContent
     * @return
     */
    Blog createComment(Long blogId, String commentContent);

    /**
     * 删除评论
     *
     * @param blogId
     * @param commentId
     * @return
     */
    void removeComment(Long blogId, Long commentId);

    /**
     * 点赞
     *
     * @param blogId
     * @return
     */
    Blog createVote(Long blogId);

    /**
     * 取消点赞
     *
     * @param blogId
     * @param voteId
     * @return
     */
    void removeVote(Long blogId, Long voteId);
}
