package com.nikhillab.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nikhillab.service.ImageService;

@Service
@Primary
public class LocalImageService implements ImageService {

    private final String LOCAL_PATH = "src/main/resources/static/profile/";

    @Override
    public String uploadImage(MultipartFile contactImage, String filename) throws IOException {
        String fileName = filename + contactImage.getOriginalFilename();
        String filePath = LOCAL_PATH + fileName;

        // Copies Spring's multipartfile inputStream to absolute path
        Files.copy(contactImage.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        return filePath;

    }

}
