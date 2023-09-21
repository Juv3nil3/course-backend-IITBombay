package com.example.coursemanagement.repository;

import com.example.coursemanagement.enums.Semester;
import com.example.coursemanagement.model.Course;
import com.example.coursemanagement.model.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    //List<CourseInstance> findByYearAndSemester(int year, Semester semester);
}
