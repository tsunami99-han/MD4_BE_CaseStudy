package com.codegym.repository;

import com.codegym.model.ImagePost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends CrudRepository<ImagePost,Long> {
}
