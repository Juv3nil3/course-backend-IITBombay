package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.request.CourseInstanceRequest;
import com.example.coursemanagement.dto.response.CourseInstanceResponse;
import com.example.coursemanagement.mapper.CourseInstanceMapper;
import com.example.coursemanagement.model.Course;
import com.example.coursemanagement.model.CourseInstance;
import com.example.coursemanagement.repository.CourseInstanceRepo;
import com.example.coursemanagement.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseInstanceService {
    private final CourseRepository courseRepository;
    private final CourseInstanceRepo courseInstanceRepo;


    public CourseInstanceResponse createInstance(CourseInstanceRequest courseInstanceRequest) {
        int courseId = courseInstanceRequest.getCourseId();
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        Course savedCourse = courseOptional.get();
        CourseInstance instance = CourseInstanceMapper.prepareCourseInstance(courseInstanceRequest);
        instance.setCourse(savedCourse);
        savedCourse.getCourseInstanceList().add(instance);

        courseRepository.save(savedCourse);

        return CourseInstanceMapper.CourseInstanceToCourseInstanceResponse(instance);
    }


    public List<CourseInstanceResponse> getInstanceByYearAndSemester(String year, int semester) {
        List<CourseInstance> allInstances = courseInstanceRepo.findByYearAndSemester(year, semester);
        List<CourseInstanceResponse> response = new ArrayList<>();
        for(CourseInstance c : allInstances){
            response.add(CourseInstanceMapper.CourseInstanceToCourseInstanceResponse(c));
        }
        return response;
    }

    public CourseInstanceResponse getDetailedInstance(String year, int semester, int courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);

    }
}
