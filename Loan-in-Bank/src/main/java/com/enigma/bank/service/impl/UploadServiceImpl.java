package com.enigma.bank.service.impl;

import com.enigma.bank.dto.response.UploadResponse;
import com.enigma.bank.entity.ProfilePicture;
import com.enigma.bank.entity.User;
import com.enigma.bank.repository.ProfilePictureRepository;
import com.enigma.bank.service.UploadService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;


@Service
//@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {
    private final Path fileStorageLocation;

    private final ProfilePictureRepository profilePictureRepository;
    @Autowired
    public UploadServiceImpl(ProfilePictureRepository profilePictureRepository) {
        this.profilePictureRepository = profilePictureRepository;
        this.fileStorageLocation = Path.of("assets/images/");
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public String storeFile(MultipartFile file, User user) {

        String fileName = Objects.requireNonNull(file.getOriginalFilename());
        String idFileName = user.getId() + "_" + fileName;

        ProfilePicture profilePicture = new ProfilePicture();
        profilePicture.setContentType(file.getContentType());
        profilePicture.setName(idFileName);
        profilePicture.setImageUrl(idFileName);
        profilePicture.setSize(file.getSize());
        profilePicture.setUser(user);
        profilePictureRepository.save(profilePicture);

        try {
            Path targetLocation = fileStorageLocation.resolve(idFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return idFileName;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] loadFileAsBytes(String fileName) {
        Path filePath = fileStorageLocation.resolve(fileName);
        if (!Files.exists(filePath)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),fileName+" not found");
        }
        try {
            //            return Files.readAllBytes(filePath); // ini jika langusng tnapa di oalh
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Thumbnails.of(filePath.toFile())
                    .size(100, 100)
                    .outputFormat("jpg")
                    .toOutputStream(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(500),e.getMessage()+" not found");
        }
    }
}
