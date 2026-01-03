package com.grkicbreport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZalogDTO {
    private BigDecimal sums;
    private String ls;
    private String kodCb;
    private String numDog;
    // Другие поля по необходимости
}
