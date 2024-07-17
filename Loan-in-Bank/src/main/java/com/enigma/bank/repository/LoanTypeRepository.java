package com.enigma.bank.repository;

import com.enigma.bank.entity.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTypeRepository extends JpaRepository<LoanType, String> {
}