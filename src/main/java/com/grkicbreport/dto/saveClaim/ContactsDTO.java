package com.grkicbreport.dto.saveClaim;

public class ContactsDTO {
    private String post_address;
    private String post_index;
    private String phone;
    private String email;

    public ContactsDTO() {
    }

    public String getPost_address() {
        return this.post_address;
    }

    public String getPost_index() {
        return this.post_index;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPost_address(String post_address) {
        this.post_address = post_address;
    }

    public void setPost_index(String post_index) {
        this.post_index = post_index;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ContactsDTO)) return false;
        final ContactsDTO other = (ContactsDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$post_address = this.getPost_address();
        final Object other$post_address = other.getPost_address();
        if (this$post_address == null ? other$post_address != null : !this$post_address.equals(other$post_address))
            return false;
        final Object this$post_index = this.getPost_index();
        final Object other$post_index = other.getPost_index();
        if (this$post_index == null ? other$post_index != null : !this$post_index.equals(other$post_index))
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
        return other instanceof ContactsDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $post_address = this.getPost_address();
        result = result * PRIME + ($post_address == null ? 43 : $post_address.hashCode());
        final Object $post_index = this.getPost_index();
        result = result * PRIME + ($post_index == null ? 43 : $post_index.hashCode());
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        return result;
    }

    public String toString() {
        return "ContactsDTO(post_address=" + this.getPost_address() + ", post_index=" + this.getPost_index() + ", phone=" + this.getPhone() + ", email=" + this.getEmail() + ")";
    }
}
