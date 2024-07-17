package com.enigma.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "m_profile_picture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfilePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String contentType;
    private String imageUrl;
    private Long size;
    @OneToOne
    @JoinColumn(name = "id")
    private User user;
}
