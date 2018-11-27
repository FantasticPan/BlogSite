package com.pan.blog.service;

import com.pan.blog.entity.Vote;

/**
 * Created by FantasticPan on 2018/11/27.
 */
public interface VoteService {

    /**
     * 根据id获取vote
     * @param id
     * @return
     */
    Vote getVoteById(Long id);

    /**
     * 删除点赞
     * @param id
     */
    void removeVote(Long id);
}
