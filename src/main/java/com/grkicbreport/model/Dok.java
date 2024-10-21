package com.grkicbreport.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "dok")
public class Dok {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kod")
    private Long kod;

    @Column(name = "numdok", nullable = false, columnDefinition = "nchar(10)")
    private String numdok;

    @Column(name = "dats", nullable = false)
    private LocalDate dats;

    @Column(name = "tipdoc", columnDefinition = "tinyint")
    private int tipdoc;

    @Column(name = "ls", nullable = false, columnDefinition = "nchar(20)")
    private String ls;

    @Column(name = "lscor", nullable = false, columnDefinition = "nchar(20)")
    private String lscor;

    @Column(name = "nazn", nullable = false)
    private String nazn;

    @Column(name = "sost", columnDefinition = "tinyint")
    private int sost;

    @Column(name = "users", columnDefinition = "smallint")
    private int users;

    @Column(name = "dtime", nullable = false)
    private LocalDateTime dtime;

    @Column(name = "sums", nullable = false)
    private BigDecimal sums;

    @Column(name = "dat_prov")
    private Date datProv;

    @Column(name = "datsproc")
    private LocalDate datsProc;

    @Column(name = "koduch", nullable = false, columnDefinition = "nchar(8)")
    private String kodUch;

    @Column(name = "prixgroup")
    private Integer prixGroup;

    @Column(name = "groupstr", columnDefinition = "tinyint")
    private Integer groupStr;


    public Dok(Long kod, String numdok, LocalDate dats, int tipdoc, String ls, String lscor, String nazn, int sost, int users, LocalDateTime dtime, BigDecimal sums, Date datProv, LocalDate datsProc, String kodUch, Integer prixGroup, Integer groupStr) {
        this.kod = kod;
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
        this.datsProc = datsProc;
        this.kodUch = kodUch;
        this.prixGroup = prixGroup;
        this.groupStr = groupStr;
    }

    public Dok() {
    }

    public Long getKod() {
        return this.kod;
    }

    public String getNumdok() {
        return this.numdok;
    }

    public LocalDate getDats() {
        return this.dats;
    }

