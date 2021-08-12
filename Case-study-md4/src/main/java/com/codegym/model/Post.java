package com.codegym.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime time;

    @ManyToOne
    private User user;

    private String status;

    private long likeQuantity=0;
    private long commentQuantity=0;

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getLikeQuantity() {
        return likeQuantity;
    }

    public void setLikeQuantity(long likeQuantity) {
        this.likeQuantity = likeQuantity;
    }

    public long getCommentQuantity() {
        return commentQuantity;
    }

    public void setCommentQuantity(long commentQuantity) {
        this.commentQuantity = commentQuantity;
    }
}
