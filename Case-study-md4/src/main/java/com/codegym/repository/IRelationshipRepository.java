package com.codegym.repository;

import com.codegym.model.Relationship;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRelationshipRepository extends PagingAndSortingRepository<Relationship,Long>{
}
