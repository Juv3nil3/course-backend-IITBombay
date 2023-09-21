package com.example.coursemanagement.model;

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

    int semester;

    @ManyToOne
    @JoinColumn
    Course course;
}
