package com.codegym.repository;

import com.codegym.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends CrudRepository<Role,Long> {
    @Query("select r from Role r where r.name = ?1")
    Optional<Role> findByName(String name);
}
