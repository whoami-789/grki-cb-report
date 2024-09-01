package com.grkicbreport.dto.saveClaim;

import com.grkicbreport.dto.CreditorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
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
