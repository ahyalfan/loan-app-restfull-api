package com.enigma.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "m_customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    @Temporal(TemporalType.TIMESTAMP)
    private Date birth_day;
    private String status;
    private boolean deleted;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "customers")
    private Set<LoanDocument> loanDocuments;
    @OneToMany(mappedBy = "customers")
    private Set<Loan> loans;
}
