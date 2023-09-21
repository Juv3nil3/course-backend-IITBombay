package com.example.coursemanagement.mapper;

import com.example.coursemanagement.dto.request.CourseRequest;
import com.example.coursemanagement.dto.response.CourseResponse;
import com.example.coursemanagement.model.Course;

public class CourseMapper {
    public static Course CourseRequestToCourse(CourseRequest courseRequest){
        return Course.builder()
                .title(courseRequest.getTitle())
                .courseCode(courseRequest.getCourseCode())
                .courseDescription(courseRequest.getCourseDescription())
                .build();
    }

    public static CourseResponse CourseToCourseResponse(Course course){
        return CourseResponse.builder()
                .title(course.getTitle())
                .courseCode(course.getCourseCode())
                .courseDescription(course.getCourseDescription())
                .build();
    }
}
