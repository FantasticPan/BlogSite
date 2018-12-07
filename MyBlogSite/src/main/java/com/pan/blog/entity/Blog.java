package com.pan.blog.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Blog 实体
 * Created by FantasticPan on 2018/11/25.
 */
@Entity
@Data
public class Blog implements Serializable {

    private static final long serialVersionUID = 581304626074441455L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min = 2, max = 200)
    @Column(nullable = false, length = 1000) //映射为字段，值不能为空
    private String title;

    @Size(min = 2, max = 200)
    @Column(nullable = false, length = 1000) //映射为字段，值不能为空
    private String summary;

    @Lob                              //大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch = FetchType.LAZY)    //懒加载
    @Size(min = 2)
    @Column(nullable = false)         //映射为字段，值不能为空
    private String content;

    @Lob                              //大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch = FetchType.LAZY)    //懒加载
    @Size(min = 2)
    @Column(nullable = false)         //映射为字段，值不能为空
    private String htmlContent;


    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)                       //映射为字段，值不能为空
    //@org.hibernate.annotations.CreationTimestamp  //由数据库自动创建时间
    private Date createTime;

    @Column(name = "readSize", columnDefinition = "INT default 0")
    private Integer readSize;    //访问量、阅读量

    @Column(name = "commentSize", columnDefinition = "INT default 0")
    private Integer commentSize; //评论量

    @Column(name = "voteSize", columnDefinition = "INT default 0")
    private Integer voteSize;    //点赞量

    @Column(name = "tags", length = 100)
    private String tags;

    //@OneToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    //@JoinColumn(name = "catalog_id")
    //private Catalog catalog;

    private String category;
    private String catalog;
    private String image;

    public Blog() {
    }

    public Blog(@Size(min = 2, max = 200) String title,
                @Size(min = 2, max = 200) String summary,
                @Size(min = 2) String content,
                @Size(min = 2) String htmlContent, String tags, String image) {

        this.title = title;
        this.summary = summary;
        this.content = content;
        this.htmlContent = htmlContent;
        this.tags = tags;
        this.image = image;
    }
}
