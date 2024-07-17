package com.enigma.bank.repository;

import com.enigma.bank.entity.LoanDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDocumentRepository extends JpaRepository<LoanDocument, String> {
}