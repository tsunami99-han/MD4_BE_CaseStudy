package com.codegym.controller;

import com.codegym.model.Role;
import com.codegym.model.User;
import com.codegym.model.VerificationToken;
import com.codegym.service.role.IRoleService;
import com.codegym.service.user.IUserService;
import com.codegym.service.verificationTokenService.IVerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class TuyenUserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleService roleService;


    @Autowired
    private IVerificationTokenService verificationTokenService;

    @PostMapping("/register")
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
        User user1 = userService.findAllByUserNameContaining(user.getUsername()).get();
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<Iterable<User>> findAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        if (!userService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userService.findById(id).get(), HttpStatus.OK);

    }
}
