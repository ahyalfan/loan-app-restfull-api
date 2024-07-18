package com.enigma.bank.dto.request;

import com.enigma.bank.entity.InstalmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInstalmentTypeRequest {
    @NotNull
    private InstalmentType.EInstalmentType instalmentType;
}
