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
    int id;  //Unique Identifier

    String year;  //Year of course delivery YYYY

    int semester;  //Semester of delivery 1 or 2

    @ManyToOne
    @JoinColumn
    Course course;  //Reference to the associated course
}
