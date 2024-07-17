package com.enigma.bank.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
@Builder
public class WebResponse<T> {
    private HttpStatusCode statusCode;
    private String message;
    private T data;
}
