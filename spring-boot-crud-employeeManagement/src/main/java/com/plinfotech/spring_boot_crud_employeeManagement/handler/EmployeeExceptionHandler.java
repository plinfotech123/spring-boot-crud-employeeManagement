package com.plinfotech.spring_boot_crud_employeeManagement.handler;

import com.plinfotech.spring_boot_crud_employeeManagement.exception.EmployeeNotFoudException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(EmployeeNotFoudException.class)
    public ProblemDetail employeeNotFOundException(EmployeeNotFoudException ex){

        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
    }
}
