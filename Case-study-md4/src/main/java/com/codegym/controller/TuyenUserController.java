package com.codegym.controller;

import com.codegym.model.JwtResponse;
import com.codegym.model.Role;
import com.codegym.model.User;
import com.codegym.model.VerificationToken;
import com.codegym.service.jwt.JwtService;
import com.codegym.service.role.IRoleService;
import com.codegym.service.user.IUserService;
import com.codegym.service.verificationTokenService.IVerificationTokenService;
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
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class TuyenUserController {
    private String uploadPath = "C:\\Users\\Admin-Thính\\Desktop\\FE\\MD4_FE_CaseStudy\\Case-study-md4-wt\\image";

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
        User user1 = userService.findByUsername(user.getUsername()).get();
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<Iterable<User>> findAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    // Xem trang cá nhân
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        if (!userService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userService.findById(id).get(), HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(user.getUsername()).get();
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getFullName(), userDetails.getAuthorities()));
    }
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user){
        System.out.println(user);
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (user.getUsername()==null){
            user.setUsername(userOptional.get().getUsername());
        }
        if (user.getPassword()==null){
            user.setPassword(userOptional.get().getPassword());
        }
        if (user.getRoleSet()==null){
            user.setRoleSet(userOptional.get().getRoleSet());
        }
        if (user.getFullName()==null){
            user.setFullName(userOptional.get().getFullName());
        }
        if (user.getDateOfBirth()==null){
            user.setDateOfBirth(userOptional.get().getDateOfBirth());
        }
        if (user.getGender()==null){
            user.setGender(userOptional.get().getGender());
        }
        if (user.getAvatar()==null){
            user.setAvatar(userOptional.get().getAvatar());
        }
        if (user.getBackground()==null){
            user.setBackground(userOptional.get().getBackground());
        }
        if (user.getAddress()==null){
            user.setAddress(userOptional.get().getAddress());
        }
        if (user.getDescription()==null){
            user.setDescription(userOptional.get().getDescription());
        }
        if (user.getPhoneNumber()==null){
            user.setPhoneNumber(userOptional.get().getPhoneNumber());
        }
        user.setId(id);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
