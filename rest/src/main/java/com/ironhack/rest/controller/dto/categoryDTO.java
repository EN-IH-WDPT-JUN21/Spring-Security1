package com.ironhack.rest.controller.dto;

import com.ironhack.rest.enums.Category;
import com.ironhack.rest.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Getter
@Setter
public class categoryDTO {

    @Enumerated(EnumType.STRING)
    private Category category;

}
