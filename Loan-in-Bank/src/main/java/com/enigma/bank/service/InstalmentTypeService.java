package com.enigma.bank.service;

import com.enigma.bank.dto.request.CreateInstalmentTypeRequest;
import com.enigma.bank.dto.request.UpdateInstalmentTypeRequest;
import com.enigma.bank.dto.response.InstalmentTypeResponse;
import com.enigma.bank.entity.User;

import java.util.List;

public interface InstalmentTypeService {
    InstalmentTypeResponse createInst(CreateInstalmentTypeRequest request);
     List<InstalmentTypeResponse> getAll();
     InstalmentTypeResponse getById(String id);
     void deleteById(String id);
     InstalmentTypeResponse updateById(UpdateInstalmentTypeRequest request);
}
