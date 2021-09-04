package com.ironhack.rest.service.impl;


import com.ironhack.rest.dao.Product;
import com.ironhack.rest.repository.ProductRepository;
import com.ironhack.rest.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.ironhack.rest.enums.Category;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service   // used to mark the class as a service provider / define set of available methods
public class ProductService implements IProductService {


    @Autowired
    private ProductRepository productRepository;

//    public void update(Long id, Product product) {
//        Optional<Product> storedProduct = productRepository.findById(id);
//        if(storedProduct.isPresent()) {
//            product.setId(storedProduct.get().getId());   // this will replace the old product with same id
//            productRepository.save(product);
//        }
//    }

//    public void update(Long id, Product product){
//        Product storedProduct = productRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not Found"));
//            product.setId(storedProduct.getId());   // this will replace the old product with same id
//            productRepository.save(product);
//    }

    public void update(Long id, Product product){

        Optional<Product> storedProduct = productRepository.findById(id);
        if (storedProduct.isPresent()){

            if (product.getName() != null){
                try {
                    storedProduct.get().setName(product.getName());
                } catch(Exception e){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be blank");
                }
            }
            if (product.getCategory() != null){
                try{
                    storedProduct.get().setCategory(product.getCategory());
                } catch (Exception e){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Category");
                }
            }
            if (product.getDepartment() != null){
                try{
                    storedProduct.get().setDepartment(product.getDepartment());
                } catch (Exception e){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Department");
                }
            }
            productRepository.save(storedProduct.get());
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Product doesn't exists");
        }

    }

    public void updatePrice(Long id, BigDecimal price){
        Optional<Product> storedProduct = productRepository.findById(id);
        if (storedProduct.isPresent()){
            storedProduct.get().setPrice(price);
            productRepository.save(storedProduct.get());
        }
    }
    public void updateCategory(Long id, Category category){
        Optional<Product> storedProduct = productRepository.findById(id);
        if (storedProduct.isPresent()){
            storedProduct.get().setCategory(category);
            productRepository.save(storedProduct.get());
        }

    }




}
