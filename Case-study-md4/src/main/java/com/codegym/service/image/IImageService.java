package com.codegym.service.image;

import com.codegym.model.ImagePost;
import com.codegym.service.IGeneralService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageService extends IGeneralService<ImagePost> {
    public String uploadFile(MultipartFile file) throws IOException;

}
