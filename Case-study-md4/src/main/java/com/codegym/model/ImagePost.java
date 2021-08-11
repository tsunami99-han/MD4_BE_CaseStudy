package com.codegym.model;

import javax.persistence.*;

@Entity
public class ImagePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private Long postId;

    public ImagePost() {
    }

    public ImagePost(String image, Long postId) {
        this.image = image;
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "ImagePost{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", postId=" + postId +
                '}';
    }
}
