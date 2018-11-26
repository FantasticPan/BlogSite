package com.pan.blog.service;

import com.pan.blog.entity.Comment;

/**
 * Created by FantasticPan on 2018/11/26.
 */
public interface CommentService {

    /**
     * 根据id获取评论
     *
     * @param id
     * @return
     */
    Comment getCommentById(Long id);

    /**
     * 删除评论
     *
     * @param id
     * @return
     */
    void removeComment(Long id);
}
