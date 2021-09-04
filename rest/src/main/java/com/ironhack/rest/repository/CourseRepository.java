package com.ironhack.rest.repository;


import com.ironhack.rest.dao.Course;
import com.ironhack.rest.dao.Product;
import com.ironhack.rest.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    List<Course> findByName(String name);


}
