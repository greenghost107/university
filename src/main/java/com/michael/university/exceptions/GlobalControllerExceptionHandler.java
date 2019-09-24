package com.michael.university.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Michael on 07/06/2016.
 */
@ControllerAdvice
class GlobalControllerExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DepartmentNotFoundException.class)
    public void departmentHandle() {
        // Nothing to do
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 402
    @ExceptionHandler(ArithmeticException.class)
    public void mathHandle() {
        // Nothing to do
    }


    @ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(StudentNotFoundException.class)
    public void studentHandle() {
        // Nothing to do
    }


    @ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(CourseNotFoundException.class)
    public void courseHandle() {
        // Nothing to do
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Exception.class)
    public void genericExceptionhandler()
    {

    }
}
