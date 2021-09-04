package com.ironhack.rest.repository;


import com.ironhack.rest.dao.Product;
import com.ironhack.rest.enums.Category;
import com.ironhack.rest.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryAndDepartment(Category category , Department department);

    List<Product> findByDepartment(Department department);

}
