package com.example.coursemanagement.repository;

import com.example.coursemanagement.model.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseInstanceRepo extends JpaRepository<CourseInstance, Integer> {
    List<CourseInstance> findByYearAndSemester(String year, int semester);

    boolean existsByCourseIdAndYearAndSemester(int courseId, String year, int semester);

    @Query("SELECT ci FROM CourseInstance ci JOIN FETCH ci.course WHERE ci.course.id = :courseId AND ci.year = :year AND ci.semester = :semester")
    Optional<CourseInstance> findByIdAndYearAndSemesterWithCourseDescription(
            int courseId,
            String year,
            int semester);

    Optional<CourseInstance> findByCourseIdAndYearAndSemester(int courseId, String year, int semester);
}
