package com.enigma.bank.service;

import com.enigma.bank.dto.request.CustomerCreateRequest;
import com.enigma.bank.dto.request.CustomerUpdateRequest;
import com.enigma.bank.dto.response.CustomerResponse;
import com.enigma.bank.entity.User;

import java.util.List;

public interface CustomerService {
    CustomerResponse create(User user, CustomerCreateRequest request);
    CustomerResponse update(User user, CustomerUpdateRequest request);
    CustomerResponse getById(User user, String id);
    void deleteById(User user, String id);
    List<CustomerResponse> getAll(User user);

}
