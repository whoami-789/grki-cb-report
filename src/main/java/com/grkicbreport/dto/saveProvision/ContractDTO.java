package com.grkicbreport.dto.saveProvision;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContractDTO {
    private String contract_guid;
    private String contract_id;
    private String agreement_guid;
    private String agreement_id;
}
