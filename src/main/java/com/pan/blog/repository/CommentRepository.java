package com.pan.blog.repository;

import com.pan.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by FantasticPan on 2018/11/26.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
