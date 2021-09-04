package com.ironhack.rest.controller.dto;

import com.ironhack.rest.enums.Category;
import com.ironhack.rest.enums.Department;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {

    @NotBlank(message = "You must supply a product name ")
    private String name;

    @Digits(integer = 6, fraction = 2, message = "Wrong Price Format")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Department department;
}
