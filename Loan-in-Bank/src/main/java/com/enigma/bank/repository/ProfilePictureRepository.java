package com.enigma.bank.repository;

import com.enigma.bank.entity.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, String> {
}