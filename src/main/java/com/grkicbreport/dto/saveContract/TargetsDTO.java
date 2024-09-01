package com.grkicbreport.dto.saveContract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TargetsDTO {
    private String type;
    private String amount;
    private String info;
}
