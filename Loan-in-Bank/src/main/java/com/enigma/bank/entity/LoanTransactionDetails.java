package com.enigma.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "m_loan_transaction_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanTransactionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Long transactionDate;
    private Double nominal;
    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loans;
    private LoanStatus loanStatus; // enum
    private Long createdAt;
    private Long updatedAt;
//    @OneToOne
//    @JoinColumn(name = "guarantee_picture_id")
//    private GuaranteePicture guaranteePicture;

    public enum LoanStatus {
        PAID,
        UNPAID
    }
}
