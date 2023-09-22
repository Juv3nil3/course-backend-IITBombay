package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.request.CourseRequest;
import com.example.coursemanagement.dto.response.CourseResponse;
import com.example.coursemanagement.exception.CourseNotFoundException;
import com.example.coursemanagement.exception.DuplicateCourseCodeException;
import com.example.coursemanagement.mapper.CourseMapper;
import com.example.coursemanagement.model.Course;
import com.example.coursemanagement.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    //Service for course related operations

    private final CourseRepository courseRepository;

    public String createCourse (CourseRequest courseRequest){
        try{
            Course savedCourse = CourseMapper.CourseRequestToCourse(courseRequest);
            courseRepository.save(savedCourse);
            return "Course Saved Successfully";
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException constraintViolationException = (ConstraintViolationException) e.getCause();
                if (constraintViolationException.getSQLException().getSQLState().equals("23000")) {
                    throw new DuplicateCourseCodeException("A course with the same course code already exists.");
                }
            }
            throw e;
        }
    }

    public List<CourseResponse> getAllCourses() {
        List<CourseResponse> allCourses = new ArrayList<>();
        List<Course> courses = courseRepository.findAll();
        for(Course course : courses){
            allCourses.add(CourseMapper.CourseToCourseResponse(course));
        }
        return allCourses;
    }


    public CourseResponse getCourseById(int id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if(courseOptional.isEmpty()){
            throw new CourseNotFoundException("Invalid course id");
        }
        CourseResponse response = CourseMapper.CourseToCourseResponse(courseOptional.get());
        return response;
    }

    public String deleteCourse(int id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if(courseOptional.isEmpty()){
            throw new CourseNotFoundException("Invalid course id");
        }
        Course course = courseOptional.get();
        courseRepository.delete(course);
        return "Course Deleted Successfully";
    }
}
