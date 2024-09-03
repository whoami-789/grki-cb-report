package com.grkicbreport.dto.saveProvision;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProvisionsDTO {

    private String provision_type;
    private String currency;
    private String amount;
    private String provision_source;
    private String number;
    private String date;
    private String name;
    private List<Mortgage> mortgage;
    private List<Vehicle> vehicles;
    private List<Security> securities;
    private List<GuaranteeSurety> guarantees_sureties;
    private List<InsurancePolicy> insurance_policies;
    private List<Deposit> deposits;
    private List<Collateral> collateral;
    private OwnerLegal owner_legal;
    private OwnerIndividual owner_individual;

    @Data
    @NoArgsConstructor
    public static class Mortgage {
        private String provision_id;
        private String cadaster;
        private String estimate_amount;
        private String cadastre_amount;
        private String pledge_amount;
        private String year;
        private String object_country;
        private String object_area;
        private String object_region;
        private String estimate_inn;
        private String estimate_name;
        private String estimate_date;
        private String insurance_inn;
        private String insurance_name;
    }

    @Data
    @NoArgsConstructor
    public static class Vehicle {
        private String provision_id;
        private String engine_number;
        private String body_number;
        private String year;
        private String country;
        private String state_number;
        private String model;
        private String chassis_number;
        private String color;
        private String doc_seria_number;
        private String vin_number;
        private String estimate_amount;
        private String pledge_amount;
        private String estimate_inn;
        private String estimate_name;
        private String insurance_inn;
        private String insurance_name;
    }

    @Data
    @NoArgsConstructor
    public static class Security {
        private String provision_id;
        private String date_begin;
        private String date_end;
        private String emitent_resident;
        private String emitent_inn;
        private String total_amount;
        private String total_count;
        private String single_amount;
        private String discount_amount;
        private String emitent_name;
    }

    @Data
    @NoArgsConstructor
    public static class GuaranteeSurety {
        private String provision_id;
        private String date_end;
    }

    @Data
    @NoArgsConstructor
    public static class InsurancePolicy {
        private String provision_id;
        private String insurance_name;
        private String insurance_inn;
        private String date_end;
    }

    @Data
    @NoArgsConstructor
    public static class Deposit {
        private String provision_id;
        private String date_end;
        private String bank_code;
        private String deposit_account;
        private String deposit_name;
    }

    @Data
    @NoArgsConstructor
    public static class Collateral {
        private String provision_id;
        private String estimate_amount;
        private String pledge_amount;
        private String object_name;
        private String year;
        private String date_receivables;
        private String object_country;
        private String object_area;
        private String object_region;
        private String estimate_inn;
        private String estimate_name;
        private String estimate_date;
        private String insurance_inn;
        private String insurance_name;
    }

    @Data
    @NoArgsConstructor
    public static class OwnerLegal {
        private String resident_code;
        private String resident_inn;
        private String nibbd_code;
        private String noresident_inn;
        private String name;
        private String country;
        private String area;
        private String region;
        private String post_address;
        private String post_index;
        private String web_site;
        private String phone;
        private String email;
    }

    @Data
    @NoArgsConstructor
    public static class OwnerIndividual {
        private String resident_code;
        private String pinfl;
        private String birth_date;
        private String gender;
        private String country;
        private String area;
        private String region;
        private String doc_type;
        private String doc_seria;
        private String doc_number;
        private String doc_date;
        private String doc_issuer;
        private String second_name;
        private String first_name;
        private String patronymic;
        private String post_address;
        private String phone;
        private String email;
    }

}
