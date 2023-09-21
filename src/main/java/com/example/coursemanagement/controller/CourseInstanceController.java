package com.example.coursemanagement.controller;

import com.example.coursemanagement.service.CourseInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instances")
public class CourseInstanceController {
    private final CourseInstanceService courseInstanceService;

    @PostMapping
    public ResponseEntity createInstance(){

    }
}
