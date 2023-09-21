package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.request.CourseRequest;
import com.example.coursemanagement.dto.response.CourseResponse;
import com.example.coursemanagement.exception.CourseNotFoundException;
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
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity createCourse(@RequestBody CourseRequest courseRequest){
        String response = courseService.createCourse(courseRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<CourseResponse> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity getCourseById(@PathVariable("id") int id){
        try{
            CourseResponse response = courseService.getCourseById(id);
            return new ResponseEntity(response, HttpStatus.FOUND);
        } catch (CourseNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable("id") int id){
        try{
            String response = courseService.deleteCourse(id);
            return new ResponseEntity(response,HttpStatus.OK);
        }catch (CourseNotFoundException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
