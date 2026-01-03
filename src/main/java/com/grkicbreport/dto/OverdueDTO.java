package com.grkicbreport.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class OverdueDTO {
    private Date PlannedPaymentDate;
    private BigDecimal OverdueSumm;
    private String OverduePeriod;
}
