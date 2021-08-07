package com.codegym.repository;

import com.codegym.model.Relationship;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRelationshipRepository extends PagingAndSortingRepository<Relationship,Long>{
    @Query("select r from Relationship r where r.name = 'friend' and r.user.id =:id")
    List<Relationship> findFriendById(Long id);

    List<Relationship> findRelationshipsByUserAndUserFriend(Long id1, Long id2);
}
