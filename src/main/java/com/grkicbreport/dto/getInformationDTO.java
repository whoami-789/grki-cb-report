package com.grkicbreport.dto;

public class getInformationDTO {
    private CreditorDTO creditor;
    private IdentityDTO identity;

    public getInformationDTO() {
    }

    public CreditorDTO getCreditor() {
        return this.creditor;
    }

    public IdentityDTO getIdentity() {
        return this.identity;
    }

    public void setCreditor(CreditorDTO creditor) {
        this.creditor = creditor;
    }

    public void setIdentity(IdentityDTO identity) {
        this.identity = identity;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof getInformationDTO)) return false;
        final getInformationDTO other = (getInformationDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$creditor = this.getCreditor();
        final Object other$creditor = other.getCreditor();
        if (this$creditor == null ? other$creditor != null : !this$creditor.equals(other$creditor)) return false;
        final Object this$identity = this.getIdentity();
        final Object other$identity = other.getIdentity();
        if (this$identity == null ? other$identity != null : !this$identity.equals(other$identity)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof getInformationDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $creditor = this.getCreditor();
        result = result * PRIME + ($creditor == null ? 43 : $creditor.hashCode());
        final Object $identity = this.getIdentity();
        result = result * PRIME + ($identity == null ? 43 : $identity.hashCode());
        return result;
    }

    public String toString() {
        return "getInformationDTO(creditor=" + this.getCreditor() + ", identity=" + this.getIdentity() + ")";
    }

    public static class IdentityDTO {
        private String identity_type;
        private String identity_id;

        public IdentityDTO() {
        }

        public String getIdentity_type() {
            return this.identity_type;
        }

        public String getIdentity_id() {
            return this.identity_id;
        }

        public void setIdentity_type(String identity_type) {
            this.identity_type = identity_type;
        }

        public void setIdentity_id(String identity_id) {
            this.identity_id = identity_id;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof IdentityDTO)) return false;
            final IdentityDTO other = (IdentityDTO) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$identity_type = this.getIdentity_type();
            final Object other$identity_type = other.getIdentity_type();
            if (this$identity_type == null ? other$identity_type != null : !this$identity_type.equals(other$identity_type))
                return false;
            final Object this$identity_id = this.getIdentity_id();
            final Object other$identity_id = other.getIdentity_id();
            if (this$identity_id == null ? other$identity_id != null : !this$identity_id.equals(other$identity_id))
                return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof IdentityDTO;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $identity_type = this.getIdentity_type();
            result = result * PRIME + ($identity_type == null ? 43 : $identity_type.hashCode());
            final Object $identity_id = this.getIdentity_id();
            result = result * PRIME + ($identity_id == null ? 43 : $identity_id.hashCode());
            return result;
        }

        public String toString() {
            return "getInformationDTO.IdentityDTO(identity_type=" + this.getIdentity_type() + ", identity_id=" + this.getIdentity_id() + ")";
        }
    }
}
