package com.example.coursemanagement.mapper;

import com.example.coursemanagement.dto.request.CourseInstanceRequest;
import com.example.coursemanagement.dto.response.CourseInstanceResponse;
import com.example.coursemanagement.model.CourseInstance;

public class CourseInstanceMapper {
    public static CourseInstance prepareCourseInstance(CourseInstanceRequest courseInstanceRequest){
        return CourseInstance.builder()
                .year(courseInstanceRequest.getYear())
                .semester(courseInstanceRequest.getSemester())
                .build();
    }

    public static CourseInstanceResponse CourseInstanceToCourseInstanceResponse(CourseInstance courseInstance){
        return CourseInstanceResponse.builder()
                .title(courseInstance.getCourse().getTitle())
                .courseCode(courseInstance.getCourse().getCourseCode())
                .Year(courseInstance.getYear())
                .semester(courseInstance.getSemester())
                .build();
    }

    public static CourseInstanceResponse detailedCourseInstanceResponse(CourseInstance courseInstance){
        return CourseInstanceResponse.builder()
                .title(courseInstance.getCourse().getTitle())
                .courseCode(courseInstance.getCourse().getCourseCode())
                .Year(courseInstance.getYear())
                .semester(courseInstance.getSemester())
                .description(courseInstance.getCourse().getCourseDescription())
                .build();
    }
}
