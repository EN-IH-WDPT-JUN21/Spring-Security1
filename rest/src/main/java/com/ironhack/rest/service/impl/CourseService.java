package com.ironhack.rest.service.impl;

import com.ironhack.rest.dao.Course;
import com.ironhack.rest.repository.CourseRepository;
import com.ironhack.rest.service.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;


    public Course store(Course course) {
        // Validate if code already exists
        Optional<Course> optionalCourse = courseRepository.findById(course.getCode());
        if (optionalCourse.isPresent()) throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Course with code " + course.getCode() + " already exist");

        return courseRepository.save(course);
    }
}
