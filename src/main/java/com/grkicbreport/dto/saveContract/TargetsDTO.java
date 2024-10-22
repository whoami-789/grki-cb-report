package com.grkicbreport.dto.saveContract;

public class TargetsDTO {
    private String type;
    private String amount;
    private String info;

    public TargetsDTO() {
    }

    public String getType() {
        return this.type;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getInfo() {
        return this.info;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TargetsDTO)) return false;
        final TargetsDTO other = (TargetsDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        final Object this$info = this.getInfo();
        final Object other$info = other.getInfo();
        if (this$info == null ? other$info != null : !this$info.equals(other$info)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TargetsDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        final Object $info = this.getInfo();
        result = result * PRIME + ($info == null ? 43 : $info.hashCode());
        return result;
    }

    public String toString() {
        return "TargetsDTO(type=" + this.getType() + ", amount=" + this.getAmount() + ", info=" + this.getInfo() + ")";
    }
}
