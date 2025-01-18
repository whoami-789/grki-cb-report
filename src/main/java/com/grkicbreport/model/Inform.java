package com.grkicbreport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inform")
public class Inform {

    @Id
    @Column(name = "numks", nullable = false, length = 5)
    private String numks;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "fio_direktor", length = 60)
    private String fioDirektor;

    @Column(name = "inn", columnDefinition = "nchar(9)")
    private String inn;

    @Column(name = "grki_password")
    private String grki_password;

    @Column(name = "grki_file_path")
    private String grki_file_path;

    public Inform(String numks, String name, String fioDirektor, String inn, String grki_password, String grki_file_path) {
        this.numks = numks;
        this.name = name;
        this.fioDirektor = fioDirektor;
        this.inn = inn;
        this.grki_password = grki_password;
        this.grki_file_path = grki_file_path;
    }

    public Inform() {

    }

    protected boolean canEqual(final Object other) {
        return other instanceof Inform;
    }


    public String getNumks() {
        return this.numks;
    }

    public String getName() {
        return this.name;
    }

    public String getFioDirektor() {
        return this.fioDirektor;
    }

    public String getInn() {
        return this.inn;
    }

    public String getGrki_password() {
        return this.grki_password;
    }

    public String getGrki_file_path() {
        return this.grki_file_path;
    }

    public void setNumks(String numks) {
        this.numks = numks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFioDirektor(String fioDirektor) {
        this.fioDirektor = fioDirektor;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setGrki_password(String grki_password) {
        this.grki_password = grki_password;
    }

    public void setGrki_file_path(String grki_file_path) {
        this.grki_file_path = grki_file_path;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Inform)) return false;
        final Inform other = (Inform) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$numks = this.getNumks();
        final Object other$numks = other.getNumks();
        if (this$numks == null ? other$numks != null : !this$numks.equals(other$numks)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$fioDirektor = this.getFioDirektor();
        final Object other$fioDirektor = other.getFioDirektor();
        if (this$fioDirektor == null ? other$fioDirektor != null : !this$fioDirektor.equals(other$fioDirektor))
            return false;
        final Object this$inn = this.getInn();
        final Object other$inn = other.getInn();
        if (this$inn == null ? other$inn != null : !this$inn.equals(other$inn)) return false;
        final Object this$grki_password = this.getGrki_password();
        final Object other$grki_password = other.getGrki_password();
        if (this$grki_password == null ? other$grki_password != null : !this$grki_password.equals(other$grki_password))
            return false;
        final Object this$grki_file_path = this.getGrki_file_path();
        final Object other$grki_file_path = other.getGrki_file_path();
        if (this$grki_file_path == null ? other$grki_file_path != null : !this$grki_file_path.equals(other$grki_file_path))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $numks = this.getNumks();
        result = result * PRIME + ($numks == null ? 43 : $numks.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $fioDirektor = this.getFioDirektor();
        result = result * PRIME + ($fioDirektor == null ? 43 : $fioDirektor.hashCode());
        final Object $inn = this.getInn();
        result = result * PRIME + ($inn == null ? 43 : $inn.hashCode());
        final Object $grki_password = this.getGrki_password();
        result = result * PRIME + ($grki_password == null ? 43 : $grki_password.hashCode());
        final Object $grki_file_path = this.getGrki_file_path();
        result = result * PRIME + ($grki_file_path == null ? 43 : $grki_file_path.hashCode());
        return result;
    }

    public String toString() {
        return "Inform(numks=" + this.getNumks() + ", name=" + this.getName() + ", fioDirektor=" + this.getFioDirektor() + ", inn=" + this.getInn() + ", grki_password=" + this.getGrki_password() + ", grki_file_path=" + this.getGrki_file_path() + ")";
    }
}