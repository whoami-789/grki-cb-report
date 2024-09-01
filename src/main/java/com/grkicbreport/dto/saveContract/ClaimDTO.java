package com.grkicbreport.dto.saveContract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClaimDTO {
    private String claim_guid;
    private String claim_id;
    private String contract_guid;
    private String contract_id;
}
