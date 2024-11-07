package com.grkicbreport.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "azolik_fiz")
public class AzolikFiz {

    @Column(name = "kodchlen", nullable = false, columnDefinition = "nchar(8)")
    private String kodchlen;

    @Column(name = "name")
    private String name;

    @Column(name = "inn", nullable = false, columnDefinition = "nchar()")
    private String inn;

    @Column(name = "adres")
    private String adres;

    @Column(name = "telmobil", nullable = false, columnDefinition = "nchar()")
    private String telmobil;

    @Column(name = "telhome", nullable = false, columnDefinition = "nchar()")
    private String telhome;

    @Column(name = "ser_num_pasp")
    private String serNumPasp;

    @Column(name = "ser_pasp")
    private String ser_pasp;

    @Column(name = "num_pasp")
    private String num_pasp;

    @Column(name = "kem_pasp")
    private String kem_pasp;

    @Column(name = "vidan_pasp")
    private LocalDate vidanPasp;

    @Column(name = "dats_rojd")
    private LocalDate datsRojd;

    @Column(name = "fsobst")
    private Byte fsobst;

    @Column(name = "users")
    private Short users;

    @Transient
    private Byte status;


    @Column(name = "lsvznos", nullable = false, columnDefinition = "nchar()")
    private String lsvznos;

    @Column(name = "indpred")
    private Byte indpred;

    @Column(name = "kodObl", nullable = false, columnDefinition = "nchar()")
    private String kodObl;

    @Column(name = "kodRayon", nullable = false, columnDefinition = "nchar()")
    private String kodRayon;

    @Column(name = "fam")
    private String fam;

    @Column(name = "imya")
    private String imya;

    @Column(name = "otch")
    private String otch;

    @Column(name = "kodPension", nullable = false, columnDefinition = "nchar()")
    private String kodPension;

    @Column(name = "tipDok", nullable = false, columnDefinition = "nchar()")
    private String tipDok;

    @Column(name = "dats_izm")
    private LocalDate datsIzm;

    @Column(name = "paspdo")
    private LocalDate paspdo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public AzolikFiz(String kodchlen, String name, String inn, String adres, String telmobil, String telhome, String serNumPasp, String ser_pasp, String num_pasp, String kem_pasp, LocalDate vidanPasp, LocalDate datsRojd, Byte fsobst, Short users, Byte status, String lsvznos, Byte indpred, String kodObl, String kodRayon, String fam, String imya, String otch, String kodPension, String tipDok, LocalDate datsIzm, LocalDate paspdo, Integer id) {
        this.kodchlen = kodchlen;
        this.name = name;
        this.inn = inn;
        this.adres = adres;
        this.telmobil = telmobil;
        this.telhome = telhome;
        this.serNumPasp = serNumPasp;
        this.ser_pasp = ser_pasp;
        this.num_pasp = num_pasp;
        this.kem_pasp = kem_pasp;
        this.vidanPasp = vidanPasp;
        this.datsRojd = datsRojd;
        this.fsobst = fsobst;
        this.users = users;
        this.status = status;
        this.lsvznos = lsvznos;
        this.indpred = indpred;
        this.kodObl = kodObl;
        this.kodRayon = kodRayon;
        this.fam = fam;
        this.imya = imya;
        this.otch = otch;
        this.kodPension = kodPension;
        this.tipDok = tipDok;
        this.datsIzm = datsIzm;
        this.paspdo = paspdo;
        this.id = id;
    }

    public AzolikFiz() {
    }

    public String getKodchlen() {
        return this.kodchlen;
    }

    public String getName() {
        return this.name;
    }

    public String getInn() {
        return this.inn;
    }

    public String getAdres() {
        return this.adres;
    }

    public String getTelmobil() {
        return this.telmobil;
    }

    public String getTelhome() {
        return this.telhome;
    }

    public String getSerNumPasp() {
        return this.serNumPasp;
    }

    public String getSer_pasp() {
        return this.ser_pasp;
    }

    public String getNum_pasp() {
        return this.num_pasp;
    }

    public String getKem_pasp() {
        return this.kem_pasp;
    }

    public LocalDate getVidanPasp() {
        return this.vidanPasp;
    }

    public LocalDate getDatsRojd() {
        return this.datsRojd;
    }

