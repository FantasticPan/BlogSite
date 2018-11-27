package com.pan.blog.repository;

import com.pan.blog.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by FantasticPan on 2018/11/27.
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