    public int getTipdoc() {
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

    public int getSost() {
        return this.sost;
    }

    public int getUsers() {
        return this.users;
    }

    public LocalDateTime getDtime() {
        return this.dtime;
    }

    public BigDecimal getSums() {
        return this.sums;
    }

    public Date getDatProv() {
        return this.datProv;
    }

    public LocalDate getDatsProc() {
        return this.datsProc;
    }

    public String getKodUch() {
        return this.kodUch;
    }

    public Integer getPrixGroup() {
        return this.prixGroup;
    }

    public Integer getGroupStr() {
        return this.groupStr;
    }

    public void setKod(Long kod) {
        this.kod = kod;
    }

    public void setNumdok(String numdok) {
        this.numdok = numdok;
    }

    public void setDats(LocalDate dats) {
        this.dats = dats;
    }

    public void setTipdoc(int tipdoc) {
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

    public void setSost(int sost) {
        this.sost = sost;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public void setDtime(LocalDateTime dtime) {
        this.dtime = dtime;
    }

    public void setSums(BigDecimal sums) {
        this.sums = sums;
    }

    public void setDatProv(Date datProv) {
        this.datProv = datProv;
    }

    public void setDatsProc(LocalDate datsProc) {
        this.datsProc = datsProc;
    }

    public void setKodUch(String kodUch) {
        this.kodUch = kodUch;
    }

    public void setPrixGroup(Integer prixGroup) {
        this.prixGroup = prixGroup;
    }

    public void setGroupStr(Integer groupStr) {
        this.groupStr = groupStr;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Dok)) return false;
        final Dok other = (Dok) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$kod = this.getKod();
        final Object other$kod = other.getKod();
        if (this$kod == null ? other$kod != null : !this$kod.equals(other$kod)) return false;
        final Object this$numdok = this.getNumdok();
        final Object other$numdok = other.getNumdok();
        if (this$numdok == null ? other$numdok != null : !this$numdok.equals(other$numdok)) return false;
        final Object this$dats = this.getDats();
        final Object other$dats = other.getDats();
        if (this$dats == null ? other$dats != null : !this$dats.equals(other$dats)) return false;
        if (this.getTipdoc() != other.getTipdoc()) return false;
        final Object this$ls = this.getLs();
        final Object other$ls = other.getLs();
        if (this$ls == null ? other$ls != null : !this$ls.equals(other$ls)) return false;
        final Object this$lscor = this.getLscor();
        final Object other$lscor = other.getLscor();
        if (this$lscor == null ? other$lscor != null : !this$lscor.equals(other$lscor)) return false;
        final Object this$nazn = this.getNazn();
        final Object other$nazn = other.getNazn();
        if (this$nazn == null ? other$nazn != null : !this$nazn.equals(other$nazn)) return false;
        if (this.getSost() != other.getSost()) return false;
        if (this.getUsers() != other.getUsers()) return false;
        final Object this$dtime = this.getDtime();
        final Object other$dtime = other.getDtime();
        if (this$dtime == null ? other$dtime != null : !this$dtime.equals(other$dtime)) return false;
        final Object this$sums = this.getSums();
        final Object other$sums = other.getSums();
        if (this$sums == null ? other$sums != null : !this$sums.equals(other$sums)) return false;
        final Object this$datProv = this.getDatProv();
        final Object other$datProv = other.getDatProv();
        if (this$datProv == null ? other$datProv != null : !this$datProv.equals(other$datProv)) return false;
        final Object this$datsProc = this.getDatsProc();
        final Object other$datsProc = other.getDatsProc();
        if (this$datsProc == null ? other$datsProc != null : !this$datsProc.equals(other$datsProc)) return false;
        final Object this$kodUch = this.getKodUch();
        final Object other$kodUch = other.getKodUch();
        if (this$kodUch == null ? other$kodUch != null : !this$kodUch.equals(other$kodUch)) return false;
        final Object this$prixGroup = this.getPrixGroup();
        final Object other$prixGroup = other.getPrixGroup();
        if (this$prixGroup == null ? other$prixGroup != null : !this$prixGroup.equals(other$prixGroup)) return false;
        final Object this$groupStr = this.getGroupStr();
        final Object other$groupStr = other.getGroupStr();
        if (this$groupStr == null ? other$groupStr != null : !this$groupStr.equals(other$groupStr)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Dok;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $kod = this.getKod();
        result = result * PRIME + ($kod == null ? 43 : $kod.hashCode());
        final Object $numdok = this.getNumdok();
        result = result * PRIME + ($numdok == null ? 43 : $numdok.hashCode());
        final Object $dats = this.getDats();
        result = result * PRIME + ($dats == null ? 43 : $dats.hashCode());
        result = result * PRIME + this.getTipdoc();
        final Object $ls = this.getLs();
        result = result * PRIME + ($ls == null ? 43 : $ls.hashCode());
        final Object $lscor = this.getLscor();
        result = result * PRIME + ($lscor == null ? 43 : $lscor.hashCode());
        final Object $nazn = this.getNazn();
        result = result * PRIME + ($nazn == null ? 43 : $nazn.hashCode());
        result = result * PRIME + this.getSost();
        result = result * PRIME + this.getUsers();
        final Object $dtime = this.getDtime();
        result = result * PRIME + ($dtime == null ? 43 : $dtime.hashCode());
        final Object $sums = this.getSums();
        result = result * PRIME + ($sums == null ? 43 : $sums.hashCode());
        final Object $datProv = this.getDatProv();
        result = result * PRIME + ($datProv == null ? 43 : $datProv.hashCode());
        final Object $datsProc = this.getDatsProc();
        result = result * PRIME + ($datsProc == null ? 43 : $datsProc.hashCode());
        final Object $kodUch = this.getKodUch();
        result = result * PRIME + ($kodUch == null ? 43 : $kodUch.hashCode());
        final Object $prixGroup = this.getPrixGroup();
        result = result * PRIME + ($prixGroup == null ? 43 : $prixGroup.hashCode());
        final Object $groupStr = this.getGroupStr();
        result = result * PRIME + ($groupStr == null ? 43 : $groupStr.hashCode());
        return result;
    }

    public String toString() {
        return "Dok(kod=" + this.getKod() + ", numdok=" + this.getNumdok() + ", dats=" + this.getDats() + ", tipdoc=" + this.getTipdoc() + ", ls=" + this.getLs() + ", lscor=" + this.getLscor() + ", nazn=" + this.getNazn() + ", sost=" + this.getSost() + ", users=" + this.getUsers() + ", dtime=" + this.getDtime() + ", sums=" + this.getSums() + ", datProv=" + this.getDatProv() + ", datsProc=" + this.getDatsProc() + ", kodUch=" + this.getKodUch() + ", prixGroup=" + this.getPrixGroup() + ", groupStr=" + this.getGroupStr() + ")";
    }
}