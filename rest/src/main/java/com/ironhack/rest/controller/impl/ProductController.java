package com.ironhack.rest.controller.impl;

import com.ironhack.rest.controller.dto.PriceDTO;
import com.ironhack.rest.controller.dto.categoryDTO;
import com.ironhack.rest.controller.dto.ProductDTO;

import com.ironhack.rest.controller.interfaces.IProductController;
import com.ironhack.rest.dao.Product;
import com.ironhack.rest.enums.Category;
import com.ironhack.rest.enums.Department;
import com.ironhack.rest.repository.ProductRepository;

import com.ironhack.rest.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
public class ProductController implements IProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IProductService productService;




    @GetMapping("/hello-world")  // this manages a specific route
    @ResponseStatus(HttpStatus.OK)   // this is managing the status code
    public String helloWorld() {
        return "Hello World";
    }





    @GetMapping("/products/{id}")   // uniform resource identifier
    @ResponseStatus(HttpStatus.OK)
    public Product getById(@PathVariable(name = "id") long productId) {

        Optional<Product> optionalProduct = productRepository.findById(productId);

        return optionalProduct.isPresent() ? optionalProduct.get() : null;

    }

    @GetMapping("/products")
    public List<Product> getProductsByCategoryAndDepartment(@RequestParam Optional<String> category, @RequestParam(defaultValue = "clothing") String department){
        if (category.isPresent()){
            return productRepository.findByCategoryAndDepartment(Category.valueOf(category.get().toUpperCase()), Department.valueOf(department.toUpperCase()));
        }
        else{
//            return productRepository.findByDepartment(Department.valueOf(department.toUpperCase()));
          return productRepository.findAll();
        }

    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product store(@RequestBody @Valid Product product ) {
        return productRepository.save(product);
    }


    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable(name = "id") Long id, @RequestBody @Valid Product product  ){
        productService.update(id,product);
    }


    @PatchMapping("/products/{id}/price")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePrice(@PathVariable(name = "id") Long id, @RequestBody @Valid PriceDTO priceDTO ) {
        productService.updatePrice(id, priceDTO.getPrice());
    }


    @PatchMapping("/products/{id}/category")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable(name = "id") Long id, @RequestBody @Valid categoryDTO categorydto ) {
        productService.updateCategory(id, categorydto.getCategory());
    }


    @DeleteMapping("products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id){
        productRepository.deleteById(id);
    }


}
