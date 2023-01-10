package com.sbszc.demobank.customer;

import com.sbszc.demobank.customer.dto.CustomerDto;
import com.sbszc.demobank.customer.exception.CustomerAlreadyExistsException;
import com.sbszc.demobank.customer.exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<CustomerDto> findAll() {
        return repository.findAll().stream()
                .map(CustomerDto::new)
                .toList();
    }

    public CustomerDto findById(Long id) {
        return repository.findById(id)
                .map(CustomerDto::new)
                .orElseThrow(
                        () -> new CustomerNotFoundException(String.format("Customer with id:'%d' was not found", id))
                );
    }

    public CustomerDto save(CustomerDto customerDto) {
        repository.findById(customerDto.getId())
                .ifPresent(customer -> {
                    throw new CustomerAlreadyExistsException(String.format("Customer with id:'%d' already exists", customer.getId()));
                });

        Customer savedCustomer = repository.save(new Customer(customerDto));
        return new CustomerDto(savedCustomer);
    }

    public CustomerDto update(CustomerDto customerDto) {
        return repository.findById(customerDto.getId())
                .map(customer -> {
                    Customer savedCustomer = repository.save(new Customer(customerDto));
                    return new CustomerDto(savedCustomer);
                })
                .orElseThrow(
                        () -> new CustomerNotFoundException(String.format("Customer with id:'%d' was not found", customerDto.getId()))
                );
    }

    public CustomerDto deleteById(Long id) {
        return repository.findById(id)
                .map(customer -> {
                    repository.deleteById(id);
                    return new CustomerDto(customer);
                })
                .orElseThrow(
                        () -> new CustomerNotFoundException(String.format("Customer with id:'%d' was not found", id))
                );
    }
}
