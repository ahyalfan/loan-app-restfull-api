package com.enigma.bank.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UploadResponse {
    private String fileName;
    private String url;
}
