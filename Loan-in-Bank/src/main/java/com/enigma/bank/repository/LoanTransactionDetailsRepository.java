package com.enigma.bank.repository;

import com.enigma.bank.entity.Loan;
import com.enigma.bank.entity.LoanTransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanTransactionDetailsRepository extends JpaRepository<LoanTransactionDetails, String> {
    Optional<LoanTransactionDetails> findFirstByLoans(Loan loan);
}