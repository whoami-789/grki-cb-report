package com.grkicbreport.dto.saveContract;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SourcesDTO {
    private String type;
    private String foreign_org;
    private String currency;
    private String amount;
}
