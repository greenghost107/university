package com.michael.university.exceptions;

/**
 * Created by Michael on 07/06/2016.
 */
@SuppressWarnings("serial")
public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(Long departmentID) {
        super("could not find department with " + departmentID + "id.");
    }
}
