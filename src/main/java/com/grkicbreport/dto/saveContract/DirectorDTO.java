package com.grkicbreport.dto.saveContract;

public class DirectorDTO {
    private String inn;
    private String pinfl;
    private String full_name;
    private String birth_date;
    private String gender;
    private String citizenship;
    private String country;
    private String area;
    private String region;
    private String doc_type;
    private String doc_seria;
    private String doc_number;
    private String doc_date;
    private String doc_issuer;
    private ContactsDTO contacts;

    public DirectorDTO(String inn, String pinfl, String full_name, String birth_date, String gender, String citizenship, String country, String area, String region, String doc_type, String doc_seria, String doc_number, String doc_date, String doc_issuer, ContactsDTO contacts) {
        this.inn = inn;
        this.pinfl = pinfl;
        this.full_name = full_name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.citizenship = citizenship;
        this.country = country;
        this.area = area;
        this.region = region;
        this.doc_type = doc_type;
        this.doc_seria = doc_seria;
        this.doc_number = doc_number;
        this.doc_date = doc_date;
        this.doc_issuer = doc_issuer;
        this.contacts = contacts;
    }

    public String getInn() {
        return this.inn;
    }

    public String getPinfl() {
        return this.pinfl;
    }

    public String getFull_name() {
        return this.full_name;
    }

    public String getBirth_date() {
        return this.birth_date;
    }

    public String getGender() {
        return this.gender;
    }

    public String getCitizenship() {
        return this.citizenship;
    }

    public String getCountry() {
        return this.country;
    }

    public String getArea() {
        return this.area;
    }

    public String getRegion() {
        return this.region;
    }

    public String getDoc_type() {
        return this.doc_type;
    }

    public String getDoc_seria() {
        return this.doc_seria;
    }

    public String getDoc_number() {
        return this.doc_number;
    }

    public String getDoc_date() {
        return this.doc_date;
    }

    public String getDoc_issuer() {
        return this.doc_issuer;
    }

