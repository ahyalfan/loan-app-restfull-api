package com.enigma.bank.controller;

import com.enigma.bank.constant.ApiUrl;
import com.enigma.bank.dto.response.UploadResponse;
import com.enigma.bank.dto.response.WebResponse;
import com.enigma.bank.entity.User;
import com.enigma.bank.service.UploadService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping(path = ApiUrl.UPLOAD_API)
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class UploadController {

    private final UploadService uploadService;

    @PostMapping("/avatar")
    public ResponseEntity<WebResponse<UploadResponse>> uploadAvatar(@RequestParam("avatar") MultipartFile avatar, User user) {
        String fileName = uploadService.storeFile(avatar, user);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ApiUrl.CUSTOMER_API)
                .path("/avatar/")
                .path(fileName)
                .toUriString();

        UploadResponse avatarResponse = UploadResponse.builder()
                .url(fileDownloadUri)
                .fileName(fileName)
                .build();

        WebResponse<UploadResponse> commonResponse = WebResponse.<UploadResponse>builder()
                .message("File uploaded successfully")
                .statusCode(HttpStatusCode.valueOf(200))
                .data(avatarResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @GetMapping("/avatar/{filename:.+}")
    public ResponseEntity<byte[]> getAvatar(@PathVariable String filename) {
        byte[] bytes = uploadService.loadFileAsBytes(filename);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }
}
