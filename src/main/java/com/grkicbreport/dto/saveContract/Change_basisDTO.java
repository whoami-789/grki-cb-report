package com.grkicbreport.dto.saveContract;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Change_basisDTO {
    private String revisor;
    private String number;
    private String date;
    private String reason;
    private String revisor_chief;
}
