package com.enigma.bank.repository;

import com.enigma.bank.entity.LoanTransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTransactionDetailsRepository extends JpaRepository<LoanTransactionDetails, String> {
}