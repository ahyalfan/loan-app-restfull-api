package com.enigma.bank.dto.request;

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
public class UpdateInstalmentTypeRequest {
    @JsonIgnore
    private String id;
    private InstalmentType.EInstalmentType instalmentType;
}
