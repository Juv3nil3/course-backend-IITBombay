package com.example.coursemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;  //Unique identifier for the course

    @NotBlank(message = "Title cant be Empty!")
    @Column(nullable = false)
    String title;

    @NotBlank(message = "Code cant be Empty!")
    @Column(unique = true, nullable = false)
    String courseCode;

    @NotBlank(message = "Description cant be Empty!")
    @Column(nullable = false)
    String courseDescription;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CourseInstance> courseInstanceList = new ArrayList<>(); //List of instances associated with this course



}
