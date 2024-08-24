package com.plinfotech.spring_boot_crud_employeeManagement.handler;

import com.plinfotech.spring_boot_crud_employeeManagement.exception.EmployeeNotFoudException;
import com.plinfotech.spring_boot_crud_employeeManagement.exception.UniqueConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class EmployeeExceptionHandler {

    @ExceptionHandler(EmployeeNotFoudException.class)
    public ProblemDetail employeeNotFoundException(EmployeeNotFoudException ex){
      log.info("employeeNotFoundException");
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> validateProductFields(MethodArgumentNotValidException ex){
        log.info("validateProductFields");
        Map<String, String> mapFields= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            mapFields.put(error.getField(), error.getDefaultMessage());
        });
        return mapFields;
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        log.info("handleHttpMessageNotReadableException");

        Map<String, String> response = new HashMap<>();

        Throwable mostSpecificCause = ex.getMostSpecificCause();
        if (mostSpecificCause instanceof DateTimeParseException) {
            DateTimeParseException dateTimeParseException = (DateTimeParseException) mostSpecificCause;
            response.put("error", "Invalid date format");
            response.put("details", dateTimeParseException.getMessage());
        } else {
            response.put("error", "Malformed JSON request");
            response.put("details", ex.getMessage());
        }

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UniqueConstraintViolationException.class)
    public ResponseEntity<String> handleUniqueConstraintViolation(UniqueConstraintViolationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
