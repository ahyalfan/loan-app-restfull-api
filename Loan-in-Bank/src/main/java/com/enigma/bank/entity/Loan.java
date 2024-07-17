package com.enigma.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "m_loan_transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;
    private Long approvedAt;
    private String approvedBy;
    private Long createdAt;
    private Double nominal;
    private String rejectedAt;
    private Long updatedAt;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customers;
    @ManyToOne
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;
    @ManyToOne
    @JoinColumn(name = "instalment_type_id")
    private InstalmentType instalmentType;
    @OneToMany(mappedBy = "loans")
    private Set<LoanTransactionDetails> loanTransactionDetails;

    public enum ApprovalStatus {
        APPROVED,
        REJECTED
    }
}
