package com.codegym.service.relationship;

import com.codegym.model.Relationship;
import com.codegym.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRelationshipService extends IGeneralService<Relationship> {
    @Query("select r from Relationship r where r.user=:id and r.name='friend'")
    List<Relationship> findFriendById(Long id);


    List<Relationship> findRelationshipsByUserAndUserFriend(Long id1, Long id2);
}
