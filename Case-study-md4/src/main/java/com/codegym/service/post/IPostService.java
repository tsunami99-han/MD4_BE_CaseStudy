package com.codegym.service.post;

import com.codegym.model.Post;
import com.codegym.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;

public interface IPostService extends IGeneralService<Post> {
    @Query("select p from Post p inner join p.account u where u.fullName like: name")
    Iterable<Post> findAllByAccountContaining(String name);
}
