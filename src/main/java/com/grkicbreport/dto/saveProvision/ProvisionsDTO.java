package com.grkicbreport.dto.saveProvision;

import java.util.List;

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

    public ProvisionsDTO() {
    }

    public String getProvision_type() {
        return this.provision_type;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getProvision_source() {
        return this.provision_source;
    }

    public String getNumber() {
        return this.number;
    }

    public String getDate() {
        return this.date;
    }

    public String getName() {
        return this.name;
    }

    public List<Mortgage> getMortgage() {
        return this.mortgage;
    }

    public List<Vehicle> getVehicles() {
        return this.vehicles;
    }

    public List<Security> getSecurities() {
        return this.securities;
    }

    public List<GuaranteeSurety> getGuarantees_sureties() {
        return this.guarantees_sureties;
    }

    public List<InsurancePolicy> getInsurance_policies() {
        return this.insurance_policies;
    }

    public List<Deposit> getDeposits() {
        return this.deposits;
    }

    public List<Collateral> getCollateral() {
        return this.collateral;
    }

    public OwnerLegal getOwner_legal() {
        return this.owner_legal;
    }

    public OwnerIndividual getOwner_individual() {
        return this.owner_individual;
    }

    public void setProvision_type(String provision_type) {
        this.provision_type = provision_type;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setProvision_source(String provision_source) {
        this.provision_source = provision_source;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMortgage(List<Mortgage> mortgage) {
        this.mortgage = mortgage;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setSecurities(List<Security> securities) {
        this.securities = securities;
    }

    public void setGuarantees_sureties(List<GuaranteeSurety> guarantees_sureties) {
        this.guarantees_sureties = guarantees_sureties;
    }

    public void setInsurance_policies(List<InsurancePolicy> insurance_policies) {
        this.insurance_policies = insurance_policies;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public void setCollateral(List<Collateral> collateral) {
        this.collateral = collateral;
    }

    public void setOwner_legal(OwnerLegal owner_legal) {
        this.owner_legal = owner_legal;
    }

    public void setOwner_individual(OwnerIndividual owner_individual) {
        this.owner_individual = owner_individual;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ProvisionsDTO)) return false;
        final ProvisionsDTO other = (ProvisionsDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$provision_type = this.getProvision_type();
        final Object other$provision_type = other.getProvision_type();
        if (this$provision_type == null ? other$provision_type != null : !this$provision_type.equals(other$provision_type))
            return false;
        final Object this$currency = this.getCurrency();
        final Object other$currency = other.getCurrency();
        if (this$currency == null ? other$currency != null : !this$currency.equals(other$currency)) return false;
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        final Object this$provision_source = this.getProvision_source();
        final Object other$provision_source = other.getProvision_source();
        if (this$provision_source == null ? other$provision_source != null : !this$provision_source.equals(other$provision_source))
            return false;
        final Object this$number = this.getNumber();
        final Object other$number = other.getNumber();
        if (this$number == null ? other$number != null : !this$number.equals(other$number)) return false;
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        if (this$date == null ? other$date != null : !this$date.equals(other$date)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$mortgage = this.getMortgage();
        final Object other$mortgage = other.getMortgage();
        if (this$mortgage == null ? other$mortgage != null : !this$mortgage.equals(other$mortgage)) return false;
        final Object this$vehicles = this.getVehicles();
        final Object other$vehicles = other.getVehicles();
        if (this$vehicles == null ? other$vehicles != null : !this$vehicles.equals(other$vehicles)) return false;
        final Object this$securities = this.getSecurities();
        final Object other$securities = other.getSecurities();
        if (this$securities == null ? other$securities != null : !this$securities.equals(other$securities))
            return false;
        final Object this$guarantees_sureties = this.getGuarantees_sureties();
        final Object other$guarantees_sureties = other.getGuarantees_sureties();
        if (this$guarantees_sureties == null ? other$guarantees_sureties != null : !this$guarantees_sureties.equals(other$guarantees_sureties))
            return false;
        final Object this$insurance_policies = this.getInsurance_policies();
        final Object other$insurance_policies = other.getInsurance_policies();
        if (this$insurance_policies == null ? other$insurance_policies != null : !this$insurance_policies.equals(other$insurance_policies))
            return false;
        final Object this$deposits = this.getDeposits();
        final Object other$deposits = other.getDeposits();
        if (this$deposits == null ? other$deposits != null : !this$deposits.equals(other$deposits)) return false;
        final Object this$collateral = this.getCollateral();
        final Object other$collateral = other.getCollateral();
        if (this$collateral == null ? other$collateral != null : !this$collateral.equals(other$collateral))
            return false;
        final Object this$owner_legal = this.getOwner_legal();
        final Object other$owner_legal = other.getOwner_legal();
        if (this$owner_legal == null ? other$owner_legal != null : !this$owner_legal.equals(other$owner_legal))
            return false;
        final Object this$owner_individual = this.getOwner_individual();
        final Object other$owner_individual = other.getOwner_individual();
        if (this$owner_individual == null ? other$owner_individual != null : !this$owner_individual.equals(other$owner_individual))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ProvisionsDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $provision_type = this.getProvision_type();
        result = result * PRIME + ($provision_type == null ? 43 : $provision_type.hashCode());
        final Object $currency = this.getCurrency();
        result = result * PRIME + ($currency == null ? 43 : $currency.hashCode());
        final Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        final Object $provision_source = this.getProvision_source();
        result = result * PRIME + ($provision_source == null ? 43 : $provision_source.hashCode());
        final Object $number = this.getNumber();
        result = result * PRIME + ($number == null ? 43 : $number.hashCode());
        final Object $date = this.getDate();
        result = result * PRIME + ($date == null ? 43 : $date.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $mortgage = this.getMortgage();
        result = result * PRIME + ($mortgage == null ? 43 : $mortgage.hashCode());
        final Object $vehicles = this.getVehicles();
        result = result * PRIME + ($vehicles == null ? 43 : $vehicles.hashCode());
        final Object $securities = this.getSecurities();
        result = result * PRIME + ($securities == null ? 43 : $securities.hashCode());
        final Object $guarantees_sureties = this.getGuarantees_sureties();
        result = result * PRIME + ($guarantees_sureties == null ? 43 : $guarantees_sureties.hashCode());
        final Object $insurance_policies = this.getInsurance_policies();
        result = result * PRIME + ($insurance_policies == null ? 43 : $insurance_policies.hashCode());
        final Object $deposits = this.getDeposits();
        result = result * PRIME + ($deposits == null ? 43 : $deposits.hashCode());
        final Object $collateral = this.getCollateral();
        result = result * PRIME + ($collateral == null ? 43 : $collateral.hashCode());
        final Object $owner_legal = this.getOwner_legal();
        result = result * PRIME + ($owner_legal == null ? 43 : $owner_legal.hashCode());
        final Object $owner_individual = this.getOwner_individual();
        result = result * PRIME + ($owner_individual == null ? 43 : $owner_individual.hashCode());
        return result;
    }

    public String toString() {
        return "ProvisionsDTO(provision_type=" + this.getProvision_type() + ", currency=" + this.getCurrency() + ", amount=" + this.getAmount() + ", provision_source=" + this.getProvision_source() + ", number=" + this.getNumber() + ", date=" + this.getDate() + ", name=" + this.getName() + ", mortgage=" + this.getMortgage() + ", vehicles=" + this.getVehicles() + ", securities=" + this.getSecurities() + ", guarantees_sureties=" + this.getGuarantees_sureties() + ", insurance_policies=" + this.getInsurance_policies() + ", deposits=" + this.getDeposits() + ", collateral=" + this.getCollateral() + ", owner_legal=" + this.getOwner_legal() + ", owner_individual=" + this.getOwner_individual() + ")";
    }

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

        public Mortgage() {
        }

        public String getProvision_id() {
            return this.provision_id;
        }

        public String getCadaster() {
            return this.cadaster;
        }

        public String getEstimate_amount() {
            return this.estimate_amount;
        }

        public String getCadastre_amount() {
            return this.cadastre_amount;
        }

        public String getPledge_amount() {
            return this.pledge_amount;
        }

        public String getYear() {
            return this.year;
        }

        public String getObject_country() {
            return this.object_country;
        }

        public String getObject_area() {
            return this.object_area;
        }

        public String getObject_region() {
            return this.object_region;
        }

        public String getEstimate_inn() {
            return this.estimate_inn;
        }

        public String getEstimate_name() {
            return this.estimate_name;
        }

        public String getEstimate_date() {
            return this.estimate_date;
        }

        public String getInsurance_inn() {
            return this.insurance_inn;
        }

        public String getInsurance_name() {
            return this.insurance_name;
        }

        public void setProvision_id(String provision_id) {
            this.provision_id = provision_id;
        }

        public void setCadaster(String cadaster) {
            this.cadaster = cadaster;
        }

        public void setEstimate_amount(String estimate_amount) {
            this.estimate_amount = estimate_amount;
        }

        public void setCadastre_amount(String cadastre_amount) {
            this.cadastre_amount = cadastre_amount;
        }

        public void setPledge_amount(String pledge_amount) {
            this.pledge_amount = pledge_amount;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public void setObject_country(String object_country) {
            this.object_country = object_country;
        }

        public void setObject_area(String object_area) {
            this.object_area = object_area;
        }

        public void setObject_region(String object_region) {
            this.object_region = object_region;
        }

        public void setEstimate_inn(String estimate_inn) {
            this.estimate_inn = estimate_inn;
        }

        public void setEstimate_name(String estimate_name) {
            this.estimate_name = estimate_name;
        }

        public void setEstimate_date(String estimate_date) {
            this.estimate_date = estimate_date;
        }

        public void setInsurance_inn(String insurance_inn) {
            this.insurance_inn = insurance_inn;
        }

        public void setInsurance_name(String insurance_name) {
            this.insurance_name = insurance_name;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Mortgage)) return false;
            final Mortgage other = (Mortgage) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$provision_id = this.getProvision_id();
            final Object other$provision_id = other.getProvision_id();
            if (this$provision_id == null ? other$provision_id != null : !this$provision_id.equals(other$provision_id))
                return false;
            final Object this$cadaster = this.getCadaster();
            final Object other$cadaster = other.getCadaster();
            if (this$cadaster == null ? other$cadaster != null : !this$cadaster.equals(other$cadaster)) return false;
            final Object this$estimate_amount = this.getEstimate_amount();
            final Object other$estimate_amount = other.getEstimate_amount();
            if (this$estimate_amount == null ? other$estimate_amount != null : !this$estimate_amount.equals(other$estimate_amount))
                return false;
            final Object this$cadastre_amount = this.getCadastre_amount();
            final Object other$cadastre_amount = other.getCadastre_amount();
            if (this$cadastre_amount == null ? other$cadastre_amount != null : !this$cadastre_amount.equals(other$cadastre_amount))
                return false;
            final Object this$pledge_amount = this.getPledge_amount();
            final Object other$pledge_amount = other.getPledge_amount();
            if (this$pledge_amount == null ? other$pledge_amount != null : !this$pledge_amount.equals(other$pledge_amount))
                return false;
            final Object this$year = this.getYear();
            final Object other$year = other.getYear();
            if (this$year == null ? other$year != null : !this$year.equals(other$year)) return false;
            final Object this$object_country = this.getObject_country();
            final Object other$object_country = other.getObject_country();
            if (this$object_country == null ? other$object_country != null : !this$object_country.equals(other$object_country))
                return false;
            final Object this$object_area = this.getObject_area();
            final Object other$object_area = other.getObject_area();
            if (this$object_area == null ? other$object_area != null : !this$object_area.equals(other$object_area))
                return false;
            final Object this$object_region = this.getObject_region();
            final Object other$object_region = other.getObject_region();
            if (this$object_region == null ? other$object_region != null : !this$object_region.equals(other$object_region))
                return false;
            final Object this$estimate_inn = this.getEstimate_inn();
            final Object other$estimate_inn = other.getEstimate_inn();
            if (this$estimate_inn == null ? other$estimate_inn != null : !this$estimate_inn.equals(other$estimate_inn))
                return false;
            final Object this$estimate_name = this.getEstimate_name();
            final Object other$estimate_name = other.getEstimate_name();
            if (this$estimate_name == null ? other$estimate_name != null : !this$estimate_name.equals(other$estimate_name))
                return false;
            final Object this$estimate_date = this.getEstimate_date();
            final Object other$estimate_date = other.getEstimate_date();
            if (this$estimate_date == null ? other$estimate_date != null : !this$estimate_date.equals(other$estimate_date))
                return false;
            final Object this$insurance_inn = this.getInsurance_inn();
            final Object other$insurance_inn = other.getInsurance_inn();
            if (this$insurance_inn == null ? other$insurance_inn != null : !this$insurance_inn.equals(other$insurance_inn))
                return false;
            final Object this$insurance_name = this.getInsurance_name();
            final Object other$insurance_name = other.getInsurance_name();
            if (this$insurance_name == null ? other$insurance_name != null : !this$insurance_name.equals(other$insurance_name))
                return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Mortgage;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $provision_id = this.getProvision_id();
            result = result * PRIME + ($provision_id == null ? 43 : $provision_id.hashCode());
            final Object $cadaster = this.getCadaster();
            result = result * PRIME + ($cadaster == null ? 43 : $cadaster.hashCode());
            final Object $estimate_amount = this.getEstimate_amount();
            result = result * PRIME + ($estimate_amount == null ? 43 : $estimate_amount.hashCode());
            final Object $cadastre_amount = this.getCadastre_amount();
            result = result * PRIME + ($cadastre_amount == null ? 43 : $cadastre_amount.hashCode());
            final Object $pledge_amount = this.getPledge_amount();
            result = result * PRIME + ($pledge_amount == null ? 43 : $pledge_amount.hashCode());
            final Object $year = this.getYear();
            result = result * PRIME + ($year == null ? 43 : $year.hashCode());
            final Object $object_country = this.getObject_country();
            result = result * PRIME + ($object_country == null ? 43 : $object_country.hashCode());
            final Object $object_area = this.getObject_area();
            result = result * PRIME + ($object_area == null ? 43 : $object_area.hashCode());
            final Object $object_region = this.getObject_region();
            result = result * PRIME + ($object_region == null ? 43 : $object_region.hashCode());
            final Object $estimate_inn = this.getEstimate_inn();
            result = result * PRIME + ($estimate_inn == null ? 43 : $estimate_inn.hashCode());
            final Object $estimate_name = this.getEstimate_name();
            result = result * PRIME + ($estimate_name == null ? 43 : $estimate_name.hashCode());
            final Object $estimate_date = this.getEstimate_date();
            result = result * PRIME + ($estimate_date == null ? 43 : $estimate_date.hashCode());
            final Object $insurance_inn = this.getInsurance_inn();
            result = result * PRIME + ($insurance_inn == null ? 43 : $insurance_inn.hashCode());
            final Object $insurance_name = this.getInsurance_name();
            result = result * PRIME + ($insurance_name == null ? 43 : $insurance_name.hashCode());
            return result;
        }

        public String toString() {
            return "ProvisionsDTO.Mortgage(provision_id=" + this.getProvision_id() + ", cadaster=" + this.getCadaster() + ", estimate_amount=" + this.getEstimate_amount() + ", cadastre_amount=" + this.getCadastre_amount() + ", pledge_amount=" + this.getPledge_amount() + ", year=" + this.getYear() + ", object_country=" + this.getObject_country() + ", object_area=" + this.getObject_area() + ", object_region=" + this.getObject_region() + ", estimate_inn=" + this.getEstimate_inn() + ", estimate_name=" + this.getEstimate_name() + ", estimate_date=" + this.getEstimate_date() + ", insurance_inn=" + this.getInsurance_inn() + ", insurance_name=" + this.getInsurance_name() + ")";
        }
    }

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
        private String estimate_date;
        private String insurance_inn;
        private String insurance_name;

        public Vehicle() {
        }

        public String getProvision_id() {
            return this.provision_id;
        }

        public String getEngine_number() {
            return this.engine_number;
        }

        public String getBody_number() {
            return this.body_number;
        }

        public String getYear() {
            return this.year;
        }

        public String getCountry() {
            return this.country;
        }

        public String getState_number() {
            return this.state_number;
        }

        public String getModel() {
            return this.model;
        }

        public String getChassis_number() {
            return this.chassis_number;
        }

        public String getColor() {
            return this.color;
        }

        public String getDoc_seria_number() {
            return this.doc_seria_number;
        }

        public String getVin_number() {
            return this.vin_number;
        }

        public String getEstimate_amount() {
            return this.estimate_amount;
        }

        public String getPledge_amount() {
            return this.pledge_amount;
        }

        public String getEstimate_inn() {
            return this.estimate_inn;
        }

        public String getEstimate_name() {
            return this.estimate_name;
        }

        public String getEstimate_date() {
            return this.estimate_date;
        }

        public String getInsurance_inn() {
            return this.insurance_inn;
        }

        public String getInsurance_name() {
            return this.insurance_name;
        }

        public void setProvision_id(String provision_id) {
            this.provision_id = provision_id;
        }

        public void setEngine_number(String engine_number) {
            this.engine_number = engine_number;
        }

        public void setBody_number(String body_number) {
            this.body_number = body_number;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setState_number(String state_number) {
            this.state_number = state_number;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public void setChassis_number(String chassis_number) {
            this.chassis_number = chassis_number;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setDoc_seria_number(String doc_seria_number) {
            this.doc_seria_number = doc_seria_number;
        }

        public void setVin_number(String vin_number) {
            this.vin_number = vin_number;
        }

        public void setEstimate_amount(String estimate_amount) {
            this.estimate_amount = estimate_amount;
        }

        public void setPledge_amount(String pledge_amount) {
            this.pledge_amount = pledge_amount;
        }

        public void setEstimate_inn(String estimate_inn) {
            this.estimate_inn = estimate_inn;
        }

        public void setEstimate_name(String estimate_name) {
            this.estimate_name = estimate_name;
        }

        public void setEstimate_date(String estimate_date) {
            this.estimate_date = estimate_date;
        }

        public void setInsurance_inn(String insurance_inn) {
            this.insurance_inn = insurance_inn;
        }

        public void setInsurance_name(String insurance_name) {
            this.insurance_name = insurance_name;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Vehicle)) return false;
            final Vehicle other = (Vehicle) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$provision_id = this.getProvision_id();
            final Object other$provision_id = other.getProvision_id();
            if (this$provision_id == null ? other$provision_id != null : !this$provision_id.equals(other$provision_id))
                return false;
            final Object this$engine_number = this.getEngine_number();
            final Object other$engine_number = other.getEngine_number();
            if (this$engine_number == null ? other$engine_number != null : !this$engine_number.equals(other$engine_number))
                return false;
            final Object this$body_number = this.getBody_number();
            final Object other$body_number = other.getBody_number();
            if (this$body_number == null ? other$body_number != null : !this$body_number.equals(other$body_number))
                return false;
            final Object this$year = this.getYear();
            final Object other$year = other.getYear();
            if (this$year == null ? other$year != null : !this$year.equals(other$year)) return false;
            final Object this$country = this.getCountry();
            final Object other$country = other.getCountry();
            if (this$country == null ? other$country != null : !this$country.equals(other$country)) return false;
            final Object this$state_number = this.getState_number();
            final Object other$state_number = other.getState_number();
            if (this$state_number == null ? other$state_number != null : !this$state_number.equals(other$state_number))
                return false;
            final Object this$model = this.getModel();
            final Object other$model = other.getModel();
            if (this$model == null ? other$model != null : !this$model.equals(other$model)) return false;
            final Object this$chassis_number = this.getChassis_number();
            final Object other$chassis_number = other.getChassis_number();
            if (this$chassis_number == null ? other$chassis_number != null : !this$chassis_number.equals(other$chassis_number))
                return false;
            final Object this$color = this.getColor();
            final Object other$color = other.getColor();
            if (this$color == null ? other$color != null : !this$color.equals(other$color)) return false;
            final Object this$doc_seria_number = this.getDoc_seria_number();
            final Object other$doc_seria_number = other.getDoc_seria_number();
            if (this$doc_seria_number == null ? other$doc_seria_number != null : !this$doc_seria_number.equals(other$doc_seria_number))
                return false;
            final Object this$vin_number = this.getVin_number();
            final Object other$vin_number = other.getVin_number();
            if (this$vin_number == null ? other$vin_number != null : !this$vin_number.equals(other$vin_number))
                return false;
            final Object this$estimate_amount = this.getEstimate_amount();
            final Object other$estimate_amount = other.getEstimate_amount();
            if (this$estimate_amount == null ? other$estimate_amount != null : !this$estimate_amount.equals(other$estimate_amount))
                return false;
            final Object this$pledge_amount = this.getPledge_amount();
            final Object other$pledge_amount = other.getPledge_amount();
            if (this$pledge_amount == null ? other$pledge_amount != null : !this$pledge_amount.equals(other$pledge_amount))
                return false;
            final Object this$estimate_inn = this.getEstimate_inn();
            final Object other$estimate_inn = other.getEstimate_inn();
            if (this$estimate_inn == null ? other$estimate_inn != null : !this$estimate_inn.equals(other$estimate_inn))
                return false;
            final Object this$estimate_name = this.getEstimate_name();
            final Object other$estimate_name = other.getEstimate_name();
            if (this$estimate_name == null ? other$estimate_name != null : !this$estimate_name.equals(other$estimate_name))
                return false;
            final Object this$estimate_date = this.getEstimate_date();
            final Object other$estimate_date = other.getEstimate_date();
            if (this$estimate_date == null ? other$estimate_date != null : !this$estimate_date.equals(other$estimate_date))
                return false;
            final Object this$insurance_inn = this.getInsurance_inn();
            final Object other$insurance_inn = other.getInsurance_inn();
            if (this$insurance_inn == null ? other$insurance_inn != null : !this$insurance_inn.equals(other$insurance_inn))
                return false;
            final Object this$insurance_name = this.getInsurance_name();
            final Object other$insurance_name = other.getInsurance_name();
            if (this$insurance_name == null ? other$insurance_name != null : !this$insurance_name.equals(other$insurance_name))
                return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Vehicle;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $provision_id = this.getProvision_id();
            result = result * PRIME + ($provision_id == null ? 43 : $provision_id.hashCode());
            final Object $engine_number = this.getEngine_number();
            result = result * PRIME + ($engine_number == null ? 43 : $engine_number.hashCode());
            final Object $body_number = this.getBody_number();
            result = result * PRIME + ($body_number == null ? 43 : $body_number.hashCode());
            final Object $year = this.getYear();
            result = result * PRIME + ($year == null ? 43 : $year.hashCode());
            final Object $country = this.getCountry();
            result = result * PRIME + ($country == null ? 43 : $country.hashCode());
            final Object $state_number = this.getState_number();
            result = result * PRIME + ($state_number == null ? 43 : $state_number.hashCode());
            final Object $model = this.getModel();
            result = result * PRIME + ($model == null ? 43 : $model.hashCode());
            final Object $chassis_number = this.getChassis_number();
            result = result * PRIME + ($chassis_number == null ? 43 : $chassis_number.hashCode());
            final Object $color = this.getColor();
            result = result * PRIME + ($color == null ? 43 : $color.hashCode());
            final Object $doc_seria_number = this.getDoc_seria_number();
            result = result * PRIME + ($doc_seria_number == null ? 43 : $doc_seria_number.hashCode());
            final Object $vin_number = this.getVin_number();
            result = result * PRIME + ($vin_number == null ? 43 : $vin_number.hashCode());
            final Object $estimate_amount = this.getEstimate_amount();
            result = result * PRIME + ($estimate_amount == null ? 43 : $estimate_amount.hashCode());
            final Object $pledge_amount = this.getPledge_amount();
            result = result * PRIME + ($pledge_amount == null ? 43 : $pledge_amount.hashCode());
            final Object $estimate_inn = this.getEstimate_inn();
            result = result * PRIME + ($estimate_inn == null ? 43 : $estimate_inn.hashCode());
            final Object $estimate_name = this.getEstimate_name();
            result = result * PRIME + ($estimate_name == null ? 43 : $estimate_name.hashCode());
            final Object $estimate_date = this.getEstimate_date();
            result = result * PRIME + ($estimate_date == null ? 43 : $estimate_date.hashCode());
            final Object $insurance_inn = this.getInsurance_inn();
            result = result * PRIME + ($insurance_inn == null ? 43 : $insurance_inn.hashCode());
            final Object $insurance_name = this.getInsurance_name();
            result = result * PRIME + ($insurance_name == null ? 43 : $insurance_name.hashCode());
            return result;
        }

        public String toString() {
            return "ProvisionsDTO.Vehicle(provision_id=" + this.getProvision_id() + ", engine_number=" + this.getEngine_number() + ", body_number=" + this.getBody_number() + ", year=" + this.getYear() + ", country=" + this.getCountry() + ", state_number=" + this.getState_number() + ", model=" + this.getModel() + ", chassis_number=" + this.getChassis_number() + ", color=" + this.getColor() + ", doc_seria_number=" + this.getDoc_seria_number() + ", vin_number=" + this.getVin_number() + ", estimate_amount=" + this.getEstimate_amount() + ", pledge_amount=" + this.getPledge_amount() + ", estimate_inn=" + this.getEstimate_inn() + ", estimate_name=" + this.getEstimate_name() + ", estimate_date=" + this.getEstimate_date() + ", insurance_inn=" + this.getInsurance_inn() + ", insurance_name=" + this.getInsurance_name() + ")";
        }
    }

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

        public Security() {
        }

        public String getProvision_id() {
            return this.provision_id;
        }

        public String getDate_begin() {
            return this.date_begin;
        }

        public String getDate_end() {
            return this.date_end;
        }

        public String getEmitent_resident() {
            return this.emitent_resident;
        }

        public String getEmitent_inn() {
            return this.emitent_inn;
        }

        public String getTotal_amount() {
            return this.total_amount;
        }

        public String getTotal_count() {
            return this.total_count;
        }

        public String getSingle_amount() {
            return this.single_amount;
        }

        public String getDiscount_amount() {
            return this.discount_amount;
        }

        public String getEmitent_name() {
            return this.emitent_name;
        }

        public void setProvision_id(String provision_id) {
            this.provision_id = provision_id;
        }

        public void setDate_begin(String date_begin) {
            this.date_begin = date_begin;
        }

        public void setDate_end(String date_end) {
            this.date_end = date_end;
        }

        public void setEmitent_resident(String emitent_resident) {
            this.emitent_resident = emitent_resident;
        }

        public void setEmitent_inn(String emitent_inn) {
            this.emitent_inn = emitent_inn;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public void setTotal_count(String total_count) {
            this.total_count = total_count;
        }

        public void setSingle_amount(String single_amount) {
            this.single_amount = single_amount;
        }

        public void setDiscount_amount(String discount_amount) {
            this.discount_amount = discount_amount;
        }

        public void setEmitent_name(String emitent_name) {
            this.emitent_name = emitent_name;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Security)) return false;
            final Security other = (Security) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$provision_id = this.getProvision_id();
            final Object other$provision_id = other.getProvision_id();
            if (this$provision_id == null ? other$provision_id != null : !this$provision_id.equals(other$provision_id))
                return false;
            final Object this$date_begin = this.getDate_begin();
            final Object other$date_begin = other.getDate_begin();
            if (this$date_begin == null ? other$date_begin != null : !this$date_begin.equals(other$date_begin))
                return false;
            final Object this$date_end = this.getDate_end();
            final Object other$date_end = other.getDate_end();
            if (this$date_end == null ? other$date_end != null : !this$date_end.equals(other$date_end)) return false;
            final Object this$emitent_resident = this.getEmitent_resident();
            final Object other$emitent_resident = other.getEmitent_resident();
            if (this$emitent_resident == null ? other$emitent_resident != null : !this$emitent_resident.equals(other$emitent_resident))
                return false;
            final Object this$emitent_inn = this.getEmitent_inn();
            final Object other$emitent_inn = other.getEmitent_inn();
            if (this$emitent_inn == null ? other$emitent_inn != null : !this$emitent_inn.equals(other$emitent_inn))
                return false;
            final Object this$total_amount = this.getTotal_amount();
            final Object other$total_amount = other.getTotal_amount();
            if (this$total_amount == null ? other$total_amount != null : !this$total_amount.equals(other$total_amount))
                return false;
            final Object this$total_count = this.getTotal_count();
            final Object other$total_count = other.getTotal_count();
            if (this$total_count == null ? other$total_count != null : !this$total_count.equals(other$total_count))
                return false;
            final Object this$single_amount = this.getSingle_amount();
            final Object other$single_amount = other.getSingle_amount();
            if (this$single_amount == null ? other$single_amount != null : !this$single_amount.equals(other$single_amount))
                return false;
            final Object this$discount_amount = this.getDiscount_amount();
            final Object other$discount_amount = other.getDiscount_amount();
            if (this$discount_amount == null ? other$discount_amount != null : !this$discount_amount.equals(other$discount_amount))
                return false;
            final Object this$emitent_name = this.getEmitent_name();
            final Object other$emitent_name = other.getEmitent_name();
            if (this$emitent_name == null ? other$emitent_name != null : !this$emitent_name.equals(other$emitent_name))
                return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Security;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $provision_id = this.getProvision_id();
            result = result * PRIME + ($provision_id == null ? 43 : $provision_id.hashCode());
            final Object $date_begin = this.getDate_begin();
            result = result * PRIME + ($date_begin == null ? 43 : $date_begin.hashCode());
            final Object $date_end = this.getDate_end();
            result = result * PRIME + ($date_end == null ? 43 : $date_end.hashCode());
            final Object $emitent_resident = this.getEmitent_resident();
            result = result * PRIME + ($emitent_resident == null ? 43 : $emitent_resident.hashCode());
            final Object $emitent_inn = this.getEmitent_inn();
            result = result * PRIME + ($emitent_inn == null ? 43 : $emitent_inn.hashCode());
            final Object $total_amount = this.getTotal_amount();
            result = result * PRIME + ($total_amount == null ? 43 : $total_amount.hashCode());
            final Object $total_count = this.getTotal_count();
            result = result * PRIME + ($total_count == null ? 43 : $total_count.hashCode());
            final Object $single_amount = this.getSingle_amount();
            result = result * PRIME + ($single_amount == null ? 43 : $single_amount.hashCode());
            final Object $discount_amount = this.getDiscount_amount();
            result = result * PRIME + ($discount_amount == null ? 43 : $discount_amount.hashCode());
            final Object $emitent_name = this.getEmitent_name();
            result = result * PRIME + ($emitent_name == null ? 43 : $emitent_name.hashCode());
            return result;
        }

        public String toString() {
            return "ProvisionsDTO.Security(provision_id=" + this.getProvision_id() + ", date_begin=" + this.getDate_begin() + ", date_end=" + this.getDate_end() + ", emitent_resident=" + this.getEmitent_resident() + ", emitent_inn=" + this.getEmitent_inn() + ", total_amount=" + this.getTotal_amount() + ", total_count=" + this.getTotal_count() + ", single_amount=" + this.getSingle_amount() + ", discount_amount=" + this.getDiscount_amount() + ", emitent_name=" + this.getEmitent_name() + ")";
        }
    }

    public static class GuaranteeSurety {
        private String provision_id;
        private String date_end;

        public GuaranteeSurety() {
        }

        public String getProvision_id() {
            return this.provision_id;
        }

        public String getDate_end() {
            return this.date_end;
        }

        public void setProvision_id(String provision_id) {
            this.provision_id = provision_id;
        }

        public void setDate_end(String date_end) {
            this.date_end = date_end;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof GuaranteeSurety)) return false;
            final GuaranteeSurety other = (GuaranteeSurety) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$provision_id = this.getProvision_id();
            final Object other$provision_id = other.getProvision_id();
            if (this$provision_id == null ? other$provision_id != null : !this$provision_id.equals(other$provision_id))
                return false;
            final Object this$date_end = this.getDate_end();
            final Object other$date_end = other.getDate_end();
            if (this$date_end == null ? other$date_end != null : !this$date_end.equals(other$date_end)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof GuaranteeSurety;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $provision_id = this.getProvision_id();
            result = result * PRIME + ($provision_id == null ? 43 : $provision_id.hashCode());
            final Object $date_end = this.getDate_end();
            result = result * PRIME + ($date_end == null ? 43 : $date_end.hashCode());
            return result;
        }

        public String toString() {
            return "ProvisionsDTO.GuaranteeSurety(provision_id=" + this.getProvision_id() + ", date_end=" + this.getDate_end() + ")";
        }
    }

    public static class InsurancePolicy {
        private String provision_id;
        private String insurance_name;
        private String insurance_inn;
        private String date_end;

        public InsurancePolicy() {
        }

        public String getProvision_id() {
            return this.provision_id;
        }

        public String getInsurance_name() {
            return this.insurance_name;
        }

        public String getInsurance_inn() {
            return this.insurance_inn;
        }

        public String getDate_end() {
            return this.date_end;
        }

        public void setProvision_id(String provision_id) {
            this.provision_id = provision_id;
        }

        public void setInsurance_name(String insurance_name) {
            this.insurance_name = insurance_name;
        }

        public void setInsurance_inn(String insurance_inn) {
            this.insurance_inn = insurance_inn;
        }

        public void setDate_end(String date_end) {
            this.date_end = date_end;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof InsurancePolicy)) return false;
            final InsurancePolicy other = (InsurancePolicy) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$provision_id = this.getProvision_id();
            final Object other$provision_id = other.getProvision_id();
            if (this$provision_id == null ? other$provision_id != null : !this$provision_id.equals(other$provision_id))
                return false;
            final Object this$insurance_name = this.getInsurance_name();
            final Object other$insurance_name = other.getInsurance_name();
            if (this$insurance_name == null ? other$insurance_name != null : !this$insurance_name.equals(other$insurance_name))
                return false;
            final Object this$insurance_inn = this.getInsurance_inn();
            final Object other$insurance_inn = other.getInsurance_inn();
            if (this$insurance_inn == null ? other$insurance_inn != null : !this$insurance_inn.equals(other$insurance_inn))
                return false;
            final Object this$date_end = this.getDate_end();
            final Object other$date_end = other.getDate_end();
            if (this$date_end == null ? other$date_end != null : !this$date_end.equals(other$date_end)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof InsurancePolicy;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $provision_id = this.getProvision_id();
            result = result * PRIME + ($provision_id == null ? 43 : $provision_id.hashCode());
            final Object $insurance_name = this.getInsurance_name();
            result = result * PRIME + ($insurance_name == null ? 43 : $insurance_name.hashCode());
            final Object $insurance_inn = this.getInsurance_inn();
            result = result * PRIME + ($insurance_inn == null ? 43 : $insurance_inn.hashCode());
            final Object $date_end = this.getDate_end();
            result = result * PRIME + ($date_end == null ? 43 : $date_end.hashCode());
            return result;
        }

        public String toString() {
            return "ProvisionsDTO.InsurancePolicy(provision_id=" + this.getProvision_id() + ", insurance_name=" + this.getInsurance_name() + ", insurance_inn=" + this.getInsurance_inn() + ", date_end=" + this.getDate_end() + ")";
        }
    }

    public static class Deposit {
        private String provision_id;
        private String date_end;
        private String bank_code;
        private String deposit_account;
        private String deposit_name;

        public Deposit() {
        }

        public String getProvision_id() {
            return this.provision_id;
        }

        public String getDate_end() {
            return this.date_end;
        }

        public String getBank_code() {
            return this.bank_code;
        }

        public String getDeposit_account() {
            return this.deposit_account;
        }

        public String getDeposit_name() {
            return this.deposit_name;
        }

        public void setProvision_id(String provision_id) {
            this.provision_id = provision_id;
        }

        public void setDate_end(String date_end) {
            this.date_end = date_end;
        }

        public void setBank_code(String bank_code) {
            this.bank_code = bank_code;
        }

        public void setDeposit_account(String deposit_account) {
            this.deposit_account = deposit_account;
        }

        public void setDeposit_name(String deposit_name) {
            this.deposit_name = deposit_name;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Deposit)) return false;
            final Deposit other = (Deposit) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$provision_id = this.getProvision_id();
            final Object other$provision_id = other.getProvision_id();
            if (this$provision_id == null ? other$provision_id != null : !this$provision_id.equals(other$provision_id))
                return false;
            final Object this$date_end = this.getDate_end();
            final Object other$date_end = other.getDate_end();
            if (this$date_end == null ? other$date_end != null : !this$date_end.equals(other$date_end)) return false;
            final Object this$bank_code = this.getBank_code();
            final Object other$bank_code = other.getBank_code();
            if (this$bank_code == null ? other$bank_code != null : !this$bank_code.equals(other$bank_code))
                return false;
            final Object this$deposit_account = this.getDeposit_account();
            final Object other$deposit_account = other.getDeposit_account();
            if (this$deposit_account == null ? other$deposit_account != null : !this$deposit_account.equals(other$deposit_account))
                return false;
            final Object this$deposit_name = this.getDeposit_name();
            final Object other$deposit_name = other.getDeposit_name();
            if (this$deposit_name == null ? other$deposit_name != null : !this$deposit_name.equals(other$deposit_name))
                return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Deposit;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $provision_id = this.getProvision_id();
            result = result * PRIME + ($provision_id == null ? 43 : $provision_id.hashCode());
            final Object $date_end = this.getDate_end();
            result = result * PRIME + ($date_end == null ? 43 : $date_end.hashCode());
            final Object $bank_code = this.getBank_code();
            result = result * PRIME + ($bank_code == null ? 43 : $bank_code.hashCode());
            final Object $deposit_account = this.getDeposit_account();
            result = result * PRIME + ($deposit_account == null ? 43 : $deposit_account.hashCode());
            final Object $deposit_name = this.getDeposit_name();
            result = result * PRIME + ($deposit_name == null ? 43 : $deposit_name.hashCode());
            return result;
        }

        public String toString() {
            return "ProvisionsDTO.Deposit(provision_id=" + this.getProvision_id() + ", date_end=" + this.getDate_end() + ", bank_code=" + this.getBank_code() + ", deposit_account=" + this.getDeposit_account() + ", deposit_name=" + this.getDeposit_name() + ")";
        }
    }

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

        public Collateral() {
        }

        public String getProvision_id() {
            return this.provision_id;
        }

        public String getEstimate_amount() {
            return this.estimate_amount;
        }

        public String getPledge_amount() {
            return this.pledge_amount;
        }

        public String getObject_name() {
            return this.object_name;
        }

        public String getYear() {
            return this.year;
        }

        public String getDate_receivables() {
            return this.date_receivables;
        }

        public String getObject_country() {
            return this.object_country;
        }

        public String getObject_area() {
            return this.object_area;
        }

        public String getObject_region() {
            return this.object_region;
        }

        public String getEstimate_inn() {
            return this.estimate_inn;
        }

        public String getEstimate_name() {
            return this.estimate_name;
        }

        public String getEstimate_date() {
            return this.estimate_date;
        }

        public String getInsurance_inn() {
            return this.insurance_inn;
        }

        public String getInsurance_name() {
            return this.insurance_name;
        }

        public void setProvision_id(String provision_id) {
            this.provision_id = provision_id;
        }

        public void setEstimate_amount(String estimate_amount) {
            this.estimate_amount = estimate_amount;
        }

        public void setPledge_amount(String pledge_amount) {
            this.pledge_amount = pledge_amount;
        }

        public void setObject_name(String object_name) {
            this.object_name = object_name;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public void setDate_receivables(String date_receivables) {
            this.date_receivables = date_receivables;
        }

        public void setObject_country(String object_country) {
            this.object_country = object_country;
        }

        public void setObject_area(String object_area) {
            this.object_area = object_area;
        }

        public void setObject_region(String object_region) {
            this.object_region = object_region;
        }

        public void setEstimate_inn(String estimate_inn) {
            this.estimate_inn = estimate_inn;
        }

        public void setEstimate_name(String estimate_name) {
            this.estimate_name = estimate_name;
        }

        public void setEstimate_date(String estimate_date) {
            this.estimate_date = estimate_date;
        }

        public void setInsurance_inn(String insurance_inn) {
            this.insurance_inn = insurance_inn;
        }

        public void setInsurance_name(String insurance_name) {
            this.insurance_name = insurance_name;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Collateral)) return false;
            final Collateral other = (Collateral) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$provision_id = this.getProvision_id();
            final Object other$provision_id = other.getProvision_id();
            if (this$provision_id == null ? other$provision_id != null : !this$provision_id.equals(other$provision_id))
                return false;
            final Object this$estimate_amount = this.getEstimate_amount();
            final Object other$estimate_amount = other.getEstimate_amount();
            if (this$estimate_amount == null ? other$estimate_amount != null : !this$estimate_amount.equals(other$estimate_amount))
                return false;
            final Object this$pledge_amount = this.getPledge_amount();
            final Object other$pledge_amount = other.getPledge_amount();
            if (this$pledge_amount == null ? other$pledge_amount != null : !this$pledge_amount.equals(other$pledge_amount))
                return false;
            final Object this$object_name = this.getObject_name();
            final Object other$object_name = other.getObject_name();
            if (this$object_name == null ? other$object_name != null : !this$object_name.equals(other$object_name))
                return false;
            final Object this$year = this.getYear();
            final Object other$year = other.getYear();
            if (this$year == null ? other$year != null : !this$year.equals(other$year)) return false;
            final Object this$date_receivables = this.getDate_receivables();
            final Object other$date_receivables = other.getDate_receivables();
            if (this$date_receivables == null ? other$date_receivables != null : !this$date_receivables.equals(other$date_receivables))
                return false;
            final Object this$object_country = this.getObject_country();
            final Object other$object_country = other.getObject_country();
            if (this$object_country == null ? other$object_country != null : !this$object_country.equals(other$object_country))
                return false;
            final Object this$object_area = this.getObject_area();
            final Object other$object_area = other.getObject_area();
            if (this$object_area == null ? other$object_area != null : !this$object_area.equals(other$object_area))
                return false;
            final Object this$object_region = this.getObject_region();
            final Object other$object_region = other.getObject_region();
            if (this$object_region == null ? other$object_region != null : !this$object_region.equals(other$object_region))
                return false;
            final Object this$estimate_inn = this.getEstimate_inn();
            final Object other$estimate_inn = other.getEstimate_inn();
            if (this$estimate_inn == null ? other$estimate_inn != null : !this$estimate_inn.equals(other$estimate_inn))
                return false;
            final Object this$estimate_name = this.getEstimate_name();
            final Object other$estimate_name = other.getEstimate_name();
            if (this$estimate_name == null ? other$estimate_name != null : !this$estimate_name.equals(other$estimate_name))
                return false;
            final Object this$estimate_date = this.getEstimate_date();
            final Object other$estimate_date = other.getEstimate_date();
            if (this$estimate_date == null ? other$estimate_date != null : !this$estimate_date.equals(other$estimate_date))
                return false;
            final Object this$insurance_inn = this.getInsurance_inn();
            final Object other$insurance_inn = other.getInsurance_inn();
            if (this$insurance_inn == null ? other$insurance_inn != null : !this$insurance_inn.equals(other$insurance_inn))
                return false;
            final Object this$insurance_name = this.getInsurance_name();
            final Object other$insurance_name = other.getInsurance_name();
            if (this$insurance_name == null ? other$insurance_name != null : !this$insurance_name.equals(other$insurance_name))
                return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Collateral;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $provision_id = this.getProvision_id();
            result = result * PRIME + ($provision_id == null ? 43 : $provision_id.hashCode());
            final Object $estimate_amount = this.getEstimate_amount();
            result = result * PRIME + ($estimate_amount == null ? 43 : $estimate_amount.hashCode());
            final Object $pledge_amount = this.getPledge_amount();
            result = result * PRIME + ($pledge_amount == null ? 43 : $pledge_amount.hashCode());
            final Object $object_name = this.getObject_name();
            result = result * PRIME + ($object_name == null ? 43 : $object_name.hashCode());
            final Object $year = this.getYear();
            result = result * PRIME + ($year == null ? 43 : $year.hashCode());
            final Object $date_receivables = this.getDate_receivables();
            result = result * PRIME + ($date_receivables == null ? 43 : $date_receivables.hashCode());
            final Object $object_country = this.getObject_country();
            result = result * PRIME + ($object_country == null ? 43 : $object_country.hashCode());
            final Object $object_area = this.getObject_area();
            result = result * PRIME + ($object_area == null ? 43 : $object_area.hashCode());
            final Object $object_region = this.getObject_region();
            result = result * PRIME + ($object_region == null ? 43 : $object_region.hashCode());
            final Object $estimate_inn = this.getEstimate_inn();
            result = result * PRIME + ($estimate_inn == null ? 43 : $estimate_inn.hashCode());
            final Object $estimate_name = this.getEstimate_name();
            result = result * PRIME + ($estimate_name == null ? 43 : $estimate_name.hashCode());
            final Object $estimate_date = this.getEstimate_date();
            result = result * PRIME + ($estimate_date == null ? 43 : $estimate_date.hashCode());
            final Object $insurance_inn = this.getInsurance_inn();
            result = result * PRIME + ($insurance_inn == null ? 43 : $insurance_inn.hashCode());
            final Object $insurance_name = this.getInsurance_name();
            result = result * PRIME + ($insurance_name == null ? 43 : $insurance_name.hashCode());
            return result;
        }

        public String toString() {
            return "ProvisionsDTO.Collateral(provision_id=" + this.getProvision_id() + ", estimate_amount=" + this.getEstimate_amount() + ", pledge_amount=" + this.getPledge_amount() + ", object_name=" + this.getObject_name() + ", year=" + this.getYear() + ", date_receivables=" + this.getDate_receivables() + ", object_country=" + this.getObject_country() + ", object_area=" + this.getObject_area() + ", object_region=" + this.getObject_region() + ", estimate_inn=" + this.getEstimate_inn() + ", estimate_name=" + this.getEstimate_name() + ", estimate_date=" + this.getEstimate_date() + ", insurance_inn=" + this.getInsurance_inn() + ", insurance_name=" + this.getInsurance_name() + ")";
        }
    }

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

        public OwnerLegal() {
        }

        public String getResident_code() {
            return this.resident_code;
        }

        public String getResident_inn() {
            return this.resident_inn;
        }

        public String getNibbd_code() {
            return this.nibbd_code;
        }

        public String getNoresident_inn() {
            return this.noresident_inn;
        }

        public String getName() {
            return this.name;
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

        public String getPost_address() {
            return this.post_address;
        }

        public String getPost_index() {
            return this.post_index;
        }

        public String getWeb_site() {
            return this.web_site;
        }

        public String getPhone() {
            return this.phone;
        }

        public String getEmail() {
            return this.email;
        }

        public void setResident_code(String resident_code) {
            this.resident_code = resident_code;
        }

        public void setResident_inn(String resident_inn) {
            this.resident_inn = resident_inn;
        }

        public void setNibbd_code(String nibbd_code) {
            this.nibbd_code = nibbd_code;
        }

        public void setNoresident_inn(String noresident_inn) {
            this.noresident_inn = noresident_inn;
        }

        public void setName(String name) {
            this.name = name;
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

        public void setPost_address(String post_address) {
            this.post_address = post_address;
        }

        public void setPost_index(String post_index) {
            this.post_index = post_index;
        }

        public void setWeb_site(String web_site) {
            this.web_site = web_site;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof OwnerLegal)) return false;
            final OwnerLegal other = (OwnerLegal) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$resident_code = this.getResident_code();
            final Object other$resident_code = other.getResident_code();
            if (this$resident_code == null ? other$resident_code != null : !this$resident_code.equals(other$resident_code))
                return false;
            final Object this$resident_inn = this.getResident_inn();
            final Object other$resident_inn = other.getResident_inn();
            if (this$resident_inn == null ? other$resident_inn != null : !this$resident_inn.equals(other$resident_inn))
                return false;
            final Object this$nibbd_code = this.getNibbd_code();
            final Object other$nibbd_code = other.getNibbd_code();
            if (this$nibbd_code == null ? other$nibbd_code != null : !this$nibbd_code.equals(other$nibbd_code))
                return false;
            final Object this$noresident_inn = this.getNoresident_inn();
            final Object other$noresident_inn = other.getNoresident_inn();
            if (this$noresident_inn == null ? other$noresident_inn != null : !this$noresident_inn.equals(other$noresident_inn))
                return false;
            final Object this$name = this.getName();
            final Object other$name = other.getName();
            if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
            final Object this$country = this.getCountry();
            final Object other$country = other.getCountry();
            if (this$country == null ? other$country != null : !this$country.equals(other$country)) return false;
            final Object this$area = this.getArea();
            final Object other$area = other.getArea();
            if (this$area == null ? other$area != null : !this$area.equals(other$area)) return false;
            final Object this$region = this.getRegion();
            final Object other$region = other.getRegion();
            if (this$region == null ? other$region != null : !this$region.equals(other$region)) return false;
            final Object this$post_address = this.getPost_address();
            final Object other$post_address = other.getPost_address();
            if (this$post_address == null ? other$post_address != null : !this$post_address.equals(other$post_address))
                return false;
            final Object this$post_index = this.getPost_index();
            final Object other$post_index = other.getPost_index();
            if (this$post_index == null ? other$post_index != null : !this$post_index.equals(other$post_index))
                return false;
            final Object this$web_site = this.getWeb_site();
            final Object other$web_site = other.getWeb_site();
            if (this$web_site == null ? other$web_site != null : !this$web_site.equals(other$web_site)) return false;
            final Object this$phone = this.getPhone();
            final Object other$phone = other.getPhone();
            if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
            final Object this$email = this.getEmail();
            final Object other$email = other.getEmail();
            if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof OwnerLegal;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $resident_code = this.getResident_code();
            result = result * PRIME + ($resident_code == null ? 43 : $resident_code.hashCode());
            final Object $resident_inn = this.getResident_inn();
            result = result * PRIME + ($resident_inn == null ? 43 : $resident_inn.hashCode());
            final Object $nibbd_code = this.getNibbd_code();
            result = result * PRIME + ($nibbd_code == null ? 43 : $nibbd_code.hashCode());
            final Object $noresident_inn = this.getNoresident_inn();
            result = result * PRIME + ($noresident_inn == null ? 43 : $noresident_inn.hashCode());
            final Object $name = this.getName();
            result = result * PRIME + ($name == null ? 43 : $name.hashCode());
            final Object $country = this.getCountry();
            result = result * PRIME + ($country == null ? 43 : $country.hashCode());
            final Object $area = this.getArea();
            result = result * PRIME + ($area == null ? 43 : $area.hashCode());
            final Object $region = this.getRegion();
            result = result * PRIME + ($region == null ? 43 : $region.hashCode());
            final Object $post_address = this.getPost_address();
            result = result * PRIME + ($post_address == null ? 43 : $post_address.hashCode());
            final Object $post_index = this.getPost_index();
            result = result * PRIME + ($post_index == null ? 43 : $post_index.hashCode());
            final Object $web_site = this.getWeb_site();
            result = result * PRIME + ($web_site == null ? 43 : $web_site.hashCode());
            final Object $phone = this.getPhone();
            result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
            final Object $email = this.getEmail();
            result = result * PRIME + ($email == null ? 43 : $email.hashCode());
            return result;
        }

        public String toString() {
            return "ProvisionsDTO.OwnerLegal(resident_code=" + this.getResident_code() + ", resident_inn=" + this.getResident_inn() + ", nibbd_code=" + this.getNibbd_code() + ", noresident_inn=" + this.getNoresident_inn() + ", name=" + this.getName() + ", country=" + this.getCountry() + ", area=" + this.getArea() + ", region=" + this.getRegion() + ", post_address=" + this.getPost_address() + ", post_index=" + this.getPost_index() + ", web_site=" + this.getWeb_site() + ", phone=" + this.getPhone() + ", email=" + this.getEmail() + ")";
        }
    }

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

        public OwnerIndividual() {
        }

        public String getResident_code() {
            return this.resident_code;
        }

        public String getPinfl() {
            return this.pinfl;
        }

        public String getBirth_date() {
            return this.birth_date;
        }

        public String getGender() {
            return this.gender;
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

        public String getSecond_name() {
            return this.second_name;
        }

        public String getFirst_name() {
            return this.first_name;
        }

        public String getPatronymic() {
            return this.patronymic;
        }

        public String getPost_address() {
            return this.post_address;
        }

        public String getPhone() {
            return this.phone;
        }

        public String getEmail() {
            return this.email;
        }

        public void setResident_code(String resident_code) {
            this.resident_code = resident_code;
        }

        public void setPinfl(String pinfl) {
            this.pinfl = pinfl;
        }

        public void setBirth_date(String birth_date) {
            this.birth_date = birth_date;
        }

        public void setGender(String gender) {
            this.gender = gender;
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

        public void setSecond_name(String second_name) {
            this.second_name = second_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public void setPatronymic(String patronymic) {
            this.patronymic = patronymic;
        }

        public void setPost_address(String post_address) {
            this.post_address = post_address;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof OwnerIndividual)) return false;
            final OwnerIndividual other = (OwnerIndividual) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$resident_code = this.getResident_code();
            final Object other$resident_code = other.getResident_code();
            if (this$resident_code == null ? other$resident_code != null : !this$resident_code.equals(other$resident_code))
                return false;
            final Object this$pinfl = this.getPinfl();
            final Object other$pinfl = other.getPinfl();
            if (this$pinfl == null ? other$pinfl != null : !this$pinfl.equals(other$pinfl)) return false;
            final Object this$birth_date = this.getBirth_date();
            final Object other$birth_date = other.getBirth_date();
            if (this$birth_date == null ? other$birth_date != null : !this$birth_date.equals(other$birth_date))
                return false;
            final Object this$gender = this.getGender();
            final Object other$gender = other.getGender();
            if (this$gender == null ? other$gender != null : !this$gender.equals(other$gender)) return false;
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
            if (this$doc_seria == null ? other$doc_seria != null : !this$doc_seria.equals(other$doc_seria))
                return false;
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
            final Object this$post_address = this.getPost_address();
            final Object other$post_address = other.getPost_address();
            if (this$post_address == null ? other$post_address != null : !this$post_address.equals(other$post_address))
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
            return other instanceof OwnerIndividual;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $resident_code = this.getResident_code();
            result = result * PRIME + ($resident_code == null ? 43 : $resident_code.hashCode());
            final Object $pinfl = this.getPinfl();
            result = result * PRIME + ($pinfl == null ? 43 : $pinfl.hashCode());
            final Object $birth_date = this.getBirth_date();
            result = result * PRIME + ($birth_date == null ? 43 : $birth_date.hashCode());
            final Object $gender = this.getGender();
            result = result * PRIME + ($gender == null ? 43 : $gender.hashCode());
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
            final Object $second_name = this.getSecond_name();
            result = result * PRIME + ($second_name == null ? 43 : $second_name.hashCode());
            final Object $first_name = this.getFirst_name();
            result = result * PRIME + ($first_name == null ? 43 : $first_name.hashCode());
            final Object $patronymic = this.getPatronymic();
            result = result * PRIME + ($patronymic == null ? 43 : $patronymic.hashCode());
            final Object $post_address = this.getPost_address();
            result = result * PRIME + ($post_address == null ? 43 : $post_address.hashCode());
            final Object $phone = this.getPhone();
            result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
            final Object $email = this.getEmail();
            result = result * PRIME + ($email == null ? 43 : $email.hashCode());
            return result;
        }

        public String toString() {
            return "ProvisionsDTO.OwnerIndividual(resident_code=" + this.getResident_code() + ", pinfl=" + this.getPinfl() + ", birth_date=" + this.getBirth_date() + ", gender=" + this.getGender() + ", country=" + this.getCountry() + ", area=" + this.getArea() + ", region=" + this.getRegion() + ", doc_type=" + this.getDoc_type() + ", doc_seria=" + this.getDoc_seria() + ", doc_number=" + this.getDoc_number() + ", doc_date=" + this.getDoc_date() + ", doc_issuer=" + this.getDoc_issuer() + ", second_name=" + this.getSecond_name() + ", first_name=" + this.getFirst_name() + ", patronymic=" + this.getPatronymic() + ", post_address=" + this.getPost_address() + ", phone=" + this.getPhone() + ", email=" + this.getEmail() + ")";
        }
    }

}
