package com.sbszc.demobank.customer;

import com.sbszc.demobank.customer.dto.CustomerDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = {
        @Index(
                name = "UK_customer_identification",
                columnList = "identification",
                unique = true)})
@Data
@NoArgsConstructor
public class Customer {
    @Id
    private Long id;
    @Column(nullable = false)
    private String identification;
    @Column(nullable = false)
    private String name;

    public Customer(CustomerDto customerDto) {
        this.id = customerDto.getId();
        this.identification = customerDto.getIdentification();
        this.name = customerDto.getName();
    }
}
