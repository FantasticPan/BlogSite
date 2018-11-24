package com.pan.blog.service.impl;

import com.pan.blog.entity.User;
import com.pan.blog.repository.UserRepository;
import com.pan.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by FantasticPan on 2018/11/23.
 */
@Service
public class UserServiceImpl implements UserService
        //, UserDetailsService
{

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public User saveOrUpdateUser(User user) {
        System.out.println(user.toString());
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    //@Transactional
    //@Override
    //public void removeUsersInBatch(List<User> users) {
    //    userRepository.deleteInBatch(users);
    //}

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    //@Override
    //public List<User> listUsers() {
    //    return userRepository.findAll();
    //}

    @Override
    public Page<User> listUsersByNameLike(String name, Pageable pageable) {
        // 模糊查询
        name = "%" + name + "%";
        return userRepository.findByNameLike(name, pageable);
    }

    //@Override
    //public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //    return userRepository.findByUsername(username);
    //}
}
