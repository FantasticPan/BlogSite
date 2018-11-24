package com.pan.blog.repository;

import com.pan.blog.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Authority 仓库
 * Created by FantasticPan on 2018/11/23.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
