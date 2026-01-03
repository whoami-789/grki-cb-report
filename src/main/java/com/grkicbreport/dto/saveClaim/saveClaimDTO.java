package com.grkicbreport.dto.saveClaim;

import com.grkicbreport.dto.CreditorDTO;

import java.util.List;

public class saveClaimDTO {
    private String save_mode;
    private CreditorDTO creditor;
    private ClaimDTO claim;
    private CreditDTO financing;
    private BorrowerDTO borrower;
    private List<IncomeDTO> income;
    private ContactsDTO contacts;

    public saveClaimDTO() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof saveClaimDTO;
    }

    public String getSave_mode() {
        return this.save_mode;
    }

    public CreditorDTO getCreditor() {
        return this.creditor;
    }

    public ClaimDTO getClaim() {
        return this.claim;
    }

    public CreditDTO getFinancing() {
        return this.financing;
    }

    public BorrowerDTO getBorrower() {
        return this.borrower;
    }

    public List<IncomeDTO> getIncome() {
        return this.income;
    }

    public ContactsDTO getContacts() {
        return this.contacts;
    }

    public void setSave_mode(String save_mode) {
        this.save_mode = save_mode;
    }

    public void setCreditor(CreditorDTO creditor) {
        this.creditor = creditor;
    }

    public void setClaim(ClaimDTO claim) {
        this.claim = claim;
    }

    public void setFinancing(CreditDTO financing) {
        this.financing = financing;
    }

    public void setBorrower(BorrowerDTO borrower) {
        this.borrower = borrower;
    }

    public void setIncome(List<IncomeDTO> income) {
        this.income = income;
    }

    public void setContacts(ContactsDTO contacts) {
        this.contacts = contacts;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof saveClaimDTO)) return false;
        final saveClaimDTO other = (saveClaimDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$save_mode = this.getSave_mode();
        final Object other$save_mode = other.getSave_mode();
        if (this$save_mode == null ? other$save_mode != null : !this$save_mode.equals(other$save_mode)) return false;
        final Object this$creditor = this.getCreditor();
        final Object other$creditor = other.getCreditor();
        if (this$creditor == null ? other$creditor != null : !this$creditor.equals(other$creditor)) return false;
        final Object this$claim = this.getClaim();
        final Object other$claim = other.getClaim();
        if (this$claim == null ? other$claim != null : !this$claim.equals(other$claim)) return false;
        final Object this$financing = this.getFinancing();
        final Object other$financing = other.getFinancing();
        if (this$financing == null ? other$financing != null : !this$financing.equals(other$financing)) return false;
        final Object this$borrower = this.getBorrower();
        final Object other$borrower = other.getBorrower();
        if (this$borrower == null ? other$borrower != null : !this$borrower.equals(other$borrower)) return false;
        final Object this$income = this.getIncome();
        final Object other$income = other.getIncome();
        if (this$income == null ? other$income != null : !this$income.equals(other$income)) return false;
        final Object this$contacts = this.getContacts();
        final Object other$contacts = other.getContacts();
        if (this$contacts == null ? other$contacts != null : !this$contacts.equals(other$contacts)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $save_mode = this.getSave_mode();
        result = result * PRIME + ($save_mode == null ? 43 : $save_mode.hashCode());
        final Object $creditor = this.getCreditor();
        result = result * PRIME + ($creditor == null ? 43 : $creditor.hashCode());
        final Object $claim = this.getClaim();
        result = result * PRIME + ($claim == null ? 43 : $claim.hashCode());
        final Object $financing = this.getFinancing();
        result = result * PRIME + ($financing == null ? 43 : $financing.hashCode());
        final Object $borrower = this.getBorrower();
        result = result * PRIME + ($borrower == null ? 43 : $borrower.hashCode());
        final Object $income = this.getIncome();
        result = result * PRIME + ($income == null ? 43 : $income.hashCode());
        final Object $contacts = this.getContacts();
        result = result * PRIME + ($contacts == null ? 43 : $contacts.hashCode());
        return result;
    }

    public String toString() {
        return "saveClaimDTO(save_mode=" + this.getSave_mode() + ", creditor=" + this.getCreditor() + ", claim=" + this.getClaim() + ", financing=" + this.getFinancing() + ", borrower=" + this.getBorrower() + ", income=" + this.getIncome() + ", contacts=" + this.getContacts() + ")";
    }
}
