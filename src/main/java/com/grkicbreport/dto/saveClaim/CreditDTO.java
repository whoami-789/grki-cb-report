package com.grkicbreport.dto.saveClaim;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
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
