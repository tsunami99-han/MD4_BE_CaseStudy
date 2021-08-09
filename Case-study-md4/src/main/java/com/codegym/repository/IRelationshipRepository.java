package com.codegym.repository;

import com.codegym.model.Relationship;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRelationshipRepository extends PagingAndSortingRepository<Relationship,Long>{
    @Query(value = "from Relationship where name like :name" +
            " and (user.username = :userName or userFriend.username = :userName)")
    List<Relationship> findAllFriends(@Param("name") String name,
                                      @Param("userName") String userName);

}
