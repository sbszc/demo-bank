package com.sbszc.demobank.customer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CustomerResponseDto(
        Object data,
        String message) {
}
