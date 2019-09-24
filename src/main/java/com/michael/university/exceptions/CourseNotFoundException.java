package com.michael.university.exceptions;

/**
 * Created by Michael on 07/06/2016.
 */
public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(Long courseId) {
        super("could not find course with " + courseId + "id.");;
    }
}
