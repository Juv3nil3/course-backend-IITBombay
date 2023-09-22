package com.example.coursemanagement.exception;

public class CourseInstanceNotFoundException extends RuntimeException{
    public CourseInstanceNotFoundException(String message){
        super(message);
    }
}
