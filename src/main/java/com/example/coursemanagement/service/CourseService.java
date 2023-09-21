package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.request.CourseRequest;
import com.example.coursemanagement.dto.response.CourseResponse;
import com.example.coursemanagement.exception.CourseNotFoundException;
import com.example.coursemanagement.mapper.CourseMapper;
import com.example.coursemanagement.model.Course;
import com.example.coursemanagement.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public String createCourse (CourseRequest courseRequest){
        Course savedCourse = CourseMapper.CourseRequestToCourse(courseRequest);
        courseRepository.save(savedCourse);
        return "Course Saved Successfully";
    }

    public List<CourseResponse> getAllCourses() {
        List<CourseResponse> allCourses = new ArrayList<>();
        List<Course> courses = courseRepository.findAll();
        for(Course course : courses){
            allCourses.add(CourseMapper.CourseToCourseResponse(course));
        }
        return allCourses;
    }


    public CourseResponse getCourseById(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if(courseOptional.isEmpty()){
            throw new CourseNotFoundException("Course Not Found!!");
        }
        CourseResponse response = CourseMapper.CourseToCourseResponse(courseOptional.get());
        return response;
    }

    public String deleteCourse(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if(courseOptional.isEmpty()){
            throw new CourseNotFoundException("Course Not Found");
        }
        Course course = courseOptional.get();
        courseRepository.delete(course);
        return "Course Deleted Successfully";
    }
}
