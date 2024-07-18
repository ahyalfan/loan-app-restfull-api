package com.enigma.bank.service.impl;

import com.enigma.bank.dto.request.LoanTypeCreateRequest;
import com.enigma.bank.dto.request.LoanTypeUpdateRequest;
import com.enigma.bank.dto.response.LoanTypeResponse;
import com.enigma.bank.entity.LoanType;
import com.enigma.bank.repository.LoanRepository;
import com.enigma.bank.repository.LoanTypeRepository;
import com.enigma.bank.service.LoanTypeService;
import com.enigma.bank.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTypeServiceImpl implements LoanTypeService {
    private final LoanTypeRepository loanTypeRepository;
    private final ValidationService validationService;

    @Override
    public LoanTypeResponse createLoan(LoanTypeCreateRequest request) {
        validationService.validate(request);
        LoanType entity = new LoanType();
        entity.setType(request.getType());
        entity.setMaxLoan(request.getMaxLoan());
        return convert(loanTypeRepository.save(entity));
    }

    @Override
    public List<LoanTypeResponse> getAll() {
        return loanTypeRepository.findAll().stream().map(this::convert).toList();
    }

    @Override
    public LoanTypeResponse getById(String id) {
        return loanTypeRepository.findById(id).map(this::convert)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "No instalment types found"));
    }

    @Override
    public void deleteById(String id) {
        getById(id);
        loanTypeRepository.deleteById(id);
    }

    @Override
    public LoanTypeResponse updateById(LoanTypeUpdateRequest request) {
        validationService.validate(request);
        LoanType entity = loanTypeRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "No instalment types found"));
        entity.setType(request.getType());
        entity.setMaxLoan(request.getMaxLoan());
        return convert(loanTypeRepository.save(entity));
    }

    private LoanTypeResponse convert(LoanType entity) {
        return LoanTypeResponse.builder()
                .id(entity.getId())
                .type(entity.getType())
                .maxLoan(entity.getMaxLoan())
                .build();
    }
}
