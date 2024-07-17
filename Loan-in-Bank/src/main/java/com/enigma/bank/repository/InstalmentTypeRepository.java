package com.enigma.bank.repository;

import com.enigma.bank.entity.InstalmentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstalmentTypeRepository extends JpaRepository<InstalmentType, String> {
}