package com.sbszc.demobank.customer.exception;

import com.sbszc.demobank.customer.dto.CustomerResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedList;

@RestControllerAdvice
public class CustomerControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var errors = new LinkedList<String>();
        ex.getAllErrors().forEach(
                objectError -> errors.add(objectError.getDefaultMessage())
        );

        var response = new CustomerResponseDto(null, String.join(", ", errors));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler
    public ResponseEntity<CustomerResponseDto> handle(CustomerNotFoundException exception) {
        var response = new CustomerResponseDto(null, exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler
    public ResponseEntity<CustomerResponseDto> handle(CustomerAlreadyExistsException exception) {
        var response = new CustomerResponseDto(null, exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler
    public ResponseEntity<CustomerResponseDto> handle(Exception exception) {
        var response = new CustomerResponseDto(null, exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
