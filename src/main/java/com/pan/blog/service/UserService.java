package com.pan.blog.service;

import com.pan.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 用户服务接口
 * Created by FantasticPan on 2018/11/23.
 */
public interface UserService {

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    User saveOrUpdateUser(User user);

    /**
     * 注册用户
     * @param user
     * @return
     */
    User registerUser(User user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    void removeUser(Long id);

    ///**
    // * 删除列表里面的用户
    // *
    // * @param users
    // * @return
    // */
    //void removeUsersInBatch(List<User> users);

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 获取用户列表
     *
     * @return
     */
    //List<User> listUsers();

    /**
     * 根据用户名进行分页模糊查询
     *
     * @param name
     * @param pageable
     * @return
     */
    Page<User> listUsersByNameLike(String name, Pageable pageable);
}
