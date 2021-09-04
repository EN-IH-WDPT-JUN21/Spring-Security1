package com.ironhack.rest.controller.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
public class PriceDTO {
    @Digits(integer = 6, fraction = 2, message = "Wrong Price Format")
    private BigDecimal price;
}
