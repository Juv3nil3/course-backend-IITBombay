package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.request.CourseInstanceRequest;
import com.example.coursemanagement.enums.Semester;
import com.example.coursemanagement.exception.CourseNotFoundException;
import com.example.coursemanagement.mapper.CourseInstanceMapper;
import com.example.coursemanagement.model.Course;
import com.example.coursemanagement.model.CourseInstance;
import com.example.coursemanagement.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseInstanceService {
    private final CourseRepository courseRepository;
    public String createInstance(CourseInstanceRequest courseInstanceRequest) {
        int courseId = courseInstanceRequest.getCourseId();
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        Course savedCourse = courseOptional.get();
        CourseInstance instance = CourseInstanceMapper.prepareCourseInstance(courseInstanceRequest);
        instance.setCourse(savedCourse);
        savedCourse.getCourseInstanceList().add(instance);

        courseRepository.save(savedCourse);

        return "All GOOD";
    }
}
