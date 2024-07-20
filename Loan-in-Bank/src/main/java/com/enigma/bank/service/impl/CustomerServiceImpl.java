package com.enigma.bank.service.impl;

import com.enigma.bank.dto.request.CustomerCreateRequest;
import com.enigma.bank.dto.request.CustomerUpdateRequest;
import com.enigma.bank.dto.response.CustomerResponse;
import com.enigma.bank.entity.Customer;
import com.enigma.bank.entity.User;
import com.enigma.bank.repository.CustomerRepository;
import com.enigma.bank.service.CustomerService;
import com.enigma.bank.service.ValidationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final ValidationService validationService;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerResponse create(User user, CustomerCreateRequest request) {
        validationService.validate(request);
        log.info("create customer");

        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhone(request.getPhoneNumber());
        customer.setBirth_day(request.getBirthDate());
        customer.setStatus(request.getStatus());
        customer.setUser(user);
        customerRepository.save(customer);
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .birthDate(customer.getBirth_day())
                .status(customer.getStatus())
                .build();
    }

    @Override
    @Transactional
    public CustomerResponse update(User user, CustomerUpdateRequest request) {
        validationService.validate(request);
        Customer customer = customerRepository.findFirstByUserAndId(user,request.getId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatusCode.valueOf(400), "Customer not found"));
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhone(request.getPhoneNumber());
        customer.setBirth_day(request.getBirthDate());
        customer.setStatus(request.getStatus());
        customerRepository.save(customer);

        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .birthDate(customer.getBirth_day())
                .status(customer.getStatus())
                .build();
    }

    @Override
    public CustomerResponse getById(User user,String id) {
        Customer customer = customerRepository.findFirstByUserAndId(user,id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatusCode.valueOf(400),"Could not find"));
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .birthDate(customer.getBirth_day())
                .status(customer.getStatus())
                .build();
    }

    @Override
    public void deleteById(User user,String id) {
        Customer customer = customerRepository.findFirstByUserAndId(user,id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatusCode.valueOf(400),"Could not find"));
        customer.setDeleted(true);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerResponse> getAll(User user) {
        List<Customer> customers = customerRepository.findAllByUser(user);
        return customers.stream()
                .map(customer -> CustomerResponse.builder()
                        .id(customer.getId())
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .phone(customer.getPhone())
                        .birthDate(customer.getBirth_day())
                        .status(customer.getStatus())
                        .build())
                .collect(Collectors.toList());
    }
}
