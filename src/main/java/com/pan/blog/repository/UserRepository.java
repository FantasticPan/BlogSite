package com.pan.blog.repository;

import com.pan.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by FantasticPan on 2018/11/23.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByNameLike(String name, Pageable pageable);

    User findByUsername(String username);
}
