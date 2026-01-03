package com.grkicbreport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DokumentDTO {
    private String numdok;
    private LocalDate dats;
    private BigDecimal sums;
    private String ls;
    private String lscor;
    // Другие поля по необходимости
}