    public ContactsDTO getContacts() {
        return this.contacts;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setPinfl(String pinfl) {
        this.pinfl = pinfl;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }

    public void setDoc_seria(String doc_seria) {
        this.doc_seria = doc_seria;
    }

    public void setDoc_number(String doc_number) {
        this.doc_number = doc_number;
    }

    public void setDoc_date(String doc_date) {
        this.doc_date = doc_date;
    }

    public void setDoc_issuer(String doc_issuer) {
        this.doc_issuer = doc_issuer;
    }

    public void setContacts(ContactsDTO contacts) {
        this.contacts = contacts;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DirectorDTO)) return false;
        final DirectorDTO other = (DirectorDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$inn = this.getInn();
        final Object other$inn = other.getInn();
        if (this$inn == null ? other$inn != null : !this$inn.equals(other$inn)) return false;
        final Object this$pinfl = this.getPinfl();
        final Object other$pinfl = other.getPinfl();
        if (this$pinfl == null ? other$pinfl != null : !this$pinfl.equals(other$pinfl)) return false;
        final Object this$full_name = this.getFull_name();
        final Object other$full_name = other.getFull_name();
        if (this$full_name == null ? other$full_name != null : !this$full_name.equals(other$full_name)) return false;
        final Object this$birth_date = this.getBirth_date();
        final Object other$birth_date = other.getBirth_date();
        if (this$birth_date == null ? other$birth_date != null : !this$birth_date.equals(other$birth_date))
            return false;
        final Object this$gender = this.getGender();
        final Object other$gender = other.getGender();
        if (this$gender == null ? other$gender != null : !this$gender.equals(other$gender)) return false;
        final Object this$citizenship = this.getCitizenship();
        final Object other$citizenship = other.getCitizenship();
        if (this$citizenship == null ? other$citizenship != null : !this$citizenship.equals(other$citizenship))
            return false;
        final Object this$country = this.getCountry();
        final Object other$country = other.getCountry();
        if (this$country == null ? other$country != null : !this$country.equals(other$country)) return false;
        final Object this$area = this.getArea();
        final Object other$area = other.getArea();
        if (this$area == null ? other$area != null : !this$area.equals(other$area)) return false;
        final Object this$region = this.getRegion();
        final Object other$region = other.getRegion();
        if (this$region == null ? other$region != null : !this$region.equals(other$region)) return false;
        final Object this$doc_type = this.getDoc_type();
        final Object other$doc_type = other.getDoc_type();
        if (this$doc_type == null ? other$doc_type != null : !this$doc_type.equals(other$doc_type)) return false;
        final Object this$doc_seria = this.getDoc_seria();
        final Object other$doc_seria = other.getDoc_seria();
        if (this$doc_seria == null ? other$doc_seria != null : !this$doc_seria.equals(other$doc_seria)) return false;
        final Object this$doc_number = this.getDoc_number();
        final Object other$doc_number = other.getDoc_number();
        if (this$doc_number == null ? other$doc_number != null : !this$doc_number.equals(other$doc_number))
            return false;
        final Object this$doc_date = this.getDoc_date();
        final Object other$doc_date = other.getDoc_date();
        if (this$doc_date == null ? other$doc_date != null : !this$doc_date.equals(other$doc_date)) return false;
        final Object this$doc_issuer = this.getDoc_issuer();
        final Object other$doc_issuer = other.getDoc_issuer();
        if (this$doc_issuer == null ? other$doc_issuer != null : !this$doc_issuer.equals(other$doc_issuer))
            return false;
        final Object this$contacts = this.getContacts();
        final Object other$contacts = other.getContacts();
        if (this$contacts == null ? other$contacts != null : !this$contacts.equals(other$contacts)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DirectorDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $inn = this.getInn();
        result = result * PRIME + ($inn == null ? 43 : $inn.hashCode());
        final Object $pinfl = this.getPinfl();
        result = result * PRIME + ($pinfl == null ? 43 : $pinfl.hashCode());
        final Object $full_name = this.getFull_name();
        result = result * PRIME + ($full_name == null ? 43 : $full_name.hashCode());
        final Object $birth_date = this.getBirth_date();
        result = result * PRIME + ($birth_date == null ? 43 : $birth_date.hashCode());
        final Object $gender = this.getGender();
        result = result * PRIME + ($gender == null ? 43 : $gender.hashCode());
        final Object $citizenship = this.getCitizenship();
        result = result * PRIME + ($citizenship == null ? 43 : $citizenship.hashCode());
        final Object $country = this.getCountry();
        result = result * PRIME + ($country == null ? 43 : $country.hashCode());
        final Object $area = this.getArea();
        result = result * PRIME + ($area == null ? 43 : $area.hashCode());
        final Object $region = this.getRegion();
        result = result * PRIME + ($region == null ? 43 : $region.hashCode());
        final Object $doc_type = this.getDoc_type();
        result = result * PRIME + ($doc_type == null ? 43 : $doc_type.hashCode());
        final Object $doc_seria = this.getDoc_seria();
        result = result * PRIME + ($doc_seria == null ? 43 : $doc_seria.hashCode());
        final Object $doc_number = this.getDoc_number();
        result = result * PRIME + ($doc_number == null ? 43 : $doc_number.hashCode());
        final Object $doc_date = this.getDoc_date();
        result = result * PRIME + ($doc_date == null ? 43 : $doc_date.hashCode());
        final Object $doc_issuer = this.getDoc_issuer();
        result = result * PRIME + ($doc_issuer == null ? 43 : $doc_issuer.hashCode());
        final Object $contacts = this.getContacts();
        result = result * PRIME + ($contacts == null ? 43 : $contacts.hashCode());
        return result;
    }

    public String toString() {
        return "DirectorDTO(inn=" + this.getInn() + ", pinfl=" + this.getPinfl() + ", full_name=" + this.getFull_name() + ", birth_date=" + this.getBirth_date() + ", gender=" + this.getGender() + ", citizenship=" + this.getCitizenship() + ", country=" + this.getCountry() + ", area=" + this.getArea() + ", region=" + this.getRegion() + ", doc_type=" + this.getDoc_type() + ", doc_seria=" + this.getDoc_seria() + ", doc_number=" + this.getDoc_number() + ", doc_date=" + this.getDoc_date() + ", doc_issuer=" + this.getDoc_issuer() + ", contacts=" + this.getContacts() + ")";
    }
}
