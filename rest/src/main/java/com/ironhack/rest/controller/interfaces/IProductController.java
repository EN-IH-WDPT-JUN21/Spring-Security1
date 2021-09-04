package com.ironhack.rest.controller.interfaces;


import com.ironhack.rest.dao.Product;

import java.util.List;
import java.util.Optional;

public interface IProductController {

    Product getById(long productId);   // make use  of @path variable

    List<Product> getProductsByCategoryAndDepartment(Optional<String> category, String department); // make use of @RequestParams

}
