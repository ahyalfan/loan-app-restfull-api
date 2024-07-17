package com.enigma.bank.repository;

import com.enigma.bank.entity.Customer;
import com.enigma.bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,String> {

    Optional<Customer> findFirstByUserAndId(User user, String id);

    List<Customer> findAllByUser(User user);
}
