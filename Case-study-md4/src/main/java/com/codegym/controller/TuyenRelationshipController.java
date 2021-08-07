package com.codegym.controller;

import com.codegym.model.Relationship;
import com.codegym.service.relationship.IRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/relationships")
public class TuyenRelationshipController {
    @Autowired
    private IRelationshipService relationshipService;

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Relationship>> findAllFriend(@PathVariable Long id){
        return new ResponseEntity<>(relationshipService.findFriendById(id), HttpStatus.OK);
    }
}
