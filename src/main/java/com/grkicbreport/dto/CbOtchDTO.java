package com.grkicbreport.dto;

import java.math.BigDecimal;

public class CbOtchDTO {
    private String account;
    private BigDecimal prevAmount = BigDecimal.ZERO;
    private BigDecimal deb = BigDecimal.ZERO;
    private BigDecimal kred = BigDecimal.ZERO;
    private BigDecimal currentAmount = BigDecimal.ZERO;

    public CbOtchDTO(String account) { this.account = account; }
    public CbOtchDTO() {}

    // ← ДОБАВЬ ЭТО
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }

    public void addAmounts(BigDecimal p, BigDecimal d, BigDecimal k, BigDecimal c) {
        prevAmount = prevAmount.add(p);
        deb = deb.add(d);
        kred = kred.add(k);
        currentAmount = currentAmount.add(c);
    }

    public BigDecimal getPrevAmountBD() { return prevAmount; }
    public BigDecimal getDebBD() { return deb; }
    public BigDecimal getKredBD() { return kred; }
    public BigDecimal getCurrentAmountBD() { return currentAmount; }

    public String getPrevAmount() { return prevAmount.toPlainString(); }
    public String getDeb() { return deb.toPlainString(); }
    public String getKred() { return kred.toPlainString(); }
    public String getCurrentAmount() { return currentAmount.toPlainString(); }

    public String toLogString() {
        return String.format("Тип %s: нач=%s, деб=%s, кред=%s, кон=%s",
                account, prevAmount, deb, kred, currentAmount);
    }

    public boolean equalsAmounts(CbOtchDTO other) {
        return prevAmount.compareTo(other.prevAmount) == 0 &&
                deb.compareTo(other.deb) == 0 &&
                kred.compareTo(other.kred) == 0 &&
                currentAmount.compareTo(other.currentAmount) == 0;
    }
}

