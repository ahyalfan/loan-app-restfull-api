package com.enigma.bank.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String status;
}
