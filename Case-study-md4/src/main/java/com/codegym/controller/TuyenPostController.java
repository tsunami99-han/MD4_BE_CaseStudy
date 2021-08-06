package com.codegym.controller;

import com.codegym.model.Post;
import com.codegym.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/posts")
public class TuyenPostController {

    @Autowired
    private IPostService postService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> delete(@PathVariable Long id){
        if (!postService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        postService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Post>> searchByAccount(@RequestParam String name){
        Iterable<Post> posts = postService.findAllByAccountContaining("%"+name+"%");
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Post>> findAll(){
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }
}
