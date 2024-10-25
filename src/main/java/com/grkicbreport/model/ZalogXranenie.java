package com.grkicbreport.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "zalog_xranenie")
public class ZalogXranenie {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "data_priem")
    private LocalDate data_priem;
    @Column(name = "data_vozvrat")
    private LocalDate data_vozvrat;

    @ManyToOne
    @JoinColumn(name = "kod_dog", referencedColumnName = "kod_dog")
    private Kredit kredit;

    public ZalogXranenie(Long id, LocalDate data_priem, LocalDate data_vozvrat, Kredit kredit) {
        this.id = id;
        this.data_priem = data_priem;
        this.data_vozvrat = data_vozvrat;
        this.kredit = kredit;
    }

    public ZalogXranenie() {
    }

    public Long getId() {
        return this.id;
    }

    public LocalDate getData_priem() {
        return this.data_priem;
    }

    public LocalDate getData_vozvrat() {
        return this.data_vozvrat;
    }

    public Kredit getKredit() {
        return this.kredit;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setData_priem(LocalDate data_priem) {
        this.data_priem = data_priem;
    }

    public void setData_vozvrat(LocalDate data_vozvrat) {
        this.data_vozvrat = data_vozvrat;
    }

    public void setKredit(Kredit kredit) {
        this.kredit = kredit;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ZalogXranenie)) return false;
        final ZalogXranenie other = (ZalogXranenie) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$data_priem = this.getData_priem();
        final Object other$data_priem = other.getData_priem();
        if (this$data_priem == null ? other$data_priem != null : !this$data_priem.equals(other$data_priem))
            return false;
        final Object this$data_vozvrat = this.getData_vozvrat();
        final Object other$data_vozvrat = other.getData_vozvrat();
        if (this$data_vozvrat == null ? other$data_vozvrat != null : !this$data_vozvrat.equals(other$data_vozvrat))
            return false;
        final Object this$kredit = this.getKredit();
        final Object other$kredit = other.getKredit();
        if (this$kredit == null ? other$kredit != null : !this$kredit.equals(other$kredit)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ZalogXranenie;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $data_priem = this.getData_priem();
        result = result * PRIME + ($data_priem == null ? 43 : $data_priem.hashCode());
        final Object $data_vozvrat = this.getData_vozvrat();
        result = result * PRIME + ($data_vozvrat == null ? 43 : $data_vozvrat.hashCode());
        final Object $kredit = this.getKredit();
        result = result * PRIME + ($kredit == null ? 43 : $kredit.hashCode());
        return result;
    }

    public String toString() {
        return "ZalogXranenie(id=" + this.getId() + ", data_priem=" + this.getData_priem() + ", data_vozvrat=" + this.getData_vozvrat() + ", kredit=" + this.getKredit() + ")";
    }
}

