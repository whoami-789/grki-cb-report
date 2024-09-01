package com.grkicbreport.dto.saveContract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DecisionDTO {
    private String decide;
    private String number;
    private String date;
    private String decide_chief;
    private String borrower_link;
}
