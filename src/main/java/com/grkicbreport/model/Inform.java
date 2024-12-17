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

    @Column(name = "grki_password")
    private String grki_password;

    public Inform(String numks, String name, String fioDirektor) {
        this.numks = numks;
        this.name = name;
        this.fioDirektor = fioDirektor;
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

    public String getGrki_password() {
        return this.grki_password;
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

    public void setGrki_password(String grki_password) {
        this.grki_password = grki_password;
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
        final Object this$grki_password = this.getGrki_password();
        final Object other$grki_password = other.getGrki_password();
        if (this$grki_password == null ? other$grki_password != null : !this$grki_password.equals(other$grki_password))
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
        final Object $grki_password = this.getGrki_password();
        result = result * PRIME + ($grki_password == null ? 43 : $grki_password.hashCode());
        return result;
    }

    public String toString() {
        return "Inform(numks=" + this.getNumks() + ", name=" + this.getName() + ", fioDirektor=" + this.getFioDirektor() + ", grki_password=" + this.getGrki_password() + ")";
    }
}
