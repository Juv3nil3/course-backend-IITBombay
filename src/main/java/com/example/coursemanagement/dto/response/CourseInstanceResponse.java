package com.example.coursemanagement.dto.response;

import com.example.coursemanagement.enums.Semester;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseInstanceResponse {
    String title;

    String courseCode;

    String Year;
    Semester semester;

}
