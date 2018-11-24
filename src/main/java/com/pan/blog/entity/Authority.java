//package com.pan.blog.entity;
//
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//
//import javax.persistence.*;
//
///**
// * Created by FantasticPan on 2018/11/23.
// */
//@Entity
//@Data
//public class Authority implements GrantedAuthority {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id // 主键
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
//    private Long id; // 用户的唯一标识
//
//    @Column(nullable = false) // 映射为字段，值不能为空
//    private String name;
//
//    @Override
//    public String getAuthority() {
//        return name;
//    }
//}
