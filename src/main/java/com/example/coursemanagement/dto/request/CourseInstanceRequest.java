package com.example.coursemanagement.dto.request;

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

    int semester;
}
