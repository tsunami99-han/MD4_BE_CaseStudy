package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.service.jwt.JwtService;
import com.codegym.service.relationship.IRelationshipService;
import com.codegym.service.role.IRoleService;
import com.codegym.service.user.IUserService;
import com.codegym.service.verificationToken.IVerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IVerificationTokenService verificationTokenService;
    @Autowired
    private IRelationshipService relationshipService;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return new ResponseEntity(userService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/users/register")
    public ResponseEntity<User> create(@RequestBody User user){
        Iterable<User> users = userService.findAll();
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user.getUsername())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        Optional<Role> role = roleService.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role.get());
        user.setRoleSet(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        VerificationToken token = new VerificationToken(user);
        token.setExpiryDate(10);
        verificationTokenService.save(token);
        User user1 = userService.findByUsername(user.getUsername()).get();
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(user.getUsername()).get();
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getFullName(), userDetails.getAuthorities()));
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        if (!userService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userService.findById(id).get(), HttpStatus.OK);
    }

    //sửa thông tin cá nhân
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(userOptional.get().getId());
        user.setUsername(userOptional.get().getUsername());
        user.setPassword(userOptional.get().getPassword());
        user.setRoleSet(userOptional.get().getRoleSet());
        user.setAvatar(userOptional.get().getAvatar());
        user.setBackground(userOptional.get().getBackground());
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
//khóa tài khoản
    @GetMapping("/admin/users/{id}/lock")
    public ResponseEntity<Void> lockUser(@PathVariable("id") Long id){
        User user= userService.findById(id).get();
        user.setStatus(false);
        userService.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }
//bỏ khóa
    @GetMapping("/admin/users/{id}/unlock")
    public ResponseEntity<Void> unlockUser(@PathVariable("id") Long id){
        User user= userService.findById(id).get();
        user.setStatus(true);
        userService.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    //thêm bạn
    @PostMapping("/users/addfriend")
    public ResponseEntity<Void> addFriend(@RequestBody Relationship relationship){
        relationshipService.save(relationship);
        return new ResponseEntity(HttpStatus.OK);
    }
    //hủy kết bạn
    @PostMapping("/users/unfriend/{id}")
    public ResponseEntity<Void> unFriend(@PathVariable("id") Long id){
        relationshipService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    //block người dùng
    @PostMapping("/users/block")
    public ResponseEntity<Void> blockUser(@RequestBody Relationship relationship){
        relationshipService.save(relationship);
        return new ResponseEntity(HttpStatus.OK);
    }
    //unblock người dùng
    @PostMapping("/users/unblock")
    public ResponseEntity<Void> unblockUser(@PathVariable("id") Long id){
        relationshipService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
