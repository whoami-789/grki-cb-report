package com.grkicbreport.dto.saveClaim;

import lombok.*;

import java.util.List;

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
    private String business_project;

    public CreditDTO() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreditDTO;
    }

}
