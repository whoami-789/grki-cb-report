package com.grkicbreport.dto.saveProvision;

import com.grkicbreport.dto.CreditorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class saveProvisionDTO {
    private String save_mode;
    private CreditorDTO creditor;
    private ContractDTO contract;
    private List<ProvisionsDTO> provisions = new ArrayList<>();
}
