package com.sbszc.demobank.customer.dto;

import com.sbszc.demobank.customer.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto {
    @NotNull(message = "id is required")
    private Long id;
    @NotBlank(message = "identification is required")
    private String identification;
    @NotBlank(message = "name is required")
    private String name;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.identification = customer.getIdentification();
        this.name = customer.getName();
    }
}
