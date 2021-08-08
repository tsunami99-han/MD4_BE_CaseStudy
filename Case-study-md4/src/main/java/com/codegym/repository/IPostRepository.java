package com.codegym.repository;

import com.codegym.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {
//    @Query("select p from Post p inner join p.account u where u.fullName like ?1")
    Iterable<Post> findAllByContentContaining(String content);
}

