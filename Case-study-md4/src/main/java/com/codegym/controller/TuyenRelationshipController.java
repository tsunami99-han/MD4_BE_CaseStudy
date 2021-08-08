package com.codegym.controller;

import com.codegym.model.Relationship;
import com.codegym.model.User;
import com.codegym.service.relationship.IRelationshipService;
import com.codegym.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/relationships")
public class TuyenRelationshipController {
    @Autowired
    private IRelationshipService relationshipService;

    @Autowired
    private UserService userService;

//    @GetMapping("/{id}")
//    public ResponseEntity<List<User>> findAllFriend(@PathVariable Long id){
//        Iterable<Relationship> relationships = relationshipService.findFriendById(id);
//        List<User> users = new ArrayList<>();
//        for (Relationship relation:relationships) {
//            User user = userService.findById(relation.getUserFriend().getId()).get();
//            users.add(user);
//        }
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    // Tìm list bạn bè
    @GetMapping("/{id}")
    public ResponseEntity<List<User>> findAllFriend(@PathVariable Long id){
        List<User> users = getUser(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    // Tìm list bạn chung
    @GetMapping("/search/{id1}/{id2}")
    public ResponseEntity<List<User>>findMutualFriends(@PathVariable Long id1, @PathVariable Long id2){
        List<User> users = new ArrayList<>();
        List<User> usersId1 = getUser(id1);
        List<User> usersId2 = getUser(id2);
        for (User user1:usersId1) {
            for (User user2:usersId2) {
                if (user1.getId()==user2.getId()){
                    users.add(user1);
                }
            }
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
//    @GetMapping("/search/{id1}/{id2}")
//    public ResponseEntity<List<User>> findMutualFriends(@PathVariable Long id1, @PathVariable Long id2){
//        List<Relationship> relationships = relationshipService.findRelationshipsByUserAndUserFriend(id1, id2);
//        List<User> users = new ArrayList<>();
//        for (Relationship relationship: relationships) {
//            User user = userService.findById(relationship.getUserFriend().getId()).get();
//            users.add(user);
//        }
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
    // Lấy ra đối tượng user từ relationship
    public List<User> getUser(Long id){
        Iterable<Relationship> relationships = relationshipService.findFriendById(id);
        List<User> users = new ArrayList<>();
        for (Relationship relation:relationships) {
            User user = userService.findById(relation.getUserFriend().getId()).get();
            users.add(user);
        }
        return users;
    }

    // Hủy kết bạn
    @GetMapping("/delete/{id1}/{id2}")
    public ResponseEntity<Relationship> unFriend(@PathVariable Long id1, @PathVariable Long id2){
        relationshipService.unFriend(id1, id2);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
