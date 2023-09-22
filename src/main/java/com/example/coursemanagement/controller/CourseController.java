package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.request.CourseRequest;
import com.example.coursemanagement.dto.response.CourseResponse;
import com.example.coursemanagement.exception.CourseNotFoundException;
import com.example.coursemanagement.exception.DuplicateCourseCodeException;
import com.example.coursemanagement.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {
    //Controller for course related endpoints

    private final CourseService courseService; //DI of CourseService

    @PostMapping
    public ResponseEntity createCourse(@RequestBody CourseRequest courseRequest){
        try{
            String response = courseService.createCourse(courseRequest);
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch (DuplicateCourseCodeException e){
            //Handle duplicate course code exception
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            //Handle other exceptions
            return new ResponseEntity("An error occurred while creating course!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping
    public List<CourseResponse> getAllCourses(){
        //Retrive a list of all courses
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity getCourseById(@PathVariable("id") int id){
        //Retrieve a course by its ID
        try{
            CourseResponse response = courseService.getCourseById(id);
            return new ResponseEntity(response, HttpStatus.FOUND);
        } catch (CourseNotFoundException e){
            //Handle course not found exception
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable("id") int id){
        //Delete a course by its Id
        try{
            String response = courseService.deleteCourse(id);
            return new ResponseEntity(response,HttpStatus.OK);
        }catch (CourseNotFoundException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
