package com.codegym.service.image;

import com.codegym.model.ImagePost;
import com.codegym.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService implements IImageService {
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
    public String uploadFile(MultipartFile file) throws IOException {
        return copy(file);
    }

    @Override
    public void remove(Long id) {
        imageRepository.deleteById(id);
    }

    public String copy(MultipartFile file) throws IOException {
        String folderName = "/Users/thaodangxuan/Module4-CaseStudy/Intellij/Case-study-md4/src/main/resources/image";
        String imageName = file.getOriginalFilename();
        File file1 = new File(folderName + imageName);
        FileCopyUtils.copy(file.getBytes(), file1);
        return file1.getPath();
    }
}
