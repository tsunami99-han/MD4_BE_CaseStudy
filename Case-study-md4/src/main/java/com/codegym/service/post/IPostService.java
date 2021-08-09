package com.codegym.service.post;

import com.codegym.model.Post;
import com.codegym.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;

public interface IPostService extends IGeneralService<Post> {

    Iterable<Post> findAllByContentContaining(String content);
}