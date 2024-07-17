package com.enigma.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "m_loan_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String type;
    private Double maxLoan;
    @OneToMany(mappedBy = "loanType")
    private Set<Loan> loans;
}
