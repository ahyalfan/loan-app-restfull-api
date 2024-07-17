package com.enigma.bank.service;

import com.enigma.bank.dto.response.UploadResponse;
import com.enigma.bank.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String storeFile(MultipartFile file, User user);
    byte[] loadFileAsBytes(String fileName);
}