    public Byte getFsobst() {
        return this.fsobst;
    }

    public Short getUsers() {
        return this.users;
    }

    public Byte getStatus() {
        return this.status;
    }

    public String getLsvznos() {
        return this.lsvznos;
    }

    public Byte getIndpred() {
        return this.indpred;
    }

    public String getKodObl() {
        return this.kodObl;
    }

    public String getKodRayon() {
        return this.kodRayon;
    }

    public String getFam() {
        return this.fam;
    }

    public String getImya() {
        return this.imya;
    }

    public String getOtch() {
        return this.otch;
    }

    public String getKodPension() {
        return this.kodPension;
    }

    public String getTipDok() {
        return this.tipDok;
    }

    public LocalDate getDatsIzm() {
        return this.datsIzm;
    }

    public LocalDate getPaspdo() {
        return this.paspdo;
    }

    public Integer getId() {
        return this.id;
    }

    public void setKodchlen(String kodchlen) {
        this.kodchlen = kodchlen;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setTelmobil(String telmobil) {
        this.telmobil = telmobil;
    }

    public void setTelhome(String telhome) {
        this.telhome = telhome;
    }

    public void setSerNumPasp(String serNumPasp) {
        this.serNumPasp = serNumPasp;
    }

    public void setSer_pasp(String ser_pasp) {
        this.ser_pasp = ser_pasp;
    }

    public void setNum_pasp(String num_pasp) {
        this.num_pasp = num_pasp;
    }

    public void setKem_pasp(String kem_pasp) {
        this.kem_pasp = kem_pasp;
    }

    public void setVidanPasp(LocalDate vidanPasp) {
        this.vidanPasp = vidanPasp;
    }

    public void setDatsRojd(LocalDate datsRojd) {
        this.datsRojd = datsRojd;
    }

    public void setFsobst(Byte fsobst) {
        this.fsobst = fsobst;
    }

    public void setUsers(Short users) {
        this.users = users;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public void setLsvznos(String lsvznos) {
        this.lsvznos = lsvznos;
    }

    public void setIndpred(Byte indpred) {
        this.indpred = indpred;
    }

    public void setKodObl(String kodObl) {
        this.kodObl = kodObl;
    }

    public void setKodRayon(String kodRayon) {
        this.kodRayon = kodRayon;
    }

    public void setFam(String fam) {
        this.fam = fam;
    }

    public void setImya(String imya) {
        this.imya = imya;
    }

    public void setOtch(String otch) {
        this.otch = otch;
    }

    public void setKodPension(String kodPension) {
        this.kodPension = kodPension;
    }

    public void setTipDok(String tipDok) {
        this.tipDok = tipDok;
    }

    public void setDatsIzm(LocalDate datsIzm) {
        this.datsIzm = datsIzm;
    }

    public void setPaspdo(LocalDate paspdo) {
        this.paspdo = paspdo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AzolikFiz)) return false;
        final AzolikFiz other = (AzolikFiz) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$kodchlen = this.getKodchlen();
        final Object other$kodchlen = other.getKodchlen();
        if (this$kodchlen == null ? other$kodchlen != null : !this$kodchlen.equals(other$kodchlen)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$inn = this.getInn();
        final Object other$inn = other.getInn();
        if (this$inn == null ? other$inn != null : !this$inn.equals(other$inn)) return false;
        final Object this$adres = this.getAdres();
        final Object other$adres = other.getAdres();
        if (this$adres == null ? other$adres != null : !this$adres.equals(other$adres)) return false;
        final Object this$telmobil = this.getTelmobil();
        final Object other$telmobil = other.getTelmobil();
        if (this$telmobil == null ? other$telmobil != null : !this$telmobil.equals(other$telmobil)) return false;
        final Object this$telhome = this.getTelhome();
        final Object other$telhome = other.getTelhome();
        if (this$telhome == null ? other$telhome != null : !this$telhome.equals(other$telhome)) return false;
        final Object this$serNumPasp = this.getSerNumPasp();
        final Object other$serNumPasp = other.getSerNumPasp();
        if (this$serNumPasp == null ? other$serNumPasp != null : !this$serNumPasp.equals(other$serNumPasp))
            return false;
        final Object this$ser_pasp = this.getSer_pasp();
        final Object other$ser_pasp = other.getSer_pasp();
        if (this$ser_pasp == null ? other$ser_pasp != null : !this$ser_pasp.equals(other$ser_pasp)) return false;
        final Object this$num_pasp = this.getNum_pasp();
        final Object other$num_pasp = other.getNum_pasp();
        if (this$num_pasp == null ? other$num_pasp != null : !this$num_pasp.equals(other$num_pasp)) return false;
        final Object this$kem_pasp = this.getKem_pasp();
        final Object other$kem_pasp = other.getKem_pasp();
        if (this$kem_pasp == null ? other$kem_pasp != null : !this$kem_pasp.equals(other$kem_pasp)) return false;
        final Object this$vidanPasp = this.getVidanPasp();
        final Object other$vidanPasp = other.getVidanPasp();
        if (this$vidanPasp == null ? other$vidanPasp != null : !this$vidanPasp.equals(other$vidanPasp)) return false;
        final Object this$datsRojd = this.getDatsRojd();
        final Object other$datsRojd = other.getDatsRojd();
        if (this$datsRojd == null ? other$datsRojd != null : !this$datsRojd.equals(other$datsRojd)) return false;
        final Object this$fsobst = this.getFsobst();
        final Object other$fsobst = other.getFsobst();
        if (this$fsobst == null ? other$fsobst != null : !this$fsobst.equals(other$fsobst)) return false;
        final Object this$users = this.getUsers();
        final Object other$users = other.getUsers();
        if (this$users == null ? other$users != null : !this$users.equals(other$users)) return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$lsvznos = this.getLsvznos();
        final Object other$lsvznos = other.getLsvznos();
        if (this$lsvznos == null ? other$lsvznos != null : !this$lsvznos.equals(other$lsvznos)) return false;
        final Object this$indpred = this.getIndpred();
        final Object other$indpred = other.getIndpred();
        if (this$indpred == null ? other$indpred != null : !this$indpred.equals(other$indpred)) return false;
        final Object this$kodObl = this.getKodObl();
        final Object other$kodObl = other.getKodObl();
        if (this$kodObl == null ? other$kodObl != null : !this$kodObl.equals(other$kodObl)) return false;
        final Object this$kodRayon = this.getKodRayon();
        final Object other$kodRayon = other.getKodRayon();
        if (this$kodRayon == null ? other$kodRayon != null : !this$kodRayon.equals(other$kodRayon)) return false;
        final Object this$fam = this.getFam();
        final Object other$fam = other.getFam();
        if (this$fam == null ? other$fam != null : !this$fam.equals(other$fam)) return false;
        final Object this$imya = this.getImya();
        final Object other$imya = other.getImya();
        if (this$imya == null ? other$imya != null : !this$imya.equals(other$imya)) return false;
        final Object this$otch = this.getOtch();
        final Object other$otch = other.getOtch();
        if (this$otch == null ? other$otch != null : !this$otch.equals(other$otch)) return false;
        final Object this$kodPension = this.getKodPension();
        final Object other$kodPension = other.getKodPension();
        if (this$kodPension == null ? other$kodPension != null : !this$kodPension.equals(other$kodPension))
            return false;
        final Object this$tipDok = this.getTipDok();
        final Object other$tipDok = other.getTipDok();
        if (this$tipDok == null ? other$tipDok != null : !this$tipDok.equals(other$tipDok)) return false;
        final Object this$datsIzm = this.getDatsIzm();
        final Object other$datsIzm = other.getDatsIzm();
        if (this$datsIzm == null ? other$datsIzm != null : !this$datsIzm.equals(other$datsIzm)) return false;
        final Object this$paspdo = this.getPaspdo();
        final Object other$paspdo = other.getPaspdo();
        if (this$paspdo == null ? other$paspdo != null : !this$paspdo.equals(other$paspdo)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AzolikFiz;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $kodchlen = this.getKodchlen();
        result = result * PRIME + ($kodchlen == null ? 43 : $kodchlen.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $inn = this.getInn();
        result = result * PRIME + ($inn == null ? 43 : $inn.hashCode());
        final Object $adres = this.getAdres();
        result = result * PRIME + ($adres == null ? 43 : $adres.hashCode());
        final Object $telmobil = this.getTelmobil();
        result = result * PRIME + ($telmobil == null ? 43 : $telmobil.hashCode());
        final Object $telhome = this.getTelhome();
        result = result * PRIME + ($telhome == null ? 43 : $telhome.hashCode());
        final Object $serNumPasp = this.getSerNumPasp();
        result = result * PRIME + ($serNumPasp == null ? 43 : $serNumPasp.hashCode());
        final Object $ser_pasp = this.getSer_pasp();
        result = result * PRIME + ($ser_pasp == null ? 43 : $ser_pasp.hashCode());
        final Object $num_pasp = this.getNum_pasp();
        result = result * PRIME + ($num_pasp == null ? 43 : $num_pasp.hashCode());
        final Object $kem_pasp = this.getKem_pasp();
        result = result * PRIME + ($kem_pasp == null ? 43 : $kem_pasp.hashCode());
        final Object $vidanPasp = this.getVidanPasp();
        result = result * PRIME + ($vidanPasp == null ? 43 : $vidanPasp.hashCode());
        final Object $datsRojd = this.getDatsRojd();
        result = result * PRIME + ($datsRojd == null ? 43 : $datsRojd.hashCode());
        final Object $fsobst = this.getFsobst();
        result = result * PRIME + ($fsobst == null ? 43 : $fsobst.hashCode());
        final Object $users = this.getUsers();
        result = result * PRIME + ($users == null ? 43 : $users.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $lsvznos = this.getLsvznos();
        result = result * PRIME + ($lsvznos == null ? 43 : $lsvznos.hashCode());
        final Object $indpred = this.getIndpred();
        result = result * PRIME + ($indpred == null ? 43 : $indpred.hashCode());
        final Object $kodObl = this.getKodObl();
        result = result * PRIME + ($kodObl == null ? 43 : $kodObl.hashCode());
        final Object $kodRayon = this.getKodRayon();
        result = result * PRIME + ($kodRayon == null ? 43 : $kodRayon.hashCode());
        final Object $fam = this.getFam();
        result = result * PRIME + ($fam == null ? 43 : $fam.hashCode());
        final Object $imya = this.getImya();
        result = result * PRIME + ($imya == null ? 43 : $imya.hashCode());
        final Object $otch = this.getOtch();
        result = result * PRIME + ($otch == null ? 43 : $otch.hashCode());
        final Object $kodPension = this.getKodPension();
        result = result * PRIME + ($kodPension == null ? 43 : $kodPension.hashCode());
        final Object $tipDok = this.getTipDok();
        result = result * PRIME + ($tipDok == null ? 43 : $tipDok.hashCode());
        final Object $datsIzm = this.getDatsIzm();
        result = result * PRIME + ($datsIzm == null ? 43 : $datsIzm.hashCode());
        final Object $paspdo = this.getPaspdo();
        result = result * PRIME + ($paspdo == null ? 43 : $paspdo.hashCode());
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }

    public String toString() {
        return "AzolikFiz(kodchlen=" + this.getKodchlen() + ", name=" + this.getName() + ", inn=" + this.getInn() + ", adres=" + this.getAdres() + ", telmobil=" + this.getTelmobil() + ", telhome=" + this.getTelhome() + ", serNumPasp=" + this.getSerNumPasp() + ", ser_pasp=" + this.getSer_pasp() + ", num_pasp=" + this.getNum_pasp() + ", kem_pasp=" + this.getKem_pasp() + ", vidanPasp=" + this.getVidanPasp() + ", datsRojd=" + this.getDatsRojd() + ", fsobst=" + this.getFsobst() + ", users=" + this.getUsers() + ", status=" + this.getStatus() + ", lsvznos=" + this.getLsvznos() + ", indpred=" + this.getIndpred() + ", kodObl=" + this.getKodObl() + ", kodRayon=" + this.getKodRayon() + ", fam=" + this.getFam() + ", imya=" + this.getImya() + ", otch=" + this.getOtch() + ", kodPension=" + this.getKodPension() + ", tipDok=" + this.getTipDok() + ", datsIzm=" + this.getDatsIzm() + ", paspdo=" + this.getPaspdo() + ", id=" + this.getId() + ")";
    }
}

