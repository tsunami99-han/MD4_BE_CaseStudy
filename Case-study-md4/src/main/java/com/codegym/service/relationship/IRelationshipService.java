package com.codegym.service.relationship;

import com.codegym.model.Relationship;
import com.codegym.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IRelationshipService extends IGeneralService<Relationship> {

    // Tìm danh sách bạn bè
    List<Relationship> findFriendById(Long id);

//    @Query()
//    Iterable<Relationship> findMutualFriend(Long id1,Long id2);
    Optional<Relationship> findByNameAndUserAndUserFriend(String name,Long id_User,Long id_UserFriend);



}