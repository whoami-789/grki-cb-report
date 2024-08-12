package com.grkicbreport.dto.saveClaim;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class saveClaimDTO {
    private String save_mode;
    private CreditorDTO creditor;
    private ClaimDTO claim;
    private CreditDTO credit;
    private BorrowerDTO borrower;
    private List<IncomeDTO> income;
    private ContactsDTO contacts;
}
