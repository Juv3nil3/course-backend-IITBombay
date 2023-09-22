package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.request.CourseInstanceRequest;
import com.example.coursemanagement.dto.response.CourseInstanceResponse;
import com.example.coursemanagement.exception.CourseInstanceAlreadyExistsException;
import com.example.coursemanagement.exception.CourseInstanceNotFoundException;
import com.example.coursemanagement.exception.CourseNotFoundException;
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
        String year = courseInstanceRequest.getYear();
        int semester = courseInstanceRequest.getSemester();

        if (courseInstanceRepo.existsByCourseIdAndYearAndSemester(courseId, year, semester)) {
            throw new CourseInstanceAlreadyExistsException("A course instance for the same course, year, and semester already exists.");
        }
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
        // Check if an instance for the same course, year, and semester exists
        Optional<CourseInstance> courseInstanceOptional = courseInstanceRepo.findByIdAndYearAndSemesterWithCourseDescription(courseId, year, semester);
        if(courseInstanceOptional.isPresent()){
            CourseInstanceResponse response = CourseInstanceMapper.detailedCourseInstanceResponse(courseInstanceOptional.get());
            return response;
        }
        else {
            throw new CourseNotFoundException("Course instance not found");
        }
    }

    public String deleteCourseInstance(String year, int semester, int courseId) {
        Optional<CourseInstance> courseInstanceOptional = courseInstanceRepo.findByCourseIdAndYearAndSemester(courseId,year,semester);
        if(courseInstanceOptional.isEmpty()){
            throw new CourseInstanceNotFoundException("Course instance not found");
        }
        CourseInstance courseInstance = courseInstanceOptional.get();
        Course course = courseInstance.getCourse();
        course.getCourseInstanceList().remove(courseInstance);
        courseRepository.save(course);
        return "Instance deleted successfully";
    }
}
