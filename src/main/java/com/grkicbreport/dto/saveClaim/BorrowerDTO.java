package com.grkicbreport.dto.saveClaim;

public class BorrowerDTO {
    private String resident;
    private String pinfl;
    private String inn;
    private String nibbd_code;
    private String second_name;
    private String first_name;
    private String patronymic;
    private String birth_date;
    private String gender;
    private String citizenship;
    private String area;
    private String region;
    private String work_area;
    private String work_region;
    private String activity_direction;
    private String egrsp_data;
    private String egrsp_number;
    private String oked;
    private String ownership;
    private String legal_form;
    private String soogu;
    private String soato;
    private String type_business;
    private String full_name;
    private String short_name;
    private String staff_count;
    private String doc_type;
    private String doc_seria;
    private String doc_number;
    private String doc_date;
    private String doc_issuer;

    public BorrowerDTO() {
    }

    public String getResident() {
        return this.resident;
    }

    public String getPinfl() {
        return this.pinfl;
    }

    public String getInn() {
        return this.inn;
    }

    public String getNibbd_code() {
        return this.nibbd_code;
    }

    public String getSecond_name() {
        return this.second_name;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public String getPatronymic() {
        return this.patronymic;
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

    public String getArea() {
        return this.area;
    }

    public String getRegion() {
        return this.region;
    }

    public String getWork_area() {
        return this.work_area;
    }

    public String getWork_region() {
        return this.work_region;
    }

    public String getActivity_direction() {
        return this.activity_direction;
    }

    public String getEgrsp_data() {
        return this.egrsp_data;
    }

    public String getEgrsp_number() {
        return this.egrsp_number;
    }

    public String getOked() {
        return this.oked;
    }

    public String getOwnership() {
        return this.ownership;
    }

    public String getLegal_form() {
        return this.legal_form;
    }

    public String getSoogu() {
        return this.soogu;
    }

    public String getSoato() {
        return this.soato;
    }

    public String getType_business() {
        return this.type_business;
    }

    public String getFull_name() {
        return this.full_name;
    }

    public String getShort_name() {
        return this.short_name;
    }

    public String getStaff_count() {
        return this.staff_count;
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

    public void setResident(String resident) {
        this.resident = resident;
    }

    public void setPinfl(String pinfl) {
        this.pinfl = pinfl;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setNibbd_code(String nibbd_code) {
        this.nibbd_code = nibbd_code;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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

    public void setArea(String area) {
        this.area = area;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setWork_area(String work_area) {
        this.work_area = work_area;
    }

    public void setWork_region(String work_region) {
        this.work_region = work_region;
    }

    public void setActivity_direction(String activity_direction) {
        this.activity_direction = activity_direction;
    }

    public void setEgrsp_data(String egrsp_data) {
        this.egrsp_data = egrsp_data;
    }

    public void setEgrsp_number(String egrsp_number) {
        this.egrsp_number = egrsp_number;
    }

    public void setOked(String oked) {
        this.oked = oked;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public void setLegal_form(String legal_form) {
        this.legal_form = legal_form;
    }

    public void setSoogu(String soogu) {
        this.soogu = soogu;
    }

    public void setSoato(String soato) {
        this.soato = soato;
    }

    public void setType_business(String type_business) {
        this.type_business = type_business;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public void setStaff_count(String staff_count) {
        this.staff_count = staff_count;
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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BorrowerDTO)) return false;
        final BorrowerDTO other = (BorrowerDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$resident = this.getResident();
        final Object other$resident = other.getResident();
        if (this$resident == null ? other$resident != null : !this$resident.equals(other$resident)) return false;
        final Object this$pinfl = this.getPinfl();
        final Object other$pinfl = other.getPinfl();
        if (this$pinfl == null ? other$pinfl != null : !this$pinfl.equals(other$pinfl)) return false;
        final Object this$inn = this.getInn();
        final Object other$inn = other.getInn();
        if (this$inn == null ? other$inn != null : !this$inn.equals(other$inn)) return false;
        final Object this$nibbd_code = this.getNibbd_code();
        final Object other$nibbd_code = other.getNibbd_code();
        if (this$nibbd_code == null ? other$nibbd_code != null : !this$nibbd_code.equals(other$nibbd_code))
            return false;
        final Object this$second_name = this.getSecond_name();
        final Object other$second_name = other.getSecond_name();
        if (this$second_name == null ? other$second_name != null : !this$second_name.equals(other$second_name))
            return false;
        final Object this$first_name = this.getFirst_name();
        final Object other$first_name = other.getFirst_name();
        if (this$first_name == null ? other$first_name != null : !this$first_name.equals(other$first_name))
            return false;
        final Object this$patronymic = this.getPatronymic();
        final Object other$patronymic = other.getPatronymic();
        if (this$patronymic == null ? other$patronymic != null : !this$patronymic.equals(other$patronymic))
            return false;
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
        final Object this$area = this.getArea();
        final Object other$area = other.getArea();
        if (this$area == null ? other$area != null : !this$area.equals(other$area)) return false;
        final Object this$region = this.getRegion();
        final Object other$region = other.getRegion();
        if (this$region == null ? other$region != null : !this$region.equals(other$region)) return false;
        final Object this$work_area = this.getWork_area();
        final Object other$work_area = other.getWork_area();
        if (this$work_area == null ? other$work_area != null : !this$work_area.equals(other$work_area)) return false;
        final Object this$work_region = this.getWork_region();
        final Object other$work_region = other.getWork_region();
        if (this$work_region == null ? other$work_region != null : !this$work_region.equals(other$work_region))
            return false;
        final Object this$activity_direction = this.getActivity_direction();
        final Object other$activity_direction = other.getActivity_direction();
        if (this$activity_direction == null ? other$activity_direction != null : !this$activity_direction.equals(other$activity_direction))
            return false;
        final Object this$egrsp_data = this.getEgrsp_data();
        final Object other$egrsp_data = other.getEgrsp_data();
        if (this$egrsp_data == null ? other$egrsp_data != null : !this$egrsp_data.equals(other$egrsp_data))
            return false;
        final Object this$egrsp_number = this.getEgrsp_number();
        final Object other$egrsp_number = other.getEgrsp_number();
        if (this$egrsp_number == null ? other$egrsp_number != null : !this$egrsp_number.equals(other$egrsp_number))
            return false;
        final Object this$oked = this.getOked();
        final Object other$oked = other.getOked();
        if (this$oked == null ? other$oked != null : !this$oked.equals(other$oked)) return false;
        final Object this$ownership = this.getOwnership();
        final Object other$ownership = other.getOwnership();
        if (this$ownership == null ? other$ownership != null : !this$ownership.equals(other$ownership)) return false;
        final Object this$legal_form = this.getLegal_form();
        final Object other$legal_form = other.getLegal_form();
        if (this$legal_form == null ? other$legal_form != null : !this$legal_form.equals(other$legal_form))
            return false;
        final Object this$soogu = this.getSoogu();
        final Object other$soogu = other.getSoogu();
        if (this$soogu == null ? other$soogu != null : !this$soogu.equals(other$soogu)) return false;
        final Object this$soato = this.getSoato();
        final Object other$soato = other.getSoato();
        if (this$soato == null ? other$soato != null : !this$soato.equals(other$soato)) return false;
        final Object this$type_business = this.getType_business();
        final Object other$type_business = other.getType_business();
        if (this$type_business == null ? other$type_business != null : !this$type_business.equals(other$type_business))
            return false;
        final Object this$full_name = this.getFull_name();
        final Object other$full_name = other.getFull_name();
        if (this$full_name == null ? other$full_name != null : !this$full_name.equals(other$full_name)) return false;
        final Object this$short_name = this.getShort_name();
        final Object other$short_name = other.getShort_name();
        if (this$short_name == null ? other$short_name != null : !this$short_name.equals(other$short_name))
            return false;
        final Object this$staff_count = this.getStaff_count();
        final Object other$staff_count = other.getStaff_count();
        if (this$staff_count == null ? other$staff_count != null : !this$staff_count.equals(other$staff_count))
            return false;
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
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BorrowerDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $resident = this.getResident();
        result = result * PRIME + ($resident == null ? 43 : $resident.hashCode());
        final Object $pinfl = this.getPinfl();
        result = result * PRIME + ($pinfl == null ? 43 : $pinfl.hashCode());
        final Object $inn = this.getInn();
        result = result * PRIME + ($inn == null ? 43 : $inn.hashCode());
        final Object $nibbd_code = this.getNibbd_code();
        result = result * PRIME + ($nibbd_code == null ? 43 : $nibbd_code.hashCode());
        final Object $second_name = this.getSecond_name();
        result = result * PRIME + ($second_name == null ? 43 : $second_name.hashCode());
        final Object $first_name = this.getFirst_name();
        result = result * PRIME + ($first_name == null ? 43 : $first_name.hashCode());
        final Object $patronymic = this.getPatronymic();
        result = result * PRIME + ($patronymic == null ? 43 : $patronymic.hashCode());
        final Object $birth_date = this.getBirth_date();
        result = result * PRIME + ($birth_date == null ? 43 : $birth_date.hashCode());
        final Object $gender = this.getGender();
        result = result * PRIME + ($gender == null ? 43 : $gender.hashCode());
        final Object $citizenship = this.getCitizenship();
        result = result * PRIME + ($citizenship == null ? 43 : $citizenship.hashCode());
        final Object $area = this.getArea();
        result = result * PRIME + ($area == null ? 43 : $area.hashCode());
        final Object $region = this.getRegion();
        result = result * PRIME + ($region == null ? 43 : $region.hashCode());
        final Object $work_area = this.getWork_area();
        result = result * PRIME + ($work_area == null ? 43 : $work_area.hashCode());
        final Object $work_region = this.getWork_region();
        result = result * PRIME + ($work_region == null ? 43 : $work_region.hashCode());
        final Object $activity_direction = this.getActivity_direction();
        result = result * PRIME + ($activity_direction == null ? 43 : $activity_direction.hashCode());
        final Object $egrsp_data = this.getEgrsp_data();
        result = result * PRIME + ($egrsp_data == null ? 43 : $egrsp_data.hashCode());
        final Object $egrsp_number = this.getEgrsp_number();
        result = result * PRIME + ($egrsp_number == null ? 43 : $egrsp_number.hashCode());
        final Object $oked = this.getOked();
        result = result * PRIME + ($oked == null ? 43 : $oked.hashCode());
        final Object $ownership = this.getOwnership();
        result = result * PRIME + ($ownership == null ? 43 : $ownership.hashCode());
        final Object $legal_form = this.getLegal_form();
        result = result * PRIME + ($legal_form == null ? 43 : $legal_form.hashCode());
        final Object $soogu = this.getSoogu();
        result = result * PRIME + ($soogu == null ? 43 : $soogu.hashCode());
        final Object $soato = this.getSoato();
        result = result * PRIME + ($soato == null ? 43 : $soato.hashCode());
        final Object $type_business = this.getType_business();
        result = result * PRIME + ($type_business == null ? 43 : $type_business.hashCode());
        final Object $full_name = this.getFull_name();
        result = result * PRIME + ($full_name == null ? 43 : $full_name.hashCode());
        final Object $short_name = this.getShort_name();
        result = result * PRIME + ($short_name == null ? 43 : $short_name.hashCode());
        final Object $staff_count = this.getStaff_count();
        result = result * PRIME + ($staff_count == null ? 43 : $staff_count.hashCode());
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
        return result;
    }

    public String toString() {
        return "BorrowerDTO(resident=" + this.getResident() + ", pinfl=" + this.getPinfl() + ", inn=" + this.getInn() + ", nibbd_code=" + this.getNibbd_code() + ", second_name=" + this.getSecond_name() + ", first_name=" + this.getFirst_name() + ", patronymic=" + this.getPatronymic() + ", birth_date=" + this.getBirth_date() + ", gender=" + this.getGender() + ", citizenship=" + this.getCitizenship() + ", area=" + this.getArea() + ", region=" + this.getRegion() + ", work_area=" + this.getWork_area() + ", work_region=" + this.getWork_region() + ", activity_direction=" + this.getActivity_direction() + ", egrsp_data=" + this.getEgrsp_data() + ", egrsp_number=" + this.getEgrsp_number() + ", oked=" + this.getOked() + ", ownership=" + this.getOwnership() + ", legal_form=" + this.getLegal_form() + ", soogu=" + this.getSoogu() + ", soato=" + this.getSoato() + ", type_business=" + this.getType_business() + ", full_name=" + this.getFull_name() + ", short_name=" + this.getShort_name() + ", staff_count=" + this.getStaff_count() + ", doc_type=" + this.getDoc_type() + ", doc_seria=" + this.getDoc_seria() + ", doc_number=" + this.getDoc_number() + ", doc_date=" + this.getDoc_date() + ", doc_issuer=" + this.getDoc_issuer() + ")";
    }
}
