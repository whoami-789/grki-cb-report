package com.grkicbreport.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class KreditDTO {

    private String kod;
    private String numdog;
    private LocalDate datadog;
    private BigDecimal summa;
    private String grkiClaimId;
    private String grkiContractId;
}
