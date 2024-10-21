package com.grkicbreport.dto.saveClaim;

public class ClaimDTO {
    private String claim_guid;
    private String claim_id;
    private String type;
    private String number;
    private String date;

    public ClaimDTO() {
    }

    public String getClaim_guid() {
        return this.claim_guid;
    }

    public String getClaim_id() {
        return this.claim_id;
    }

    public String getType() {
        return this.type;
    }

    public String getNumber() {
        return this.number;
    }

    public String getDate() {
        return this.date;
    }

    public void setClaim_guid(String claim_guid) {
        this.claim_guid = claim_guid;
    }

    public void setClaim_id(String claim_id) {
        this.claim_id = claim_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ClaimDTO)) return false;
        final ClaimDTO other = (ClaimDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$claim_guid = this.getClaim_guid();
        final Object other$claim_guid = other.getClaim_guid();
        if (this$claim_guid == null ? other$claim_guid != null : !this$claim_guid.equals(other$claim_guid))
            return false;
        final Object this$claim_id = this.getClaim_id();
        final Object other$claim_id = other.getClaim_id();
        if (this$claim_id == null ? other$claim_id != null : !this$claim_id.equals(other$claim_id)) return false;
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

    protected boolean canEqual(final Object other) {
        return other instanceof ClaimDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $claim_guid = this.getClaim_guid();
        result = result * PRIME + ($claim_guid == null ? 43 : $claim_guid.hashCode());
        final Object $claim_id = this.getClaim_id();
        result = result * PRIME + ($claim_id == null ? 43 : $claim_id.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $number = this.getNumber();
        result = result * PRIME + ($number == null ? 43 : $number.hashCode());
        final Object $date = this.getDate();
        result = result * PRIME + ($date == null ? 43 : $date.hashCode());
        return result;
    }

    public String toString() {
        return "ClaimDTO(claim_guid=" + this.getClaim_guid() + ", claim_id=" + this.getClaim_id() + ", type=" + this.getType() + ", number=" + this.getNumber() + ", date=" + this.getDate() + ")";
    }
}
