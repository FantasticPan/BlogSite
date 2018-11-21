package com.pan.blog.repository;

import com.pan.blog.entity.User;

import java.util.List;

/**
 * Created by FantasticPan on 2018/11/21.
 */
public interface UserRepository {

    /**
     * 创建或者修改用户
     * @param user
     * @return
     */
    User saveOrUpdate(User user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Long id);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 获取用户列表
     * @return
     */
    List<User> listUsers();
}
