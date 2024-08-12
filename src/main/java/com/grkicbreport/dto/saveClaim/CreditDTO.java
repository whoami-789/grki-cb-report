package com.grkicbreport.dto.saveClaim;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class CreditDTO {
    private String subject_type;
    private String type;
    private String currency;
    private String amount;
    private String percent;
    private String period;
    private List<String> targets;
    private String new_staff;
}
