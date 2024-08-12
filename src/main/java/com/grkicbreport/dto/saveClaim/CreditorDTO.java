package com.grkicbreport.dto.saveClaim;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreditorDTO {
    private String type;
    private String code;
    private String office;
}
