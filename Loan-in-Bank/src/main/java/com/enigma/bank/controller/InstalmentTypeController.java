package com.enigma.bank.controller;

import com.enigma.bank.constant.ApiUrl;
import com.enigma.bank.dto.request.CreateInstalmentTypeRequest;
import com.enigma.bank.dto.request.UpdateInstalmentTypeRequest;
import com.enigma.bank.dto.response.InstalmentTypeResponse;
import com.enigma.bank.dto.response.WebResponse;
import com.enigma.bank.service.InstalmentTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/instalment-types")
@SecurityRequirement(name = "Authorization")
@RequiredArgsConstructor
// TODO: Implement the functionality for managing instalment types here
public class InstalmentTypeController {
    private final InstalmentTypeService instalmentTypeService;
    // Endpoint for managing instalment types
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<InstalmentTypeResponse> createInstalmentType(@RequestBody CreateInstalmentTypeRequest request) {
        // Implement create logic for InstalmentType
        var instalmentTypeResponse = instalmentTypeService.createInst(request);
        return WebResponse.<InstalmentTypeResponse>builder()
                .statusCode(HttpStatusCode.valueOf(201))
                .message("created successfully")
                .data(instalmentTypeResponse)
                .build();
    }

    @GetMapping(
            path = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<InstalmentTypeResponse> get(@PathVariable String id) {
        // Implement get all logic for InstalmentType
        var response = instalmentTypeService.getById(id);
        return WebResponse.<InstalmentTypeResponse>builder()
               .statusCode(HttpStatusCode.valueOf(200))
                .message("get successfully")
               .data(response)
               .build();
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<InstalmentTypeResponse>> getAll(){
        log.info("getAll started");
        var instalmentTypeResponses = instalmentTypeService.getAll();
        return WebResponse.<List<InstalmentTypeResponse>>builder()
                .statusCode(HttpStatusCode.valueOf(200))
                .message("get all success")
                .data(instalmentTypeResponses)
                .build();
    }

    @PutMapping(
            path = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<InstalmentTypeResponse> update(@PathVariable String id, @RequestBody UpdateInstalmentTypeRequest request){
        // Implement update logic for InstalmentType
        request.setId(id);
        var response = instalmentTypeService.updateById(request);
        return WebResponse.<InstalmentTypeResponse>builder()
               .statusCode(HttpStatusCode.valueOf(200))
               .message("updated successfully")
               .data(response)
               .build();
    }

    @DeleteMapping(
            path = "{id}"
    )
    public WebResponse<String> delete(@PathVariable String id){
        // Implement delete logic for InstalmentType
        instalmentTypeService.deleteById(id);
        return WebResponse.<String>builder()
               .statusCode(HttpStatusCode.valueOf(200))
               .message("deleted successfully")
               .data("OK")
               .build();
    }

}
