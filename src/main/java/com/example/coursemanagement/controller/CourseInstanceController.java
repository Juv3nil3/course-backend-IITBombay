package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.request.CourseInstanceRequest;
import com.example.coursemanagement.enums.Semester;
import com.example.coursemanagement.exception.CourseNotFoundException;
import com.example.coursemanagement.model.CourseInstance;
import com.example.coursemanagement.service.CourseInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instances")
public class CourseInstanceController {
    private final CourseInstanceService courseInstanceService;

    @PostMapping
    public ResponseEntity createInstance(@RequestBody CourseInstanceRequest courseInstanceRequest){
        String response = courseInstanceService.createInstance(courseInstanceRequest);
        return new ResponseEntity(response,HttpStatus.OK);

    }
}
