package com.grkicbreport.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "dokum")
public class Dokument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kod")
    private Long id;

    @Column(name = "numdok", nullable = false, length = 10)
    private String numdok;

    @Column(name = "dats", nullable = false)
    private LocalDate dats;

    @Column(name = "tipdoc", nullable = false)
    private Byte tipdoc;

    @Column(name = "ls", nullable = false, length = 20)
    private String ls;

    @Column(name = "lscor", nullable = false, length = 20)
    private String lscor;

    @Column(name = "nazn", nullable = false, length = 160)
    private String nazn;

    @Column(name = "sost", nullable = false)
    private Byte sost;

    @Column(name = "users", nullable = false)
    private Short users;

    @Column(name = "dtime", nullable = false)
    private LocalDateTime dtime = LocalDateTime.now();

    @Column(name = "sums", precision = 18, scale = 2)
    private BigDecimal sums;

    @Column(name = "dat_prov")
    private LocalDate datProv;

    @Column(name = "kod_dog", nullable = false, columnDefinition = "int default 0")
    private Integer kodDog = 0;

    @Column(name = "datsproc")
    private LocalDate datsproc;

    @Column(name = "koduch", length = 8)
    private String koduch;

    @Column(name = "prixgroup")
    private Integer prixgroup;

    @Column(name = "groupstr")
    private Byte groupstr;


    public Dokument(Long id, String numdok, LocalDate dats, Byte tipdoc, String ls, String lscor, String nazn, Byte sost, Short users, LocalDateTime dtime, BigDecimal sums, LocalDate datProv, Integer kodDog, LocalDate datsproc, String koduch, Integer prixgroup, Byte groupstr) {
        this.id = id;
        this.numdok = numdok;
        this.dats = dats;
        this.tipdoc = tipdoc;
        this.ls = ls;
        this.lscor = lscor;
        this.nazn = nazn;
        this.sost = sost;
        this.users = users;
        this.dtime = dtime;
        this.sums = sums;
        this.datProv = datProv;
        this.kodDog = kodDog;
        this.datsproc = datsproc;
        this.koduch = koduch;
        this.prixgroup = prixgroup;
        this.groupstr = groupstr;
    }

    public Dokument() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNumdok() {
        return this.numdok;
    }

    public LocalDate getDats() {
        return this.dats;
    }

    public Byte getTipdoc() {
        return this.tipdoc;
    }

    public String getLs() {
        return this.ls;
    }

    public String getLscor() {
        return this.lscor;
    }

    public String getNazn() {
        return this.nazn;
    }

    public Byte getSost() {
        return this.sost;
    }

    public Short getUsers() {
        return this.users;
    }

    public LocalDateTime getDtime() {
        return this.dtime;
    }

    public BigDecimal getSums() {
        return this.sums;
    }

    public LocalDate getDatProv() {
        return this.datProv;
    }

    public Integer getKodDog() {
        return this.kodDog;
    }

    public LocalDate getDatsproc() {
        return this.datsproc;
    }

    public String getKoduch() {
        return this.koduch;
    }

    public Integer getPrixgroup() {
        return this.prixgroup;
    }

    public Byte getGroupstr() {
        return this.groupstr;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumdok(String numdok) {
        this.numdok = numdok;
    }

    public void setDats(LocalDate dats) {
        this.dats = dats;
    }

    public void setTipdoc(Byte tipdoc) {
        this.tipdoc = tipdoc;
    }

    public void setLs(String ls) {
        this.ls = ls;
    }

    public void setLscor(String lscor) {
        this.lscor = lscor;
    }

    public void setNazn(String nazn) {
        this.nazn = nazn;
    }

    public void setSost(Byte sost) {
        this.sost = sost;
    }

    public void setUsers(Short users) {
        this.users = users;
    }

    public void setDtime(LocalDateTime dtime) {
        this.dtime = dtime;
    }

    public void setSums(BigDecimal sums) {
        this.sums = sums;
    }

    public void setDatProv(LocalDate datProv) {
        this.datProv = datProv;
    }

    public void setKodDog(Integer kodDog) {
        this.kodDog = kodDog;
    }

    public void setDatsproc(LocalDate datsproc) {
        this.datsproc = datsproc;
    }

    public void setKoduch(String koduch) {
        this.koduch = koduch;
    }

    public void setPrixgroup(Integer prixgroup) {
        this.prixgroup = prixgroup;
    }

    public void setGroupstr(Byte groupstr) {
        this.groupstr = groupstr;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Dokument)) return false;
        final Dokument other = (Dokument) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$numdok = this.getNumdok();
        final Object other$numdok = other.getNumdok();
        if (this$numdok == null ? other$numdok != null : !this$numdok.equals(other$numdok)) return false;
        final Object this$dats = this.getDats();
        final Object other$dats = other.getDats();
        if (this$dats == null ? other$dats != null : !this$dats.equals(other$dats)) return false;
        final Object this$tipdoc = this.getTipdoc();
        final Object other$tipdoc = other.getTipdoc();
        if (this$tipdoc == null ? other$tipdoc != null : !this$tipdoc.equals(other$tipdoc)) return false;
        final Object this$ls = this.getLs();
        final Object other$ls = other.getLs();
        if (this$ls == null ? other$ls != null : !this$ls.equals(other$ls)) return false;
        final Object this$lscor = this.getLscor();
        final Object other$lscor = other.getLscor();
        if (this$lscor == null ? other$lscor != null : !this$lscor.equals(other$lscor)) return false;
        final Object this$nazn = this.getNazn();
        final Object other$nazn = other.getNazn();
        if (this$nazn == null ? other$nazn != null : !this$nazn.equals(other$nazn)) return false;
        final Object this$sost = this.getSost();
        final Object other$sost = other.getSost();
        if (this$sost == null ? other$sost != null : !this$sost.equals(other$sost)) return false;
        final Object this$users = this.getUsers();
        final Object other$users = other.getUsers();
        if (this$users == null ? other$users != null : !this$users.equals(other$users)) return false;
        final Object this$dtime = this.getDtime();
        final Object other$dtime = other.getDtime();
        if (this$dtime == null ? other$dtime != null : !this$dtime.equals(other$dtime)) return false;
        final Object this$sums = this.getSums();
        final Object other$sums = other.getSums();
        if (this$sums == null ? other$sums != null : !this$sums.equals(other$sums)) return false;
        final Object this$datProv = this.getDatProv();
        final Object other$datProv = other.getDatProv();
        if (this$datProv == null ? other$datProv != null : !this$datProv.equals(other$datProv)) return false;
        final Object this$kodDog = this.getKodDog();
        final Object other$kodDog = other.getKodDog();
        if (this$kodDog == null ? other$kodDog != null : !this$kodDog.equals(other$kodDog)) return false;
        final Object this$datsproc = this.getDatsproc();
        final Object other$datsproc = other.getDatsproc();
        if (this$datsproc == null ? other$datsproc != null : !this$datsproc.equals(other$datsproc)) return false;
        final Object this$koduch = this.getKoduch();
        final Object other$koduch = other.getKoduch();
        if (this$koduch == null ? other$koduch != null : !this$koduch.equals(other$koduch)) return false;
        final Object this$prixgroup = this.getPrixgroup();
        final Object other$prixgroup = other.getPrixgroup();
        if (this$prixgroup == null ? other$prixgroup != null : !this$prixgroup.equals(other$prixgroup)) return false;
        final Object this$groupstr = this.getGroupstr();
        final Object other$groupstr = other.getGroupstr();
        if (this$groupstr == null ? other$groupstr != null : !this$groupstr.equals(other$groupstr)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Dokument;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $numdok = this.getNumdok();
        result = result * PRIME + ($numdok == null ? 43 : $numdok.hashCode());
        final Object $dats = this.getDats();
        result = result * PRIME + ($dats == null ? 43 : $dats.hashCode());
        final Object $tipdoc = this.getTipdoc();
        result = result * PRIME + ($tipdoc == null ? 43 : $tipdoc.hashCode());
        final Object $ls = this.getLs();
        result = result * PRIME + ($ls == null ? 43 : $ls.hashCode());
        final Object $lscor = this.getLscor();
        result = result * PRIME + ($lscor == null ? 43 : $lscor.hashCode());
        final Object $nazn = this.getNazn();
        result = result * PRIME + ($nazn == null ? 43 : $nazn.hashCode());
        final Object $sost = this.getSost();
        result = result * PRIME + ($sost == null ? 43 : $sost.hashCode());
        final Object $users = this.getUsers();
        result = result * PRIME + ($users == null ? 43 : $users.hashCode());
        final Object $dtime = this.getDtime();
        result = result * PRIME + ($dtime == null ? 43 : $dtime.hashCode());
        final Object $sums = this.getSums();
        result = result * PRIME + ($sums == null ? 43 : $sums.hashCode());
        final Object $datProv = this.getDatProv();
        result = result * PRIME + ($datProv == null ? 43 : $datProv.hashCode());
        final Object $kodDog = this.getKodDog();
        result = result * PRIME + ($kodDog == null ? 43 : $kodDog.hashCode());
        final Object $datsproc = this.getDatsproc();
        result = result * PRIME + ($datsproc == null ? 43 : $datsproc.hashCode());
        final Object $koduch = this.getKoduch();
        result = result * PRIME + ($koduch == null ? 43 : $koduch.hashCode());
        final Object $prixgroup = this.getPrixgroup();
        result = result * PRIME + ($prixgroup == null ? 43 : $prixgroup.hashCode());
        final Object $groupstr = this.getGroupstr();
        result = result * PRIME + ($groupstr == null ? 43 : $groupstr.hashCode());
        return result;
    }

    public String toString() {
        return "Dokument(id=" + this.getId() + ", numdok=" + this.getNumdok() + ", dats=" + this.getDats() + ", tipdoc=" + this.getTipdoc() + ", ls=" + this.getLs() + ", lscor=" + this.getLscor() + ", nazn=" + this.getNazn() + ", sost=" + this.getSost() + ", users=" + this.getUsers() + ", dtime=" + this.getDtime() + ", sums=" + this.getSums() + ", datProv=" + this.getDatProv() + ", kodDog=" + this.getKodDog() + ", datsproc=" + this.getDatsproc() + ", koduch=" + this.getKoduch() + ", prixgroup=" + this.getPrixgroup() + ", groupstr=" + this.getGroupstr() + ")";
    }
}
