package com.ironhack.rest.service.interfaces;

import com.ironhack.rest.dao.Product;
import com.ironhack.rest.enums.Category;

import java.math.BigDecimal;

public interface IProductService {

    void update(Long id, Product product);

    void updatePrice(Long id , BigDecimal price);

    void updateCategory(Long id, Category category );
}
