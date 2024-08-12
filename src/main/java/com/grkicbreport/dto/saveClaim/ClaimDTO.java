package com.grkicbreport.dto.saveClaim;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClaimDTO {
    private String claim_guid;
    private String claim_id;
    private String type;
    private String number;
    private String date;
}
