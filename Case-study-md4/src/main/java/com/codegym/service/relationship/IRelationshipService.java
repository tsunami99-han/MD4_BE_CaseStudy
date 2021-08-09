package com.codegym.service.relationship;

import com.codegym.model.Relationship;
import com.codegym.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRelationshipService extends IGeneralService<Relationship> {

    // Tìm danh sách bạn bè
    List<Relationship> findFriendById(Long id);


    // Tìm bạn chung
    List<Relationship> findRelationshipsByUserAndUserFriend(Long id1, Long id2);

    // Hủy kết bạn,
    void unFriend(Long id1, Long id2);
}