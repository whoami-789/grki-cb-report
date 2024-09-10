package com.grkicbreport.dto.saveContract;

import com.grkicbreport.dto.CreditorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class saveContractDTO {
    private String save_mode;
    private ClaimDTO claim;
    private CreditorDTO creditor;
    private DecisionDTO decision;
    private AgreementDTO agreement;
    private BorrowerDTO borrower;
    private ContractDTO contract;
    private List<TargetsDTO> targets = new ArrayList<>();
    private List<SourcesDTO> sources = new ArrayList<>();
    private List<CompensationsDTO> compensations = new ArrayList<>();
    private Inner_infoDTO inner_info;
    private Change_basisDTO change_basis;
    private String leasing_objects;
    private String export_info;
    private String prolongation;

}
