package com.grkicbreport.dto.saveContract;

public class Inner_infoDTO {
    private String nibbd_code;
    private String debtor_inn;
    private String debtor_class;
    private String phone;
    private String email;

    public Inner_infoDTO(String nibbd_code, String debtor_inn, String debtor_class, String phone, String email) {
        this.nibbd_code = nibbd_code;
        this.debtor_inn = debtor_inn;
        this.debtor_class = debtor_class;
        this.phone = phone;
        this.email = email;
    }

    public String getNibbd_code() {
        return this.nibbd_code;
    }

    public String getDebtor_inn() {
        return this.debtor_inn;
    }

    public String getDebtor_class() {
        return this.debtor_class;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setNibbd_code(String nibbd_code) {
        this.nibbd_code = nibbd_code;
    }

    public void setDebtor_inn(String debtor_inn) {
        this.debtor_inn = debtor_inn;
    }

    public void setDebtor_class(String debtor_class) {
        this.debtor_class = debtor_class;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Inner_infoDTO)) return false;
        final Inner_infoDTO other = (Inner_infoDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$nibbd_code = this.getNibbd_code();
        final Object other$nibbd_code = other.getNibbd_code();
        if (this$nibbd_code == null ? other$nibbd_code != null : !this$nibbd_code.equals(other$nibbd_code))
            return false;
        final Object this$debtor_inn = this.getDebtor_inn();
        final Object other$debtor_inn = other.getDebtor_inn();
        if (this$debtor_inn == null ? other$debtor_inn != null : !this$debtor_inn.equals(other$debtor_inn))
            return false;
        final Object this$debtor_class = this.getDebtor_class();
        final Object other$debtor_class = other.getDebtor_class();
        if (this$debtor_class == null ? other$debtor_class != null : !this$debtor_class.equals(other$debtor_class))
            return false;
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Inner_infoDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $nibbd_code = this.getNibbd_code();
        result = result * PRIME + ($nibbd_code == null ? 43 : $nibbd_code.hashCode());
        final Object $debtor_inn = this.getDebtor_inn();
        result = result * PRIME + ($debtor_inn == null ? 43 : $debtor_inn.hashCode());
        final Object $debtor_class = this.getDebtor_class();
        result = result * PRIME + ($debtor_class == null ? 43 : $debtor_class.hashCode());
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        return result;
    }

    public String toString() {
        return "Inner_infoDTO(nibbd_code=" + this.getNibbd_code() + ", debtor_inn=" + this.getDebtor_inn() + ", debtor_class=" + this.getDebtor_class() + ", phone=" + this.getPhone() + ", email=" + this.getEmail() + ")";
    }
}
