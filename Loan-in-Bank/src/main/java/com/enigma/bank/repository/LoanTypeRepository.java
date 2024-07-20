package com.enigma.bank.repository;

import com.enigma.bank.entity.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanTypeRepository extends JpaRepository<LoanType, String> {
}