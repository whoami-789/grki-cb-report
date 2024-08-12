package com.grkicbreport.dto.saveContract;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CapitalDTO {
    private String amount_uzs;
    private String amount_usd;
    private String amount_eur;
}
