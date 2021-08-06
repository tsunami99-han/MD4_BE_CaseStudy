package com.codegym.repository;

import com.codegym.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends CrudRepository<Role,Long> {

    Optional<Role> findByName(String name);
}
