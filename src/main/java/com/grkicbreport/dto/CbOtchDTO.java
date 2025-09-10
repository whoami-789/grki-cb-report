package com.grkicbreport.dto;

import java.math.BigDecimal;

public class CbOtchDTO {
    private String account;
    private BigDecimal prevAmount;
    private BigDecimal deb;
    private BigDecimal kred;
    private BigDecimal currentAmount;

    public CbOtchDTO(String account) {
        this.account = account;
        this.prevAmount = BigDecimal.ZERO;
        this.deb = BigDecimal.ZERO;
        this.kred = BigDecimal.ZERO;
        this.currentAmount = BigDecimal.ZERO;
    }

    public void addAmounts(BigDecimal prev, BigDecimal deb, BigDecimal kred, BigDecimal current) {
        this.prevAmount = this.prevAmount.add(prev);
        this.deb = this.deb.add(deb);
        this.kred = this.kred.add(kred);
        this.currentAmount = this.currentAmount.add(current);
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getPrevAmount() {
        return prevAmount;
    }

    public void setPrevAmount(BigDecimal prevAmount) {
        this.prevAmount = prevAmount;
    }

    public BigDecimal getDeb() {
        return deb;
    }

    public void setDeb(BigDecimal deb) {
        this.deb = deb;
    }

    public BigDecimal getKred() {
        return kred;
    }

    public void setKred(BigDecimal kred) {
        this.kred = kred;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }
}
