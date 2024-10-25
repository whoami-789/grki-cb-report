package com.grkicbreport.dto.saveContract;

import com.grkicbreport.dto.CreditorDTO;

import java.util.ArrayList;
import java.util.List;

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

    public saveContractDTO() {
    }

    public String getSave_mode() {
        return this.save_mode;
    }

    public ClaimDTO getClaim() {
        return this.claim;
    }

    public CreditorDTO getCreditor() {
        return this.creditor;
    }

    public DecisionDTO getDecision() {
        return this.decision;
    }

    public AgreementDTO getAgreement() {
        return this.agreement;
    }

    public BorrowerDTO getBorrower() {
        return this.borrower;
    }

    public ContractDTO getContract() {
        return this.contract;
    }

    public List<TargetsDTO> getTargets() {
        return this.targets;
    }

    public List<SourcesDTO> getSources() {
        return this.sources;
    }

    public Inner_infoDTO getInner_info() {
        return this.inner_info;
    }

    public Change_basisDTO getChange_basis() {
        return this.change_basis;
    }

    public String getLeasing_objects() {
        return this.leasing_objects;
    }

    public String getExport_info() {
        return this.export_info;
    }

    public String getProlongation() {
        return this.prolongation;
    }

    public void setSave_mode(String save_mode) {
        this.save_mode = save_mode;
    }

    public void setClaim(ClaimDTO claim) {
        this.claim = claim;
    }

    public void setCreditor(CreditorDTO creditor) {
        this.creditor = creditor;
    }

    public void setDecision(DecisionDTO decision) {
        this.decision = decision;
    }

    public void setAgreement(AgreementDTO agreement) {
        this.agreement = agreement;
    }

    public void setBorrower(BorrowerDTO borrower) {
        this.borrower = borrower;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }

    public void setTargets(List<TargetsDTO> targets) {
        this.targets = targets;
    }

    public void setSources(List<SourcesDTO> sources) {
        this.sources = sources;
    }

    public void setInner_info(Inner_infoDTO inner_info) {
        this.inner_info = inner_info;
    }

    public void setChange_basis(Change_basisDTO change_basis) {
        this.change_basis = change_basis;
    }

    public void setLeasing_objects(String leasing_objects) {
        this.leasing_objects = leasing_objects;
    }

    public void setExport_info(String export_info) {
        this.export_info = export_info;
    }

    public void setProlongation(String prolongation) {
        this.prolongation = prolongation;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof saveContractDTO)) return false;
        final saveContractDTO other = (saveContractDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$save_mode = this.getSave_mode();
        final Object other$save_mode = other.getSave_mode();
        if (this$save_mode == null ? other$save_mode != null : !this$save_mode.equals(other$save_mode)) return false;
        final Object this$claim = this.getClaim();
        final Object other$claim = other.getClaim();
        if (this$claim == null ? other$claim != null : !this$claim.equals(other$claim)) return false;
        final Object this$creditor = this.getCreditor();
        final Object other$creditor = other.getCreditor();
        if (this$creditor == null ? other$creditor != null : !this$creditor.equals(other$creditor)) return false;
        final Object this$decision = this.getDecision();
        final Object other$decision = other.getDecision();
        if (this$decision == null ? other$decision != null : !this$decision.equals(other$decision)) return false;
        final Object this$agreement = this.getAgreement();
        final Object other$agreement = other.getAgreement();
        if (this$agreement == null ? other$agreement != null : !this$agreement.equals(other$agreement)) return false;
        final Object this$borrower = this.getBorrower();
        final Object other$borrower = other.getBorrower();
        if (this$borrower == null ? other$borrower != null : !this$borrower.equals(other$borrower)) return false;
        final Object this$contract = this.getContract();
        final Object other$contract = other.getContract();
        if (this$contract == null ? other$contract != null : !this$contract.equals(other$contract)) return false;
        final Object this$targets = this.getTargets();
        final Object other$targets = other.getTargets();
        if (this$targets == null ? other$targets != null : !this$targets.equals(other$targets)) return false;
        final Object this$sources = this.getSources();
        final Object other$sources = other.getSources();
        if (this$sources == null ? other$sources != null : !this$sources.equals(other$sources)) return false;
        final Object this$inner_info = this.getInner_info();
        final Object other$inner_info = other.getInner_info();
        if (this$inner_info == null ? other$inner_info != null : !this$inner_info.equals(other$inner_info))
            return false;
        final Object this$change_basis = this.getChange_basis();
        final Object other$change_basis = other.getChange_basis();
        if (this$change_basis == null ? other$change_basis != null : !this$change_basis.equals(other$change_basis))
            return false;
        final Object this$leasing_objects = this.getLeasing_objects();
        final Object other$leasing_objects = other.getLeasing_objects();
        if (this$leasing_objects == null ? other$leasing_objects != null : !this$leasing_objects.equals(other$leasing_objects))
            return false;
        final Object this$export_info = this.getExport_info();
        final Object other$export_info = other.getExport_info();
        if (this$export_info == null ? other$export_info != null : !this$export_info.equals(other$export_info))
            return false;
        final Object this$prolongation = this.getProlongation();
        final Object other$prolongation = other.getProlongation();
        if (this$prolongation == null ? other$prolongation != null : !this$prolongation.equals(other$prolongation))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof saveContractDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $save_mode = this.getSave_mode();
        result = result * PRIME + ($save_mode == null ? 43 : $save_mode.hashCode());
        final Object $claim = this.getClaim();
        result = result * PRIME + ($claim == null ? 43 : $claim.hashCode());
        final Object $creditor = this.getCreditor();
        result = result * PRIME + ($creditor == null ? 43 : $creditor.hashCode());
        final Object $decision = this.getDecision();
        result = result * PRIME + ($decision == null ? 43 : $decision.hashCode());
        final Object $agreement = this.getAgreement();
        result = result * PRIME + ($agreement == null ? 43 : $agreement.hashCode());
        final Object $borrower = this.getBorrower();
        result = result * PRIME + ($borrower == null ? 43 : $borrower.hashCode());
        final Object $contract = this.getContract();
        result = result * PRIME + ($contract == null ? 43 : $contract.hashCode());
        final Object $targets = this.getTargets();
        result = result * PRIME + ($targets == null ? 43 : $targets.hashCode());
        final Object $sources = this.getSources();
        result = result * PRIME + ($sources == null ? 43 : $sources.hashCode());
        final Object $inner_info = this.getInner_info();
        result = result * PRIME + ($inner_info == null ? 43 : $inner_info.hashCode());
        final Object $change_basis = this.getChange_basis();
        result = result * PRIME + ($change_basis == null ? 43 : $change_basis.hashCode());
        final Object $leasing_objects = this.getLeasing_objects();
        result = result * PRIME + ($leasing_objects == null ? 43 : $leasing_objects.hashCode());
        final Object $export_info = this.getExport_info();
        result = result * PRIME + ($export_info == null ? 43 : $export_info.hashCode());
        final Object $prolongation = this.getProlongation();
        result = result * PRIME + ($prolongation == null ? 43 : $prolongation.hashCode());
        return result;
    }

    public String toString() {
        return "saveContractDTO(save_mode=" + this.getSave_mode() + ", claim=" + this.getClaim() + ", creditor=" + this.getCreditor() + ", decision=" + this.getDecision() + ", agreement=" + this.getAgreement() + ", borrower=" + this.getBorrower() + ", contract=" + this.getContract() + ", targets=" + this.getTargets() + ", sources=" + this.getSources() + ", inner_info=" + this.getInner_info() + ", change_basis=" + this.getChange_basis() + ", leasing_objects=" + this.getLeasing_objects() + ", export_info=" + this.getExport_info() + ", prolongation=" + this.getProlongation() + ")";
    }
}
