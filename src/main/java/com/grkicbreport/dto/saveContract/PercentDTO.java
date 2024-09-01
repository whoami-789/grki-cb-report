package com.grkicbreport.dto.saveContract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PercentDTO {
    private String percent_type;
    private String percent_total;
    private String percent_nonresident;
    private String percent_resident;
    private String borrower_percent;
    private String overdue_percent;
}
