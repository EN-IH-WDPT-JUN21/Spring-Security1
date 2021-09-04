package com.ironhack.rest.dao;

import com.ironhack.rest.enums.Category;
import com.ironhack.rest.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;


@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "You must supply a product name ")
    private String name;

    @Digits(integer = 6, fraction = 2, message = "Wrong Price Format")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Department department;

    public Product(String name, BigDecimal price, Category category, Department department) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.department = department;
    }

}
