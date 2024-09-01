package com.grkicbreport.dto.saveAgreement;

import com.grkicbreport.dto.CreditorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class saveAgreementDTO {
    private String save_mode;
    private CreditorDTO creditor;
    private AgreementDTO agreement;
}
