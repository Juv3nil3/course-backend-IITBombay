package com.example.coursemanagement.dto.request;

import com.example.coursemanagement.enums.Semester;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseInstanceRequest {
    int courseId;

    String year;

    Semester semester;
}
