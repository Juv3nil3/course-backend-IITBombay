package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.request.CourseInstanceRequest;
import com.example.coursemanagement.dto.response.CourseInstanceResponse;
import com.example.coursemanagement.exception.CourseInstanceAlreadyExistsException;
import com.example.coursemanagement.exception.CourseInstanceNotFoundException;
import com.example.coursemanagement.exception.CourseNotFoundException;
import com.example.coursemanagement.service.CourseInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instances")
public class CourseInstanceController {
    private final CourseInstanceService courseInstanceService;

    @PostMapping
    public ResponseEntity createInstance(@RequestBody CourseInstanceRequest courseInstanceRequest){
        //Create a new course
        try {
            CourseInstanceResponse response = courseInstanceService.createInstance(courseInstanceRequest);
            return new ResponseEntity(response,HttpStatus.CREATED);
        } catch (CourseInstanceAlreadyExistsException e){
            //Handle course instance already exists exception
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch(CourseNotFoundException e){
            //Handle course not found exception
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("/{year}/{semester}")
    public ResponseEntity getInstanceByYearAndSemester(@PathVariable("year") String year,
                                                       @PathVariable("semester") int semester){
        //List of all the course instances for given year and semester
        List<CourseInstanceResponse> response = courseInstanceService.getInstanceByYearAndSemester(year,semester);
        return new ResponseEntity(response,HttpStatus.FOUND);
    }

    @GetMapping("/{year}/{sem}/{id}")
    public ResponseEntity getDetailedInstance(@PathVariable("year") String year, @PathVariable("sem") int semester,
                                              @PathVariable("id") int courseId){
        try{
            CourseInstanceResponse response = courseInstanceService.getDetailedInstance(year,semester,courseId);
            return new ResponseEntity(response,HttpStatus.FOUND);
        } catch(CourseNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{year}/{sem}/{id}")
    public ResponseEntity deleteCourseInstance(@PathVariable("year") String year,
                                               @PathVariable("sem") int semester,
                                               @PathVariable("id") int courseId){
        try{
            String response = courseInstanceService.deleteCourseInstance(year,semester,courseId);
            return new ResponseEntity(response,HttpStatus.OK);
        }catch (CourseInstanceNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity("An error occured while deleting the instance", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
