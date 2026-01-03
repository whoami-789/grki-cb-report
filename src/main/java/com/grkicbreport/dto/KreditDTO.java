package com.grkicbreport.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class KreditDTO {

    private String kod;
    private String numdog;
    private LocalDate datadog;
    private BigDecimal summa;
    private String grkiClaimId;
    private String grkiContractId;
    private String mahalla_ru;

    public KreditDTO() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof KreditDTO;
    }

}