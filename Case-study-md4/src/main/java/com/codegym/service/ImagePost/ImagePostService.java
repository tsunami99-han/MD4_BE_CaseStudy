package com.codegym.service.ImagePost;

import com.codegym.model.ImagePost;
import com.codegym.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImagePostService implements IImagePost{

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
