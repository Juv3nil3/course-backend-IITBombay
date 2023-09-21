package com.example.coursemanagement.model;

import com.example.coursemanagement.enums.Semester;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String year;

    @Enumerated(EnumType.STRING)
    Semester semester;

    @ManyToOne
    @JoinColumn
    Course course;
}
