package com.codegym.controller;

import com.codegym.model.ImagePost;
import com.codegym.service.image.IImageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@CrossOrigin("*")
public class ThaoImageController {
    @Autowired
    IImageService imageService;

    @PostMapping("/create-image")
    public ResponseEntity<?> image(@RequestBody ImagePost post) throws JsonProcessingException {
        imageService.save(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PostMapping("/upload-file")
    public ResponseEntity<?> postImage(MultipartFile file) throws IOException {
        return new ResponseEntity<>(imageService.uploadFile(file), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id) {
        imageService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
