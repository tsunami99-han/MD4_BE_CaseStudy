package com.codegym.controller;

import com.codegym.dto.PostDto;
import com.codegym.model.ImagePost;
import com.codegym.model.Post;
import com.codegym.model.Relationship;
import com.codegym.service.ImagePost.IImagePost;
import com.codegym.service.post.IPostService;
import com.codegym.service.relationship.IRelationshipService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("/relationshipss")
@CrossOrigin("*")
public class ControllerRela_LUC {

    private String uploadFile= "F:\\Bài Tập\\MD4_FE_CaseStudy\\Case-study-md4-wt\\image" ;

    @Autowired
    private IRelationshipService relationshipService;

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;


    @Autowired
    private IImagePost iImagePost;

    @PutMapping("/{id}")
    public ResponseEntity<Relationship> blockRelationship(@PathVariable Long id, @RequestParam("status") String status){
        Optional<Relationship> relationshipOptional = relationshipService.findById(id);
        if (!relationshipOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            relationshipOptional.get().setName(status);
            relationshipService.save(relationshipOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/findAllPost")
    public ResponseEntity<Iterable<Post>> findAll(){
        Iterable<Post> findAll= postService.findAll();
        return new ResponseEntity<>(findAll,HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Post> updateProduct(@PathVariable Long id,@RequestBody Post post){

        Optional<Post> products = postService.findById(id);
        if (!products.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            post.setId(id);
            postService.save(post);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    //upload file
    @PostMapping("/create-post")
    public ResponseEntity<?> createPost(PostDto postDto){
        Post post1 = new Post();
        post1.setContent(postDto.getContent());
        post1.setStatus(postDto.getStatus());
        post1.setUser(userService.findById(postDto.getUserId()).get());
        post1.setTime(LocalDateTime.now());
        Post post = postService.save(post1);
        //upload File
        MultipartFile image = postDto.getImage();
        String fileName = image.getOriginalFilename();
        try {
            FileCopyUtils.copy(image.getBytes(),
                    new File(uploadFile + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ImagePost imagePost = new ImagePost(fileName,post);
        iImagePost.save(imagePost);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
