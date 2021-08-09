package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.user.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ThaoController {
    @Autowired
    private IUserService userService;

    @GetMapping("/search/{username}")
    public ResponseEntity<List<User>> findByUserName(@PathVariable("username") String username) {
        Optional<User> userList = userService.findByUsername(username);
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @GetMapping("/find-common")
    public ResponseEntity<List<User>> findAllFriendCommon(@RequestParam("nameRelationship") String nameRelationship,
                                                          @RequestParam("userName") String userName,
                                                          @RequestParam("friendName") String friendName) {
        List<User> friendsCommon = userService.getUserCommons(nameRelationship, userName, friendName);
        return new ResponseEntity(friendsCommon, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> checkAccount(@RequestBody String requestData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(requestData, User.class);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
