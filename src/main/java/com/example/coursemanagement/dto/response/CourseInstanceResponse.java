package com.example.coursemanagement.dto.response;

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

    int semester;

}
