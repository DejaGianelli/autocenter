package com.drivelab.autocenter.rest;

import com.drivelab.autocenter.domain.DomainException;
import com.drivelab.autocenter.domain.EntityNotFoundException;
import com.drivelab.autocenter.rest.ProblemDetails.InvalidParam;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final ObjectMapper objectMapper;
    private final MessageSource messageSource;

    public GlobalExceptionHandler(ObjectMapper objectMapper,
                                  MessageSource messageSource) {
        this.objectMapper = objectMapper;
        this.messageSource = messageSource;
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ProblemDetails> domainResponse(DomainException ex) {
        ProblemDetails problemDetails = new ProblemDetails(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problemDetails);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetails> entityNotFoundResponse(EntityNotFoundException ex) {
        ProblemDetails problemDetails = new ProblemDetails(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problemDetails);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetails> exceptionResponse(Exception ex) {
        ProblemDetails problemDetails = new ProblemDetails("An unexpected error occurred. Contact the system administrator");
        logger.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problemDetails);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetails> methodArgumentNotValidResponse(MethodArgumentNotValidException ex) {
        ProblemDetails problemDetails = new ProblemDetails("There are invalid input sent in the request");
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String code = error.getDefaultMessage() == null ? "invalid-param" : error.getDefaultMessage();
            String localizedMessage = messageSource.getMessage(code, error.getArguments(), Locale.getDefault());
            problemDetails.add(new InvalidParam(error.getField(), localizedMessage)
                    .setRejectedValue(error.getRejectedValue()));
        }
        logger.info(serializedProblemDetails(problemDetails));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problemDetails);
    }

    private String serializedProblemDetails(ProblemDetails problemDetails) {
        try {
            return objectMapper.writeValueAsString(problemDetails);
        } catch (IOException ex) {
            logger.error("Problem when serializing problem details", ex);
            throw new RuntimeJsonMappingException(JsonMappingException.fromUnexpectedIOE(ex));
        }
    }
}
