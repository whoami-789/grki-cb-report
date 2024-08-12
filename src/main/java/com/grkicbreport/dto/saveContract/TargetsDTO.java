package com.grkicbreport.dto.saveContract;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TargetsDTO {
    private String type;
    private String amount;
    private String info;
}
