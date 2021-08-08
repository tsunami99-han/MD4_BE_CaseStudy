package com.codegym.repository;

import com.codegym.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends PagingAndSortingRepository<User,Long> {

    Optional<User> findByUsername(String username);
}
