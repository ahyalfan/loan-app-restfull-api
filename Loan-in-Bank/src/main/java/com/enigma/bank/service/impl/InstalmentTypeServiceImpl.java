package com.enigma.bank.service.impl;

import com.enigma.bank.dto.request.CreateInstalmentTypeRequest;
import com.enigma.bank.dto.request.UpdateInstalmentTypeRequest;
import com.enigma.bank.dto.response.InstalmentTypeResponse;
import com.enigma.bank.entity.InstalmentType;
import com.enigma.bank.repository.InstalmentTypeRepository;
import com.enigma.bank.service.InstalmentTypeService;
import com.enigma.bank.service.ValidationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstalmentTypeServiceImpl implements InstalmentTypeService {
    private final InstalmentTypeRepository instalmentTypeRepository;
    private final ValidationService validationService;

    @Override
    public InstalmentTypeResponse createInst(CreateInstalmentTypeRequest request) {
        validationService.validate(request);
        // Create and return the new InstalmentTypeResponse
        InstalmentType entity = new InstalmentType();
        entity.setInstalmentType(request.getInstalmentType());
        return convert(instalmentTypeRepository.save(entity));
    }

    @Override
    public List<InstalmentTypeResponse> getAll() {
        List<InstalmentType> instalmentTypes = instalmentTypeRepository.findAll();
        log.info("Instalment tests found start");
        if (instalmentTypes.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "No instalment types found");
        }
        return instalmentTypes.stream().map(this::convert).toList();
    }

    @Override
    public InstalmentTypeResponse getById(String id) {
        var instalmentType = instalmentTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "No instalment types found"));
        return convert(instalmentType);
    }

    @Override
    public void deleteById(String id) {
        instalmentTypeRepository.deleteById(id);
    }

    @Override
    public InstalmentTypeResponse updateById(UpdateInstalmentTypeRequest request) {
        validationService.validate(request);
        InstalmentType entity = instalmentTypeRepository.findById(request.getId()).orElseThrow();

        entity.setInstalmentType(request.getInstalmentType());
        return convert(instalmentTypeRepository.save(entity));
    }

    private InstalmentTypeResponse convert(InstalmentType request) {
        return InstalmentTypeResponse.builder()
                .id(request.getId())
                .instalmentType(request.getInstalmentType())
                .build();
    }
}
