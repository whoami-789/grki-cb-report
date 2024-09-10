package com.grkicbreport.dto;

import com.grkicbreport.response.Response;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class getInformationDTO {
    private CreditorDTO creditor;
    private IdentityDTO identity;

    @Data
    @NoArgsConstructor
    public static class IdentityDTO {
        private String identity_type;
        private String identity_id;
    }
}
