package com.enigma.bank.dto.response;


import com.enigma.bank.entity.InstalmentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstalmentTypeResponse {
    private String id;
    private InstalmentType.EInstalmentType instalmentType;
}
