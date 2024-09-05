package com.grkicbreport.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Analiz_schetDTO {
    private String bal;
    private String namer;
    private BigDecimal deb;
    private BigDecimal kred;
}
