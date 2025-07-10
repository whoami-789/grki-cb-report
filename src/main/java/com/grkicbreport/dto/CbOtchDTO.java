package com.grkicbreport.dto;

public class CbOtchDTO {
    private String account;
    private String amount;
    private String prev_amount;

    public String getPrev_amount() {
        return prev_amount;
    }

    public void setPrev_amount(String prev_amount) {
        this.prev_amount = prev_amount;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
