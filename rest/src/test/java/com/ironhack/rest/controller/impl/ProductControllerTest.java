package com.ironhack.rest.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.rest.controller.dto.ProductDTO;
import com.ironhack.rest.dao.Product;
import com.ironhack.rest.enums.Category;
import com.ironhack.rest.enums.Department;
import com.ironhack.rest.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class ProductControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    // it is the central interface used for providing configuration information to the application

    @Autowired
    private ProductRepository productRepository;


    private MockMvc mockMvc;  // this is used to perform requests that expects a JSON format

    private final ObjectMapper objectMapper = new ObjectMapper(); // to read and write JSON, either to and from basics POJO

    private Product product;
    private Product product2;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        product = new Product("Playstation 5", new BigDecimal("499.99"), Category.COMMERCIAL_NEW, Department.JEWELRY);
        product2 = new Product("Xbox Series X", new BigDecimal("499.99"), Category.COMMERCIAL_NEW, Department.ART);

        productRepository.saveAll(List.of(product, product2));
    }

    @AfterEach
    void tearDown(){
        productRepository.deleteAll();
    }

    @Test
    void helloWorld() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/hello-world")).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hello World")); // validating the response
    }

    @Test
    void getProductsByCategoryAndDepartment_ValidParams_Products() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); // storing multi values in arraylist

        params.add("category","commercial_new");
        params.add("department","jewelry");

        MvcResult result = mockMvc.perform(
                get("/products")
                        .queryParams(params)
            ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Playstation"));
        assertFalse(result.getResponse().getContentAsString().contains("Xbox"));

    }

    @Test
    void getById_Valid_Product() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/products/"+product.getId())
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Playstation 5"));

    }

    @Test
    void store_Valid_Created() throws Exception {
       Product product = new Product("Nintendo Switch", new BigDecimal("799.99"), Category.COMMERCIAL_NEW, Department.ELECTRONICS);

       String body = objectMapper.writeValueAsString(product);
       MvcResult result = mockMvc.perform(    /// it's passing a JSON payload/body
               post("/products")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
       ).andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Nintendo"));

    }

    @Test
    void update_Valid_Created() throws Exception {

        ProductDTO productDTO = new ProductDTO();

        productDTO.setName("New Nintendo");
        productDTO.setCategory(Category.COMMERCIAL_NEW);
        productDTO.setPrice( new BigDecimal("47899.99"));
        productDTO.setDepartment(Department.ELECTRONICS);

        String body = objectMapper.writeValueAsString(productDTO);

        MvcResult result = mockMvc.perform(    /// it's passing a JSON payload/body
                put("/products"+product.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();


//        assertEquals("New Nintendo", product.getName());


    }


}