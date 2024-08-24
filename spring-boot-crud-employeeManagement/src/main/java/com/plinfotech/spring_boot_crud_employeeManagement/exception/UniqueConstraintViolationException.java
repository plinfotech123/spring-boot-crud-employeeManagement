package com.plinfotech.spring_boot_crud_employeeManagement.exception;

public class UniqueConstraintViolationException extends RuntimeException{
    public UniqueConstraintViolationException(String message){
        super(message);
    }
}
