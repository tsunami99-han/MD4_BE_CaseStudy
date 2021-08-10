package com.codegym.repository;

import com.codegym.model.Relationship;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRelationshipRepository extends PagingAndSortingRepository<Relationship,Long>{

    // Lấy danh sách bạn bè từ user
    @Query("select r from Relationship r where r.name = 'friend' and r.user.id =:id")
    List<Relationship> findFriendById(Long id);




    // Lấy danh sách bạn chung
    List<Relationship> findRelationshipsByUserAndUserFriend(Long id1, Long id2);

    // Hủy kết bạn
    @Query("delete from Relationship where name='friend' and user.id = :id1 and userFriend.id = :id2")
    void unFriend(Long id1, Long id2);
}
