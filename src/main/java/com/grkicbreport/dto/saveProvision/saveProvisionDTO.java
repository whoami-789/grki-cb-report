package com.grkicbreport.dto.saveProvision;

import com.grkicbreport.dto.CreditorDTO;

import java.util.ArrayList;
import java.util.List;

public class saveProvisionDTO {
    private String save_mode;
    private CreditorDTO creditor;
    private ContractDTO contract;
    private List<ProvisionsDTO> provisions = new ArrayList<>();

    public saveProvisionDTO() {
    }

    public String getSave_mode() {
        return this.save_mode;
    }

    public CreditorDTO getCreditor() {
        return this.creditor;
    }

    public ContractDTO getContract() {
        return this.contract;
    }

    public List<ProvisionsDTO> getProvisions() {
        return this.provisions;
    }

    public void setSave_mode(String save_mode) {
        this.save_mode = save_mode;
    }

    public void setCreditor(CreditorDTO creditor) {
        this.creditor = creditor;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }

    public void setProvisions(List<ProvisionsDTO> provisions) {
        this.provisions = provisions;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof saveProvisionDTO)) return false;
        final saveProvisionDTO other = (saveProvisionDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$save_mode = this.getSave_mode();
        final Object other$save_mode = other.getSave_mode();
        if (this$save_mode == null ? other$save_mode != null : !this$save_mode.equals(other$save_mode)) return false;
        final Object this$creditor = this.getCreditor();
        final Object other$creditor = other.getCreditor();
        if (this$creditor == null ? other$creditor != null : !this$creditor.equals(other$creditor)) return false;
        final Object this$contract = this.getContract();
        final Object other$contract = other.getContract();
        if (this$contract == null ? other$contract != null : !this$contract.equals(other$contract)) return false;
        final Object this$provisions = this.getProvisions();
        final Object other$provisions = other.getProvisions();
        if (this$provisions == null ? other$provisions != null : !this$provisions.equals(other$provisions))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof saveProvisionDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $save_mode = this.getSave_mode();
        result = result * PRIME + ($save_mode == null ? 43 : $save_mode.hashCode());
        final Object $creditor = this.getCreditor();
        result = result * PRIME + ($creditor == null ? 43 : $creditor.hashCode());
        final Object $contract = this.getContract();
        result = result * PRIME + ($contract == null ? 43 : $contract.hashCode());
        final Object $provisions = this.getProvisions();
        result = result * PRIME + ($provisions == null ? 43 : $provisions.hashCode());
        return result;
    }

    public String toString() {
        return "saveProvisionDTO(save_mode=" + this.getSave_mode() + ", creditor=" + this.getCreditor() + ", contract=" + this.getContract() + ", provisions=" + this.getProvisions() + ")";
    }
}
