package com.enigma.bank.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "m_guarantee_picture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuaranteePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String contentType;
    private String name;
    private String path;
    private Long size;
    @OneToMany(mappedBy = "guaranteePicture")
    private Set<LoanTransactionDetails> loans;
}
