package com.grkicbreport.dto.saveContract;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class saveContractDTO {
    private String save_mode;
    private ClaimDTO claim;
    private DecisionDTO decision;
    private AgreementDTO agreement;
    private BorrowerDTO borrower;
    private ContractDTO contract;
    private List<TargetsDTO> targets;
    private List<SourcesDTO> sources;
    private Inner_infoDTO inner_info;
    private Change_basisDTO change_basis;
}
