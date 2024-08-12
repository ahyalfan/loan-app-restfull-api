package com.enigma.bank.service.impl;

import com.enigma.bank.dto.request.TransactionLoanApprovedRequest;
import com.enigma.bank.dto.request.TransactionLoanCreateRequest;
import com.enigma.bank.dto.response.TransactionLoanDetailsResponse;
import com.enigma.bank.dto.response.TransactionLoanResponse;
import com.enigma.bank.entity.*;
import com.enigma.bank.repository.*;
import com.enigma.bank.service.InstalmentTypeService;
import com.enigma.bank.service.LoanTypeService;
import com.enigma.bank.service.TransactionLoanService;
import com.enigma.bank.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TransactionLoanServiceImpl implements TransactionLoanService {
    private final ValidationService validationService;
    private final LoanRepository loanRepository;
    private final LoanTypeRepository loanTypeRepository;
    private final InstalmentTypeRepository instalmentTypeRepository;
    private final CustomerRepository customerRepository;
    private final LoanTransactionDetailsRepository loanTransactionDetailsRepository;
    private final UserRepository userRepository;


    @Override
    public TransactionLoanResponse create(User user, TransactionLoanCreateRequest createRequest) {
        validationService.validate(createRequest);
        var loanType = loanTypeRepository.findById(createRequest.getLoanTypeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Could not find loan type"));
        var instalmentType = instalmentTypeRepository.findById(createRequest.getInstalmentTypeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Could not find instalment type"));
        var customerId = customerRepository.findById(createRequest.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Could not find customer"));
        // Implement your transaction logic here
        Loan loan = new Loan();
        loan.setCreatedAt(new Date().getTime());
        loan.setNominal(createRequest.getAmount());
        loan.setLoanType(loanType);
        loan.setInstalmentType(instalmentType);
        loan.setCustomers(customerId);
        loanRepository.save(loan);
        LoanTransactionDetails loanTransactionDetails = new LoanTransactionDetails();
        loanTransactionDetails.setNominal(createRequest.getTransactionDetail().getAmount());
        loanTransactionDetails.setLoans(loan);
        loanTransactionDetails.setLoanStatus(createRequest.getTransactionDetail().getLoan_status());
        loanTransactionDetails.setCreatedAt(new Date().getTime());

        return convert(loan, loanTransactionDetails);
    }

    @Override
    public TransactionLoanResponse getById(String id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Could not find loan type"));
        LoanTransactionDetails loanTransactionDetails = loanTransactionDetailsRepository.findFirstByLoans(loan)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Could not find loan type"));
        return convert(loan, loanTransactionDetails);
    }

    @Override
    public TransactionLoanResponse approved(User user, TransactionLoanApprovedRequest request) {
        validationService.validate(request);
        userRepository.findById(request.getAdminId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(403), "access danienied"));
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals(Role.ERole.ROLE_ADMIN))){
            Loan loan = loanRepository.findById(request.getLoanId())
                   .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Could not find loan type"));
            LoanTransactionDetails loanTransactionDetails = loanTransactionDetailsRepository.findFirstByLoans(loan)
                   .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Could not find loan type"));
            loan.setApprovedBy(user.getEmail());
            loanTransactionDetails.setNominal(loanTransactionDetails.getNominal() + (loanTransactionDetails.getNominal() * request.getInterestRates()));
            loan.setUpdatedAt(new Date().getTime());
            loanRepository.save(loan);
            loanTransactionDetails.setTransactionDate(new Date().getTime());
            loanTransactionDetails.setUpdatedAt(new Date().getTime());
            loanTransactionDetailsRepository.save(loanTransactionDetails);
            return convert(loan, loanTransactionDetails);
        }
        return null;
    }

    private TransactionLoanResponse convert(Loan loan, LoanTransactionDetails loanDetails) {
        return TransactionLoanResponse.builder()
                .id(loan.getId())
                .amount(loan.getNominal())
                .loanTypeId(loan.getLoanType().getId())
                .instalmentTypeId(loan.getInstalmentType().getId())
                .customerId(loan.getCustomers().getId())
                .transactionLoanDetailsResponse(
                        TransactionLoanDetailsResponse.builder()
                                .amount(loanDetails.getNominal())
                                .id(loanDetails.getId())
                                .createdAt(loanDetails.getCreatedAt())
                                .build()
                )
                .build();
    }
}
