package com.sbszc.demobank.customer;

import com.sbszc.demobank.customer.dto.CustomerDto;
import com.sbszc.demobank.customer.dto.CustomerResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("${root-path.api.v1}/customer")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CustomerResponseDto> findAll() {
        List<CustomerDto> foundCustomers = service.findAll();
        var response = new CustomerResponseDto(foundCustomers, "Found customers");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerResponseDto> findById(@PathVariable Long id) {
        CustomerDto foundCustomer = service.findById(id);
        var response = new CustomerResponseDto(foundCustomer, "Found customer");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> save(@RequestBody @Valid CustomerDto customerDto) {
        CustomerDto savedCustomer = service.save(customerDto);
        var response = new CustomerResponseDto(savedCustomer, "Saved customer");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping
    public ResponseEntity<CustomerResponseDto> update(@RequestBody /*@Valid*/ CustomerDto customerDto) {
        CustomerDto updatedCustomer = service.update(customerDto);
        var response = new CustomerResponseDto(updatedCustomer, "Updated customer");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CustomerResponseDto> deleteById(@PathVariable Long id) {
        CustomerDto deletedCustomer = service.deleteById(id);
        var response = new CustomerResponseDto(deletedCustomer, "Deleted customer");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("ping")
    public String ping() {
        return "ping";
    }
}
