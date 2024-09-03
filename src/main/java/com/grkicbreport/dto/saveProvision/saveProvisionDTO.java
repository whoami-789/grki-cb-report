package com.grkicbreport.dto.saveProvision;

import com.grkicbreport.dto.CreditorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class saveProvisionDTO {
    private String save_mode;
    private CreditorDTO creditor;
    private ContractDTO contract;
    private ProvisionsDTO provisions;
}
