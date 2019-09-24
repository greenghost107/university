package com.michael.university.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Michael on 07/06/2016.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Student")
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long studentId) {
        super("could not find user with " + studentId + "id.");
    }
}
