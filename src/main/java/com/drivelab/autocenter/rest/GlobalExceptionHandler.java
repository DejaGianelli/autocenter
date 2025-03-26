package com.drivelab.autocenter.rest;

import com.drivelab.autocenter.domain.DomainException;
import com.drivelab.autocenter.domain.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ProblemDetails> domainResponse(DomainException ex) {
        ProblemDetails problemDetails = new ProblemDetails(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetails);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetails> entityNotFoundResponse(EntityNotFoundException ex) {
        ProblemDetails problemDetails = new ProblemDetails(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetails);
    }
}
