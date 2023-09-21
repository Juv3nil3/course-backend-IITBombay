package com.example.coursemanagement.repository;

import com.example.coursemanagement.model.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseInstanceRepo extends JpaRepository<CourseInstance, Integer> {
    List<CourseInstance> findByYearAndSemester(String year, int semester);
}
