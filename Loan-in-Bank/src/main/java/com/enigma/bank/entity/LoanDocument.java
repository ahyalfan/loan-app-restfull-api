package com.enigma.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "m_loan_document")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String contentType;
    private String name;
    private String path;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customers;
}
