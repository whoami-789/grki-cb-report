package com.grkicbreport.dto;

import java.time.LocalDate;

public class RequestDTO {
    private String save_mode;

    private String contractNumber;

    private String loan_line;
    private String decisionNumber;
    private LocalDate decisionDate;

    private String agreement_id;
    private String agreement_number;
    private LocalDate agreement_date_begin;
    private LocalDate agreement_date_end;
    private String agreement_subject_type;
    private String agreement_inn_pinfl;
    private String agreement_name;
    private String agreement_amount;

    private String provisionNumber;
    private LocalDate provisionDate;
    private String nibbd;
    private String engine_number;
    private String body_number;
    private String year;
    private String state_number;
    private String model;
    private String chassis_number;
    private String color;
    private String doc_seria_number;
    private String vin_number;

    private String decide_number;
    private LocalDate decide_date;
    private String conclusion;
    private LocalDate send_date;

    private String type;
    private String number;
    private LocalDate date;

    public RequestDTO() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RequestDTO;
    }

    public String getSave_mode() {
        return this.save_mode;
    }

    public String getContractNumber() {
        return this.contractNumber;
    }

    public String getLoan_line() {
        return this.loan_line;
    }

    public String getDecisionNumber() {
        return this.decisionNumber;
    }

    public LocalDate getDecisionDate() {
        return this.decisionDate;
    }

    public String getAgreement_id() {
        return this.agreement_id;
    }

    public String getAgreement_number() {
        return this.agreement_number;
    }

    public LocalDate getAgreement_date_begin() {
        return this.agreement_date_begin;
    }

    public LocalDate getAgreement_date_end() {
        return this.agreement_date_end;
    }

    public String getAgreement_subject_type() {
        return this.agreement_subject_type;
    }

    public String getAgreement_inn_pinfl() {
        return this.agreement_inn_pinfl;
    }

    public String getAgreement_name() {
        return this.agreement_name;
    }

    public String getAgreement_amount() {
        return this.agreement_amount;
    }

    public String getProvisionNumber() {
        return this.provisionNumber;
    }

    public LocalDate getProvisionDate() {
        return this.provisionDate;
    }

    public String getNibbd() {
        return this.nibbd;
    }

    public String getEngine_number() {
        return this.engine_number;
    }

    public String getBody_number() {
        return this.body_number;
    }

    public String getYear() {
        return this.year;
    }

    public String getState_number() {
        return this.state_number;
    }

    public String getModel() {
        return this.model;
    }

    public String getChassis_number() {
        return this.chassis_number;
    }

    public String getColor() {
        return this.color;
    }

    public String getDoc_seria_number() {
        return this.doc_seria_number;
    }

    public String getVin_number() {
        return this.vin_number;
    }

    public String getDecide_number() {
        return this.decide_number;
    }

    public LocalDate getDecide_date() {
        return this.decide_date;
    }

    public String getConclusion() {
        return this.conclusion;
    }

    public LocalDate getSend_date() {
        return this.send_date;
    }

    public String getType() {
        return this.type;
    }

    public String getNumber() {
        return this.number;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setSave_mode(String save_mode) {
        this.save_mode = save_mode;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setLoan_line(String loan_line) {
        this.loan_line = loan_line;
    }

    public void setDecisionNumber(String decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public void setDecisionDate(LocalDate decisionDate) {
        this.decisionDate = decisionDate;
    }

    public void setAgreement_id(String agreement_id) {
        this.agreement_id = agreement_id;
    }

    public void setAgreement_number(String agreement_number) {
        this.agreement_number = agreement_number;
    }

    public void setAgreement_date_begin(LocalDate agreement_date_begin) {
        this.agreement_date_begin = agreement_date_begin;
    }

    public void setAgreement_date_end(LocalDate agreement_date_end) {
        this.agreement_date_end = agreement_date_end;
    }

    public void setAgreement_subject_type(String agreement_subject_type) {
        this.agreement_subject_type = agreement_subject_type;
    }

    public void setAgreement_inn_pinfl(String agreement_inn_pinfl) {
        this.agreement_inn_pinfl = agreement_inn_pinfl;
    }

    public void setAgreement_name(String agreement_name) {
        this.agreement_name = agreement_name;
    }

    public void setAgreement_amount(String agreement_amount) {
        this.agreement_amount = agreement_amount;
    }

    public void setProvisionNumber(String provisionNumber) {
        this.provisionNumber = provisionNumber;
    }

    public void setProvisionDate(LocalDate provisionDate) {
        this.provisionDate = provisionDate;
    }

    public void setNibbd(String nibbd) {
        this.nibbd = nibbd;
    }

    public void setEngine_number(String engine_number) {
        this.engine_number = engine_number;
    }

    public void setBody_number(String body_number) {
        this.body_number = body_number;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setState_number(String state_number) {
        this.state_number = state_number;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setChassis_number(String chassis_number) {
        this.chassis_number = chassis_number;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDoc_seria_number(String doc_seria_number) {
        this.doc_seria_number = doc_seria_number;
    }

    public void setVin_number(String vin_number) {
        this.vin_number = vin_number;
    }

    public void setDecide_number(String decide_number) {
        this.decide_number = decide_number;
    }

    public void setDecide_date(LocalDate decide_date) {
        this.decide_date = decide_date;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public void setSend_date(LocalDate send_date) {
        this.send_date = send_date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RequestDTO)) return false;
        final RequestDTO other = (RequestDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$save_mode = this.getSave_mode();
        final Object other$save_mode = other.getSave_mode();
        if (this$save_mode == null ? other$save_mode != null : !this$save_mode.equals(other$save_mode)) return false;
        final Object this$contractNumber = this.getContractNumber();
        final Object other$contractNumber = other.getContractNumber();
        if (this$contractNumber == null ? other$contractNumber != null : !this$contractNumber.equals(other$contractNumber))
            return false;
        final Object this$loan_line = this.getLoan_line();
        final Object other$loan_line = other.getLoan_line();
        if (this$loan_line == null ? other$loan_line != null : !this$loan_line.equals(other$loan_line)) return false;
        final Object this$decisionNumber = this.getDecisionNumber();
        final Object other$decisionNumber = other.getDecisionNumber();
        if (this$decisionNumber == null ? other$decisionNumber != null : !this$decisionNumber.equals(other$decisionNumber))
            return false;
        final Object this$decisionDate = this.getDecisionDate();
        final Object other$decisionDate = other.getDecisionDate();
        if (this$decisionDate == null ? other$decisionDate != null : !this$decisionDate.equals(other$decisionDate))
            return false;
        final Object this$agreement_id = this.getAgreement_id();
        final Object other$agreement_id = other.getAgreement_id();
        if (this$agreement_id == null ? other$agreement_id != null : !this$agreement_id.equals(other$agreement_id))
            return false;
        final Object this$agreement_number = this.getAgreement_number();
        final Object other$agreement_number = other.getAgreement_number();
        if (this$agreement_number == null ? other$agreement_number != null : !this$agreement_number.equals(other$agreement_number))
            return false;
        final Object this$agreement_date_begin = this.getAgreement_date_begin();
        final Object other$agreement_date_begin = other.getAgreement_date_begin();
        if (this$agreement_date_begin == null ? other$agreement_date_begin != null : !this$agreement_date_begin.equals(other$agreement_date_begin))
            return false;
        final Object this$agreement_date_end = this.getAgreement_date_end();
        final Object other$agreement_date_end = other.getAgreement_date_end();
        if (this$agreement_date_end == null ? other$agreement_date_end != null : !this$agreement_date_end.equals(other$agreement_date_end))
            return false;
        final Object this$agreement_subject_type = this.getAgreement_subject_type();
        final Object other$agreement_subject_type = other.getAgreement_subject_type();
        if (this$agreement_subject_type == null ? other$agreement_subject_type != null : !this$agreement_subject_type.equals(other$agreement_subject_type))
            return false;
        final Object this$agreement_inn_pinfl = this.getAgreement_inn_pinfl();
        final Object other$agreement_inn_pinfl = other.getAgreement_inn_pinfl();
        if (this$agreement_inn_pinfl == null ? other$agreement_inn_pinfl != null : !this$agreement_inn_pinfl.equals(other$agreement_inn_pinfl))
            return false;
        final Object this$agreement_name = this.getAgreement_name();
        final Object other$agreement_name = other.getAgreement_name();
        if (this$agreement_name == null ? other$agreement_name != null : !this$agreement_name.equals(other$agreement_name))
            return false;
        final Object this$agreement_amount = this.getAgreement_amount();
        final Object other$agreement_amount = other.getAgreement_amount();
        if (this$agreement_amount == null ? other$agreement_amount != null : !this$agreement_amount.equals(other$agreement_amount))
            return false;
        final Object this$provisionNumber = this.getProvisionNumber();
        final Object other$provisionNumber = other.getProvisionNumber();
        if (this$provisionNumber == null ? other$provisionNumber != null : !this$provisionNumber.equals(other$provisionNumber))
            return false;
        final Object this$provisionDate = this.getProvisionDate();
        final Object other$provisionDate = other.getProvisionDate();
        if (this$provisionDate == null ? other$provisionDate != null : !this$provisionDate.equals(other$provisionDate))
            return false;
        final Object this$nibbd = this.getNibbd();
        final Object other$nibbd = other.getNibbd();
        if (this$nibbd == null ? other$nibbd != null : !this$nibbd.equals(other$nibbd)) return false;
        final Object this$engine_number = this.getEngine_number();
        final Object other$engine_number = other.getEngine_number();
        if (this$engine_number == null ? other$engine_number != null : !this$engine_number.equals(other$engine_number))
            return false;
        final Object this$body_number = this.getBody_number();
        final Object other$body_number = other.getBody_number();
        if (this$body_number == null ? other$body_number != null : !this$body_number.equals(other$body_number))
            return false;
        final Object this$year = this.getYear();
        final Object other$year = other.getYear();
        if (this$year == null ? other$year != null : !this$year.equals(other$year)) return false;
        final Object this$state_number = this.getState_number();
        final Object other$state_number = other.getState_number();
        if (this$state_number == null ? other$state_number != null : !this$state_number.equals(other$state_number))
            return false;
        final Object this$model = this.getModel();
        final Object other$model = other.getModel();
        if (this$model == null ? other$model != null : !this$model.equals(other$model)) return false;
        final Object this$chassis_number = this.getChassis_number();
        final Object other$chassis_number = other.getChassis_number();
        if (this$chassis_number == null ? other$chassis_number != null : !this$chassis_number.equals(other$chassis_number))
            return false;
        final Object this$color = this.getColor();
        final Object other$color = other.getColor();
        if (this$color == null ? other$color != null : !this$color.equals(other$color)) return false;
        final Object this$doc_seria_number = this.getDoc_seria_number();
        final Object other$doc_seria_number = other.getDoc_seria_number();
        if (this$doc_seria_number == null ? other$doc_seria_number != null : !this$doc_seria_number.equals(other$doc_seria_number))
            return false;
        final Object this$vin_number = this.getVin_number();
        final Object other$vin_number = other.getVin_number();
        if (this$vin_number == null ? other$vin_number != null : !this$vin_number.equals(other$vin_number))
            return false;
        final Object this$decide_number = this.getDecide_number();
        final Object other$decide_number = other.getDecide_number();
        if (this$decide_number == null ? other$decide_number != null : !this$decide_number.equals(other$decide_number))
            return false;
        final Object this$decide_date = this.getDecide_date();
        final Object other$decide_date = other.getDecide_date();
        if (this$decide_date == null ? other$decide_date != null : !this$decide_date.equals(other$decide_date))
            return false;
        final Object this$conclusion = this.getConclusion();
        final Object other$conclusion = other.getConclusion();
        if (this$conclusion == null ? other$conclusion != null : !this$conclusion.equals(other$conclusion))
            return false;
        final Object this$send_date = this.getSend_date();
        final Object other$send_date = other.getSend_date();
        if (this$send_date == null ? other$send_date != null : !this$send_date.equals(other$send_date)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$number = this.getNumber();
        final Object other$number = other.getNumber();
        if (this$number == null ? other$number != null : !this$number.equals(other$number)) return false;
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        if (this$date == null ? other$date != null : !this$date.equals(other$date)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $save_mode = this.getSave_mode();
        result = result * PRIME + ($save_mode == null ? 43 : $save_mode.hashCode());
        final Object $contractNumber = this.getContractNumber();
        result = result * PRIME + ($contractNumber == null ? 43 : $contractNumber.hashCode());
        final Object $loan_line = this.getLoan_line();
        result = result * PRIME + ($loan_line == null ? 43 : $loan_line.hashCode());
        final Object $decisionNumber = this.getDecisionNumber();
        result = result * PRIME + ($decisionNumber == null ? 43 : $decisionNumber.hashCode());
        final Object $decisionDate = this.getDecisionDate();
        result = result * PRIME + ($decisionDate == null ? 43 : $decisionDate.hashCode());
        final Object $agreement_id = this.getAgreement_id();
        result = result * PRIME + ($agreement_id == null ? 43 : $agreement_id.hashCode());
        final Object $agreement_number = this.getAgreement_number();
        result = result * PRIME + ($agreement_number == null ? 43 : $agreement_number.hashCode());
        final Object $agreement_date_begin = this.getAgreement_date_begin();
        result = result * PRIME + ($agreement_date_begin == null ? 43 : $agreement_date_begin.hashCode());
        final Object $agreement_date_end = this.getAgreement_date_end();
        result = result * PRIME + ($agreement_date_end == null ? 43 : $agreement_date_end.hashCode());
        final Object $agreement_subject_type = this.getAgreement_subject_type();
        result = result * PRIME + ($agreement_subject_type == null ? 43 : $agreement_subject_type.hashCode());
        final Object $agreement_inn_pinfl = this.getAgreement_inn_pinfl();
        result = result * PRIME + ($agreement_inn_pinfl == null ? 43 : $agreement_inn_pinfl.hashCode());
        final Object $agreement_name = this.getAgreement_name();
        result = result * PRIME + ($agreement_name == null ? 43 : $agreement_name.hashCode());
        final Object $agreement_amount = this.getAgreement_amount();
        result = result * PRIME + ($agreement_amount == null ? 43 : $agreement_amount.hashCode());
        final Object $provisionNumber = this.getProvisionNumber();
        result = result * PRIME + ($provisionNumber == null ? 43 : $provisionNumber.hashCode());
        final Object $provisionDate = this.getProvisionDate();
        result = result * PRIME + ($provisionDate == null ? 43 : $provisionDate.hashCode());
        final Object $nibbd = this.getNibbd();
        result = result * PRIME + ($nibbd == null ? 43 : $nibbd.hashCode());
        final Object $engine_number = this.getEngine_number();
        result = result * PRIME + ($engine_number == null ? 43 : $engine_number.hashCode());
        final Object $body_number = this.getBody_number();
        result = result * PRIME + ($body_number == null ? 43 : $body_number.hashCode());
        final Object $year = this.getYear();
        result = result * PRIME + ($year == null ? 43 : $year.hashCode());
        final Object $state_number = this.getState_number();
        result = result * PRIME + ($state_number == null ? 43 : $state_number.hashCode());
        final Object $model = this.getModel();
        result = result * PRIME + ($model == null ? 43 : $model.hashCode());
        final Object $chassis_number = this.getChassis_number();
        result = result * PRIME + ($chassis_number == null ? 43 : $chassis_number.hashCode());
        final Object $color = this.getColor();
        result = result * PRIME + ($color == null ? 43 : $color.hashCode());
        final Object $doc_seria_number = this.getDoc_seria_number();
        result = result * PRIME + ($doc_seria_number == null ? 43 : $doc_seria_number.hashCode());
        final Object $vin_number = this.getVin_number();
        result = result * PRIME + ($vin_number == null ? 43 : $vin_number.hashCode());
        final Object $decide_number = this.getDecide_number();
        result = result * PRIME + ($decide_number == null ? 43 : $decide_number.hashCode());
        final Object $decide_date = this.getDecide_date();
        result = result * PRIME + ($decide_date == null ? 43 : $decide_date.hashCode());
        final Object $conclusion = this.getConclusion();
        result = result * PRIME + ($conclusion == null ? 43 : $conclusion.hashCode());
        final Object $send_date = this.getSend_date();
        result = result * PRIME + ($send_date == null ? 43 : $send_date.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $number = this.getNumber();
        result = result * PRIME + ($number == null ? 43 : $number.hashCode());
        final Object $date = this.getDate();
        result = result * PRIME + ($date == null ? 43 : $date.hashCode());
        return result;
    }

    public String toString() {
        return "RequestDTO(save_mode=" + this.getSave_mode() + ", contractNumber=" + this.getContractNumber() + ", loan_line=" + this.getLoan_line() + ", decisionNumber=" + this.getDecisionNumber() + ", decisionDate=" + this.getDecisionDate() + ", agreement_id=" + this.getAgreement_id() + ", agreement_number=" + this.getAgreement_number() + ", agreement_date_begin=" + this.getAgreement_date_begin() + ", agreement_date_end=" + this.getAgreement_date_end() + ", agreement_subject_type=" + this.getAgreement_subject_type() + ", agreement_inn_pinfl=" + this.getAgreement_inn_pinfl() + ", agreement_name=" + this.getAgreement_name() + ", agreement_amount=" + this.getAgreement_amount() + ", provisionNumber=" + this.getProvisionNumber() + ", provisionDate=" + this.getProvisionDate() + ", nibbd=" + this.getNibbd() + ", engine_number=" + this.getEngine_number() + ", body_number=" + this.getBody_number() + ", year=" + this.getYear() + ", state_number=" + this.getState_number() + ", model=" + this.getModel() + ", chassis_number=" + this.getChassis_number() + ", color=" + this.getColor() + ", doc_seria_number=" + this.getDoc_seria_number() + ", vin_number=" + this.getVin_number() + ", decide_number=" + this.getDecide_number() + ", decide_date=" + this.getDecide_date() + ", conclusion=" + this.getConclusion() + ", send_date=" + this.getSend_date() + ", type=" + this.getType() + ", number=" + this.getNumber() + ", date=" + this.getDate() + ")";
    }
}

