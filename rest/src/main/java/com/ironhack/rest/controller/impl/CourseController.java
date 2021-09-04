package com.ironhack.rest.controller.impl;


import com.ironhack.rest.controller.interfaces.ICourseController;
import com.ironhack.rest.service.interfaces.ICourseService;

import com.ironhack.rest.dao.Course;
import com.ironhack.rest.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CourseController implements ICourseController {


    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ICourseService courseService;



//    @GetMapping("/courses")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Course> getCourses(){
//
//        List<Course> courseList = new ArrayList<>();
//        courseList.add(new Course("MA101","College Algebra"));
//        courseList.add(new Course("MA102", "Calculus 1"));
//
//        Course course = new Course("CS105","Data Structures and Algorithms");
//
//        courseRepository.save(course);
//
//        return courseRepository.findAll();
//    }

    @GetMapping("/courses/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Course> getCoursesByNamePathVariable(@PathVariable(name = "name") String name) {
        return courseRepository.findByName(name);
    }

    @GetMapping("/courses")
    @ResponseStatus(HttpStatus.OK)
    public List<Course> getCoursesByNameQueryParam(@RequestParam Optional<String> name) {
        if (name.isPresent()) {
            return courseRepository.findByName(name.get());
        }
        else{
            return courseRepository.findAll();
        }
    }

    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public Course store(@RequestBody @Valid Course course) {
        return courseService.store(course);
    }
}
