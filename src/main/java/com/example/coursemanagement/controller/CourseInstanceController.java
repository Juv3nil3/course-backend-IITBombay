package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.request.CourseInstanceRequest;
import com.example.coursemanagement.dto.response.CourseInstanceResponse;
import com.example.coursemanagement.service.CourseInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instances")
public class CourseInstanceController {
    private final CourseInstanceService courseInstanceService;

    @PostMapping
    public ResponseEntity createInstance(@RequestBody CourseInstanceRequest courseInstanceRequest){
        CourseInstanceResponse response = courseInstanceService.createInstance(courseInstanceRequest);
        return new ResponseEntity(response,HttpStatus.CREATED);

    }

    @GetMapping("/{year}/{semester}")
    public ResponseEntity getInstanceByYearAndSemester(@PathVariable("year") String year, @PathVariable("semester") int semester){
        List<CourseInstanceResponse> response = courseInstanceService.getInstanceByYearAndSemester(year,semester);
        return new ResponseEntity(response,HttpStatus.FOUND);
    }

    @GetMapping("/{year}/{sem}/{id}")
    public ResponseEntity getDetailedInstance(@PathVariable("year") String year, @PathVariable("sem") int semester,
                                              @PathVariable("id") int courseId){
        CourseInstanceResponse response = courseInstanceService.getDetailedInstance(year,semester,courseId);
    }
}
