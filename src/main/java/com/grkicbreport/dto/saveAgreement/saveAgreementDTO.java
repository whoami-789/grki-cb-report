package com.grkicbreport.dto.saveAgreement;

import com.grkicbreport.dto.CreditorDTO;

public class saveAgreementDTO {
    private String save_mode;
    private CreditorDTO creditor;
    private AgreementDTO agreement;

    public saveAgreementDTO() {
    }

    public String getSave_mode() {
        return this.save_mode;
    }

    public CreditorDTO getCreditor() {
        return this.creditor;
    }

    public AgreementDTO getAgreement() {
        return this.agreement;
    }

    public void setSave_mode(String save_mode) {
        this.save_mode = save_mode;
    }

    public void setCreditor(CreditorDTO creditor) {
        this.creditor = creditor;
    }

    public void setAgreement(AgreementDTO agreement) {
        this.agreement = agreement;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof saveAgreementDTO)) return false;
        final saveAgreementDTO other = (saveAgreementDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$save_mode = this.getSave_mode();
        final Object other$save_mode = other.getSave_mode();
        if (this$save_mode == null ? other$save_mode != null : !this$save_mode.equals(other$save_mode)) return false;
        final Object this$creditor = this.getCreditor();
        final Object other$creditor = other.getCreditor();
        if (this$creditor == null ? other$creditor != null : !this$creditor.equals(other$creditor)) return false;
        final Object this$agreement = this.getAgreement();
        final Object other$agreement = other.getAgreement();
        if (this$agreement == null ? other$agreement != null : !this$agreement.equals(other$agreement)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof saveAgreementDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $save_mode = this.getSave_mode();
        result = result * PRIME + ($save_mode == null ? 43 : $save_mode.hashCode());
        final Object $creditor = this.getCreditor();
        result = result * PRIME + ($creditor == null ? 43 : $creditor.hashCode());
        final Object $agreement = this.getAgreement();
        result = result * PRIME + ($agreement == null ? 43 : $agreement.hashCode());
        return result;
    }

    public String toString() {
        return "saveAgreementDTO(save_mode=" + this.getSave_mode() + ", creditor=" + this.getCreditor() + ", agreement=" + this.getAgreement() + ")";
    }
}
