package com.pan.blog.entity;

import com.github.rjeschke.txtmark.Processor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Blog 实体
 * Created by FantasticPan on 2018/11/25.
 */
@Entity
@Data
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //用户的唯一标识

    @NotEmpty(message = "标题不能为空")
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50) //映射为字段，值不能为空
    private String title;

    @NotEmpty(message = "摘要不能为空")
    @Size(min = 2, max = 300)
    @Column(nullable = false)          // 映射为字段，值不能为空
    private String summary;

    @Lob                              //大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch = FetchType.LAZY)    //懒加载
    @NotEmpty(message = "内容不能为空")
    @Size(min = 2)
    @Column(nullable = false)         //映射为字段，值不能为空
    private String content;

    @Lob                               //大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch = FetchType.LAZY)     // 懒加载
    @NotEmpty(message = "内容不能为空")
    @Size(min = 2)
    @Column(nullable = false)          //映射为字段，值不能为空
    private String htmlContent;        //将 md 转为 html


    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)                     //映射为字段，值不能为空
    @org.hibernate.annotations.CreationTimestamp  //由数据库自动创建时间
    private Timestamp createTime;

    @Column(name = "readSize")
    private Integer readSize = 0;    //访问量、阅读量

    @Column(name = "commentSize")
    private Integer commentSize = 0; //评论量

    @Column(name = "voteSize")
    private Integer voteSize = 0;    //点赞量

    @Column(name = "tags", length = 100)
    private String tags;             //标签

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "blog_comment", joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"))
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "blog_vote", joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id", referencedColumnName = "id"))
    private List<Vote> votes;

    protected Blog() {
    }

    public Blog(String title, String summary, String content) {
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
        this.htmlContent = Processor.process(content); //将Markdown内容转化为HTML格式
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
        this.commentSize = this.comments.size();
    }

    /**
     * 添加评论
     *
     * @param comment
     */
    public void addComment(Comment comment) {
        this.comments.add(comment);
        this.commentSize = this.comments.size();
    }

    /**
     * 删除评论
     *
     * @param commentId
     */
    public void removeComment(Long commentId) {
        for (int index = 0; index < this.comments.size(); index++) {
            if (comments.get(index).getId() == commentId) {
                this.comments.remove(index);
                break;
            }
        }
        this.commentSize = this.comments.size();
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
        this.voteSize = this.votes.size();
    }

    /**
     * 点赞
     *
     * @param vote
     * @return
     */
    public boolean addVote(Vote vote) {
        boolean isExist = false;

        //判断重复
        for (int index = 0; index < this.votes.size(); index++) {
            if (this.votes.get(index).getUser().getId() == vote.getUser().getId()) {
                isExist = true;
                break;
            }
        }

        //没有重复
        if (!isExist) {
            this.votes.add(vote);
            this.voteSize = this.votes.size();
        }

        return isExist;
    }

    /**
     * 取消点赞
     *
     * @param voteId
     */
    public void removeVote(Long voteId) {
        for (int index = 0; index < this.votes.size(); index++) {
            if (this.votes.get(index).getId() == voteId) {
                this.votes.remove(index);
                break;
            }
        }
        this.voteSize = this.votes.size();
    }
}
