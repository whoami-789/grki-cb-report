package com.grkicbreport.dto.saveContract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SourcesDTO {
    private String type;
    private String foreign_org;
    private String currency;
    private String amount;
}
