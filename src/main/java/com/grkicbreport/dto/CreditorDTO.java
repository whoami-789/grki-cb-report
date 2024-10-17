package com.grkicbreport.dto;

public class CreditorDTO {
    private String type;
    private String code;
    private String office;

    public CreditorDTO() {
    }

    public String getType() {
        return this.type;
    }

    public String getCode() {
        return this.code;
    }

    public String getOffice() {
        return this.office;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreditorDTO)) return false;
        final CreditorDTO other = (CreditorDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$code = this.getCode();
        final Object other$code = other.getCode();
        if (this$code == null ? other$code != null : !this$code.equals(other$code)) return false;
        final Object this$office = this.getOffice();
        final Object other$office = other.getOffice();
        if (this$office == null ? other$office != null : !this$office.equals(other$office)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreditorDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $code = this.getCode();
        result = result * PRIME + ($code == null ? 43 : $code.hashCode());
        final Object $office = this.getOffice();
        result = result * PRIME + ($office == null ? 43 : $office.hashCode());
        return result;
    }

    public String toString() {
        return "CreditorDTO(type=" + this.getType() + ", code=" + this.getCode() + ", office=" + this.getOffice() + ")";
    }
}
