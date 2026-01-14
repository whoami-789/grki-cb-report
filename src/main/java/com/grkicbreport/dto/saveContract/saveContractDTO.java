package com.grkicbreport.dto.saveContract;

import com.grkicbreport.dto.CreditorDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
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
    private Inner_infoDTO inner_info;
    private Change_basisDTO change_basis;
    private String leasing_objects;
    private String export_info;
    private String prolongation;
    private String import_letter_of_credit;
    private String export_letter_of_credit;

    public saveContractDTO() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof saveContractDTO;
    }

}
