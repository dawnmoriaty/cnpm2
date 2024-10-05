package com.example.demo2.services;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String uploadImage(MultipartFile file);
}
