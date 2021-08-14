package com.codegym.service.image;

import com.codegym.model.ImagePost;
import com.codegym.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ImageService implements IImageService{
    @Autowired
    private IImageRepository imageRepository;

    @Override
    public Iterable<ImagePost> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Optional<ImagePost> findById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public ImagePost save(ImagePost imagePost) {
        return imageRepository.save(imagePost);
    }

    @Override
    public void remove(Long id) {
        imageRepository.deleteById(id);
    }
}
