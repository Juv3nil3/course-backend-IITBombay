package com.example.coursemanagement.mapper;

import com.example.coursemanagement.dto.request.CourseInstanceRequest;
import com.example.coursemanagement.enums.Semester;
import com.example.coursemanagement.model.CourseInstance;

public class CourseInstanceMapper {
    public static CourseInstance prepareCourseInstance(CourseInstanceRequest courseInstanceRequest){
        return CourseInstance.builder()
                .year(courseInstanceRequest.getYear())
                .semester(courseInstanceRequest.getSemester())
                .build();
    }
}
