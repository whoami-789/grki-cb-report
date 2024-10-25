package com.grkicbreport.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "azolik_yur")
public class AzolikYur {

    @Id
    @Column(name = "kodchlen", nullable = false, columnDefinition = "nchar(8)")
    private String kodchlen;

    @Column(name = "dats")
    private LocalDate dats;

    @Column(name = "name")
    private String name;

    @Column(name = "shotname")
    private String shotname;

    @Column(name = "soogu")
    private String soogu;

    @Column(name = "soato")
    private String soato;

    @Column(name = "inn", nullable = false, columnDefinition = "nchar()")
    private String inn;

    @Column(name = "adres")
    private String adres;

    @Column(name = "telmobil")
    private String telmobil;

    @Column(name = "telhome")
    private String telhome;

    @Column(name = "vznos")
    private BigDecimal vznos;

    @Column(name = "sost")
    private Byte sost;

    @Transient
    private Byte status;

    @Column(name = "ls")
    private String ls;

    @Column(name = "mfo")
    private String mfo;

    @Column(name = "fsobst")
    private Byte fsobst;

    @Column(name = "min_vo")
    private Short minVo;

    @Column(name = "ad_email")
    private String adEmail;

    @Column(name = "fio_director")
    private String fioDirector;

    @Column(name = "obraz_director")
    private Byte obrazDirector;

    @Column(name = "dats_rojd")
    private LocalDate datsRojd;

    @Column(name = "svyaz")
    private Byte svyaz;

    @Column(name = "dats_zakr")
    private LocalDate datsZakr;

    @Column(name = "users")
    private Short users;

    @Column(name = "kod_organ")
    private Byte kodOrgan;

    @Column(name = "num_registr")
    private Integer numRegistr;

    @Column(name = "dats_registr")
    private LocalDate datsRegistr;

    @Column(name = "prim")
    private String prim;

    @Column(name = "tec_vznos")
    private BigDecimal tecVznos;

    @Column(name = "lsvznos", nullable = false, columnDefinition = "nchar()")
    private String lsvznos;

    @Column(name = "dtime")
    private Date dtime;

    @Column(name = "indpred")
    private Byte indpred;

    @Column(name = "photo")
    private byte[] photo;

    @Transient
    private Integer kodSubject;

    @Column(name = "fio_buxg")
    private String fioBuxg;

    @Column(name = "tipZayom", nullable = false, columnDefinition = "nchar()")
    private String tipZayom;

    @Column(name = "sost_registr")
    private Byte sostRegistr;

    @Transient
    private Integer sysNumber;

    @Column(name = "dats_izm")
    private LocalDate datsIzm;

    @Column(name = "uchred")
    private Byte uchred;

    @Column(name = "kodObl", nullable = false, columnDefinition = "nchar()")
    private String kodObl;

    @Column(name = "kodRayon", nullable = false, columnDefinition = "nchar()")
    private String kodRayon;

    public AzolikYur(String kodchlen, LocalDate dats, String name, String shotname, String soogu, String soato, String inn, String adres, String telmobil, String telhome, BigDecimal vznos, Byte sost, Byte status, String ls, String mfo, Byte fsobst, Short minVo, String adEmail, String fioDirector, Byte obrazDirector, LocalDate datsRojd, Byte svyaz, LocalDate datsZakr, Short users, Byte kodOrgan, Integer numRegistr, LocalDate datsRegistr, String prim, BigDecimal tecVznos, String lsvznos, Date dtime, Byte indpred, byte[] photo, Integer kodSubject, String fioBuxg, String tipZayom, Byte sostRegistr, Integer sysNumber, LocalDate datsIzm, Byte uchred, String kodObl, String kodRayon) {
        this.kodchlen = kodchlen;
        this.dats = dats;
        this.name = name;
        this.shotname = shotname;
        this.soogu = soogu;
        this.soato = soato;
        this.inn = inn;
        this.adres = adres;
        this.telmobil = telmobil;
        this.telhome = telhome;
        this.vznos = vznos;
        this.sost = sost;
        this.status = status;
        this.ls = ls;
        this.mfo = mfo;
        this.fsobst = fsobst;
        this.minVo = minVo;
        this.adEmail = adEmail;
        this.fioDirector = fioDirector;
        this.obrazDirector = obrazDirector;
        this.datsRojd = datsRojd;
        this.svyaz = svyaz;
        this.datsZakr = datsZakr;
        this.users = users;
        this.kodOrgan = kodOrgan;
        this.numRegistr = numRegistr;
        this.datsRegistr = datsRegistr;
        this.prim = prim;
        this.tecVznos = tecVznos;
        this.lsvznos = lsvznos;
        this.dtime = dtime;
        this.indpred = indpred;
        this.photo = photo;
        this.kodSubject = kodSubject;
        this.fioBuxg = fioBuxg;
        this.tipZayom = tipZayom;
        this.sostRegistr = sostRegistr;
        this.sysNumber = sysNumber;
        this.datsIzm = datsIzm;
        this.uchred = uchred;
        this.kodObl = kodObl;
        this.kodRayon = kodRayon;
    }

    public AzolikYur() {
    }

    public String getKodchlen() {
        return this.kodchlen;
    }

    public LocalDate getDats() {
        return this.dats;
    }

    public String getName() {
        return this.name;
    }

    public String getShotname() {
        return this.shotname;
    }

    public String getSoogu() {
        return this.soogu;
    }

    public String getSoato() {
        return this.soato;
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

    public BigDecimal getVznos() {
        return this.vznos;
    }

    public Byte getSost() {
        return this.sost;
    }

    public Byte getStatus() {
        return this.status;
    }

    public String getLs() {
        return this.ls;
    }

    public String getMfo() {
        return this.mfo;
    }

    public Byte getFsobst() {
        return this.fsobst;
    }

    public Short getMinVo() {
        return this.minVo;
    }

    public String getAdEmail() {
        return this.adEmail;
    }

    public String getFioDirector() {
        return this.fioDirector;
    }

    public Byte getObrazDirector() {
        return this.obrazDirector;
    }

    public LocalDate getDatsRojd() {
        return this.datsRojd;
    }

    public Byte getSvyaz() {
        return this.svyaz;
    }

    public LocalDate getDatsZakr() {
        return this.datsZakr;
    }

    public Short getUsers() {
        return this.users;
    }

    public Byte getKodOrgan() {
        return this.kodOrgan;
    }

    public Integer getNumRegistr() {
        return this.numRegistr;
    }

    public LocalDate getDatsRegistr() {
        return this.datsRegistr;
    }

    public String getPrim() {
        return this.prim;
    }

    public BigDecimal getTecVznos() {
        return this.tecVznos;
    }

    public String getLsvznos() {
        return this.lsvznos;
    }

    public Date getDtime() {
        return this.dtime;
    }

    public Byte getIndpred() {
        return this.indpred;
    }

    public byte[] getPhoto() {
        return this.photo;
    }

    public Integer getKodSubject() {
        return this.kodSubject;
    }

    public String getFioBuxg() {
        return this.fioBuxg;
    }

    public String getTipZayom() {
        return this.tipZayom;
    }

    public Byte getSostRegistr() {
        return this.sostRegistr;
    }

    public Integer getSysNumber() {
        return this.sysNumber;
    }

    public LocalDate getDatsIzm() {
        return this.datsIzm;
    }

    public Byte getUchred() {
        return this.uchred;
    }

    public String getKodObl() {
        return this.kodObl;
    }

    public String getKodRayon() {
        return this.kodRayon;
    }

    public void setKodchlen(String kodchlen) {
        this.kodchlen = kodchlen;
    }

    public void setDats(LocalDate dats) {
        this.dats = dats;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShotname(String shotname) {
        this.shotname = shotname;
    }

    public void setSoogu(String soogu) {
        this.soogu = soogu;
    }

    public void setSoato(String soato) {
        this.soato = soato;
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

    public void setVznos(BigDecimal vznos) {
        this.vznos = vznos;
    }

    public void setSost(Byte sost) {
        this.sost = sost;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public void setLs(String ls) {
        this.ls = ls;
    }

    public void setMfo(String mfo) {
        this.mfo = mfo;
    }

    public void setFsobst(Byte fsobst) {
        this.fsobst = fsobst;
    }

    public void setMinVo(Short minVo) {
        this.minVo = minVo;
    }

    public void setAdEmail(String adEmail) {
        this.adEmail = adEmail;
    }

    public void setFioDirector(String fioDirector) {
        this.fioDirector = fioDirector;
    }

    public void setObrazDirector(Byte obrazDirector) {
        this.obrazDirector = obrazDirector;
    }

    public void setDatsRojd(LocalDate datsRojd) {
        this.datsRojd = datsRojd;
    }

    public void setSvyaz(Byte svyaz) {
        this.svyaz = svyaz;
    }

    public void setDatsZakr(LocalDate datsZakr) {
        this.datsZakr = datsZakr;
    }

    public void setUsers(Short users) {
        this.users = users;
    }

    public void setKodOrgan(Byte kodOrgan) {
        this.kodOrgan = kodOrgan;
    }

    public void setNumRegistr(Integer numRegistr) {
        this.numRegistr = numRegistr;
    }

    public void setDatsRegistr(LocalDate datsRegistr) {
        this.datsRegistr = datsRegistr;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

    public void setTecVznos(BigDecimal tecVznos) {
        this.tecVznos = tecVznos;
    }

    public void setLsvznos(String lsvznos) {
        this.lsvznos = lsvznos;
    }

    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    public void setIndpred(Byte indpred) {
        this.indpred = indpred;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setKodSubject(Integer kodSubject) {
        this.kodSubject = kodSubject;
    }

    public void setFioBuxg(String fioBuxg) {
        this.fioBuxg = fioBuxg;
    }

    public void setTipZayom(String tipZayom) {
        this.tipZayom = tipZayom;
    }

    public void setSostRegistr(Byte sostRegistr) {
        this.sostRegistr = sostRegistr;
    }

    public void setSysNumber(Integer sysNumber) {
        this.sysNumber = sysNumber;
    }

    public void setDatsIzm(LocalDate datsIzm) {
        this.datsIzm = datsIzm;
    }

    public void setUchred(Byte uchred) {
        this.uchred = uchred;
    }

    public void setKodObl(String kodObl) {
        this.kodObl = kodObl;
    }

    public void setKodRayon(String kodRayon) {
        this.kodRayon = kodRayon;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AzolikYur)) return false;
        final AzolikYur other = (AzolikYur) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$kodchlen = this.getKodchlen();
        final Object other$kodchlen = other.getKodchlen();
        if (this$kodchlen == null ? other$kodchlen != null : !this$kodchlen.equals(other$kodchlen)) return false;
        final Object this$dats = this.getDats();
        final Object other$dats = other.getDats();
        if (this$dats == null ? other$dats != null : !this$dats.equals(other$dats)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$shotname = this.getShotname();
        final Object other$shotname = other.getShotname();
        if (this$shotname == null ? other$shotname != null : !this$shotname.equals(other$shotname)) return false;
        final Object this$soogu = this.getSoogu();
        final Object other$soogu = other.getSoogu();
        if (this$soogu == null ? other$soogu != null : !this$soogu.equals(other$soogu)) return false;
        final Object this$soato = this.getSoato();
        final Object other$soato = other.getSoato();
        if (this$soato == null ? other$soato != null : !this$soato.equals(other$soato)) return false;
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
        final Object this$vznos = this.getVznos();
        final Object other$vznos = other.getVznos();
        if (this$vznos == null ? other$vznos != null : !this$vznos.equals(other$vznos)) return false;
        final Object this$sost = this.getSost();
        final Object other$sost = other.getSost();
        if (this$sost == null ? other$sost != null : !this$sost.equals(other$sost)) return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$ls = this.getLs();
        final Object other$ls = other.getLs();
        if (this$ls == null ? other$ls != null : !this$ls.equals(other$ls)) return false;
        final Object this$mfo = this.getMfo();
        final Object other$mfo = other.getMfo();
        if (this$mfo == null ? other$mfo != null : !this$mfo.equals(other$mfo)) return false;
        final Object this$fsobst = this.getFsobst();
        final Object other$fsobst = other.getFsobst();
        if (this$fsobst == null ? other$fsobst != null : !this$fsobst.equals(other$fsobst)) return false;
        final Object this$minVo = this.getMinVo();
        final Object other$minVo = other.getMinVo();
        if (this$minVo == null ? other$minVo != null : !this$minVo.equals(other$minVo)) return false;
        final Object this$adEmail = this.getAdEmail();
        final Object other$adEmail = other.getAdEmail();
        if (this$adEmail == null ? other$adEmail != null : !this$adEmail.equals(other$adEmail)) return false;
        final Object this$fioDirector = this.getFioDirector();
        final Object other$fioDirector = other.getFioDirector();
        if (this$fioDirector == null ? other$fioDirector != null : !this$fioDirector.equals(other$fioDirector))
            return false;
        final Object this$obrazDirector = this.getObrazDirector();
        final Object other$obrazDirector = other.getObrazDirector();
        if (this$obrazDirector == null ? other$obrazDirector != null : !this$obrazDirector.equals(other$obrazDirector))
            return false;
        final Object this$datsRojd = this.getDatsRojd();
        final Object other$datsRojd = other.getDatsRojd();
        if (this$datsRojd == null ? other$datsRojd != null : !this$datsRojd.equals(other$datsRojd)) return false;
        final Object this$svyaz = this.getSvyaz();
        final Object other$svyaz = other.getSvyaz();
        if (this$svyaz == null ? other$svyaz != null : !this$svyaz.equals(other$svyaz)) return false;
        final Object this$datsZakr = this.getDatsZakr();
        final Object other$datsZakr = other.getDatsZakr();
        if (this$datsZakr == null ? other$datsZakr != null : !this$datsZakr.equals(other$datsZakr)) return false;
        final Object this$users = this.getUsers();
        final Object other$users = other.getUsers();
        if (this$users == null ? other$users != null : !this$users.equals(other$users)) return false;
        final Object this$kodOrgan = this.getKodOrgan();
        final Object other$kodOrgan = other.getKodOrgan();
        if (this$kodOrgan == null ? other$kodOrgan != null : !this$kodOrgan.equals(other$kodOrgan)) return false;
        final Object this$numRegistr = this.getNumRegistr();
        final Object other$numRegistr = other.getNumRegistr();
        if (this$numRegistr == null ? other$numRegistr != null : !this$numRegistr.equals(other$numRegistr))
            return false;
        final Object this$datsRegistr = this.getDatsRegistr();
        final Object other$datsRegistr = other.getDatsRegistr();
        if (this$datsRegistr == null ? other$datsRegistr != null : !this$datsRegistr.equals(other$datsRegistr))
            return false;
        final Object this$prim = this.getPrim();
        final Object other$prim = other.getPrim();
        if (this$prim == null ? other$prim != null : !this$prim.equals(other$prim)) return false;
        final Object this$tecVznos = this.getTecVznos();
        final Object other$tecVznos = other.getTecVznos();
        if (this$tecVznos == null ? other$tecVznos != null : !this$tecVznos.equals(other$tecVznos)) return false;
        final Object this$lsvznos = this.getLsvznos();
        final Object other$lsvznos = other.getLsvznos();
        if (this$lsvznos == null ? other$lsvznos != null : !this$lsvznos.equals(other$lsvznos)) return false;
        final Object this$dtime = this.getDtime();
        final Object other$dtime = other.getDtime();
        if (this$dtime == null ? other$dtime != null : !this$dtime.equals(other$dtime)) return false;
        final Object this$indpred = this.getIndpred();
        final Object other$indpred = other.getIndpred();
        if (this$indpred == null ? other$indpred != null : !this$indpred.equals(other$indpred)) return false;
        if (!java.util.Arrays.equals(this.getPhoto(), other.getPhoto())) return false;
        final Object this$kodSubject = this.getKodSubject();
        final Object other$kodSubject = other.getKodSubject();
        if (this$kodSubject == null ? other$kodSubject != null : !this$kodSubject.equals(other$kodSubject))
            return false;
        final Object this$fioBuxg = this.getFioBuxg();
        final Object other$fioBuxg = other.getFioBuxg();
        if (this$fioBuxg == null ? other$fioBuxg != null : !this$fioBuxg.equals(other$fioBuxg)) return false;
        final Object this$tipZayom = this.getTipZayom();
        final Object other$tipZayom = other.getTipZayom();
        if (this$tipZayom == null ? other$tipZayom != null : !this$tipZayom.equals(other$tipZayom)) return false;
        final Object this$sostRegistr = this.getSostRegistr();
        final Object other$sostRegistr = other.getSostRegistr();
        if (this$sostRegistr == null ? other$sostRegistr != null : !this$sostRegistr.equals(other$sostRegistr))
            return false;
        final Object this$sysNumber = this.getSysNumber();
        final Object other$sysNumber = other.getSysNumber();
        if (this$sysNumber == null ? other$sysNumber != null : !this$sysNumber.equals(other$sysNumber)) return false;
        final Object this$datsIzm = this.getDatsIzm();
        final Object other$datsIzm = other.getDatsIzm();
        if (this$datsIzm == null ? other$datsIzm != null : !this$datsIzm.equals(other$datsIzm)) return false;
        final Object this$uchred = this.getUchred();
        final Object other$uchred = other.getUchred();
        if (this$uchred == null ? other$uchred != null : !this$uchred.equals(other$uchred)) return false;
        final Object this$kodObl = this.getKodObl();
        final Object other$kodObl = other.getKodObl();
        if (this$kodObl == null ? other$kodObl != null : !this$kodObl.equals(other$kodObl)) return false;
        final Object this$kodRayon = this.getKodRayon();
        final Object other$kodRayon = other.getKodRayon();
        if (this$kodRayon == null ? other$kodRayon != null : !this$kodRayon.equals(other$kodRayon)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AzolikYur;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $kodchlen = this.getKodchlen();
        result = result * PRIME + ($kodchlen == null ? 43 : $kodchlen.hashCode());
        final Object $dats = this.getDats();
        result = result * PRIME + ($dats == null ? 43 : $dats.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $shotname = this.getShotname();
        result = result * PRIME + ($shotname == null ? 43 : $shotname.hashCode());
        final Object $soogu = this.getSoogu();
        result = result * PRIME + ($soogu == null ? 43 : $soogu.hashCode());
        final Object $soato = this.getSoato();
        result = result * PRIME + ($soato == null ? 43 : $soato.hashCode());
        final Object $inn = this.getInn();
        result = result * PRIME + ($inn == null ? 43 : $inn.hashCode());
        final Object $adres = this.getAdres();
        result = result * PRIME + ($adres == null ? 43 : $adres.hashCode());
        final Object $telmobil = this.getTelmobil();
        result = result * PRIME + ($telmobil == null ? 43 : $telmobil.hashCode());
        final Object $telhome = this.getTelhome();
        result = result * PRIME + ($telhome == null ? 43 : $telhome.hashCode());
        final Object $vznos = this.getVznos();
        result = result * PRIME + ($vznos == null ? 43 : $vznos.hashCode());
        final Object $sost = this.getSost();
        result = result * PRIME + ($sost == null ? 43 : $sost.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $ls = this.getLs();
        result = result * PRIME + ($ls == null ? 43 : $ls.hashCode());
        final Object $mfo = this.getMfo();
        result = result * PRIME + ($mfo == null ? 43 : $mfo.hashCode());
        final Object $fsobst = this.getFsobst();
        result = result * PRIME + ($fsobst == null ? 43 : $fsobst.hashCode());
        final Object $minVo = this.getMinVo();
        result = result * PRIME + ($minVo == null ? 43 : $minVo.hashCode());
        final Object $adEmail = this.getAdEmail();
        result = result * PRIME + ($adEmail == null ? 43 : $adEmail.hashCode());
        final Object $fioDirector = this.getFioDirector();
        result = result * PRIME + ($fioDirector == null ? 43 : $fioDirector.hashCode());
        final Object $obrazDirector = this.getObrazDirector();
        result = result * PRIME + ($obrazDirector == null ? 43 : $obrazDirector.hashCode());
        final Object $datsRojd = this.getDatsRojd();
        result = result * PRIME + ($datsRojd == null ? 43 : $datsRojd.hashCode());
        final Object $svyaz = this.getSvyaz();
        result = result * PRIME + ($svyaz == null ? 43 : $svyaz.hashCode());
        final Object $datsZakr = this.getDatsZakr();
        result = result * PRIME + ($datsZakr == null ? 43 : $datsZakr.hashCode());
        final Object $users = this.getUsers();
        result = result * PRIME + ($users == null ? 43 : $users.hashCode());
        final Object $kodOrgan = this.getKodOrgan();
        result = result * PRIME + ($kodOrgan == null ? 43 : $kodOrgan.hashCode());
        final Object $numRegistr = this.getNumRegistr();
        result = result * PRIME + ($numRegistr == null ? 43 : $numRegistr.hashCode());
        final Object $datsRegistr = this.getDatsRegistr();
        result = result * PRIME + ($datsRegistr == null ? 43 : $datsRegistr.hashCode());
        final Object $prim = this.getPrim();
        result = result * PRIME + ($prim == null ? 43 : $prim.hashCode());
        final Object $tecVznos = this.getTecVznos();
        result = result * PRIME + ($tecVznos == null ? 43 : $tecVznos.hashCode());
        final Object $lsvznos = this.getLsvznos();
        result = result * PRIME + ($lsvznos == null ? 43 : $lsvznos.hashCode());
        final Object $dtime = this.getDtime();
        result = result * PRIME + ($dtime == null ? 43 : $dtime.hashCode());
        final Object $indpred = this.getIndpred();
        result = result * PRIME + ($indpred == null ? 43 : $indpred.hashCode());
        result = result * PRIME + java.util.Arrays.hashCode(this.getPhoto());
        final Object $kodSubject = this.getKodSubject();
        result = result * PRIME + ($kodSubject == null ? 43 : $kodSubject.hashCode());
        final Object $fioBuxg = this.getFioBuxg();
        result = result * PRIME + ($fioBuxg == null ? 43 : $fioBuxg.hashCode());
        final Object $tipZayom = this.getTipZayom();
        result = result * PRIME + ($tipZayom == null ? 43 : $tipZayom.hashCode());
        final Object $sostRegistr = this.getSostRegistr();
        result = result * PRIME + ($sostRegistr == null ? 43 : $sostRegistr.hashCode());
        final Object $sysNumber = this.getSysNumber();
        result = result * PRIME + ($sysNumber == null ? 43 : $sysNumber.hashCode());
        final Object $datsIzm = this.getDatsIzm();
        result = result * PRIME + ($datsIzm == null ? 43 : $datsIzm.hashCode());
        final Object $uchred = this.getUchred();
        result = result * PRIME + ($uchred == null ? 43 : $uchred.hashCode());
        final Object $kodObl = this.getKodObl();
        result = result * PRIME + ($kodObl == null ? 43 : $kodObl.hashCode());
        final Object $kodRayon = this.getKodRayon();
        result = result * PRIME + ($kodRayon == null ? 43 : $kodRayon.hashCode());
        return result;
    }

    public String toString() {
        return "AzolikYur(kodchlen=" + this.getKodchlen() + ", dats=" + this.getDats() + ", name=" + this.getName() + ", shotname=" + this.getShotname() + ", soogu=" + this.getSoogu() + ", soato=" + this.getSoato() + ", inn=" + this.getInn() + ", adres=" + this.getAdres() + ", telmobil=" + this.getTelmobil() + ", telhome=" + this.getTelhome() + ", vznos=" + this.getVznos() + ", sost=" + this.getSost() + ", status=" + this.getStatus() + ", ls=" + this.getLs() + ", mfo=" + this.getMfo() + ", fsobst=" + this.getFsobst() + ", minVo=" + this.getMinVo() + ", adEmail=" + this.getAdEmail() + ", fioDirector=" + this.getFioDirector() + ", obrazDirector=" + this.getObrazDirector() + ", datsRojd=" + this.getDatsRojd() + ", svyaz=" + this.getSvyaz() + ", datsZakr=" + this.getDatsZakr() + ", users=" + this.getUsers() + ", kodOrgan=" + this.getKodOrgan() + ", numRegistr=" + this.getNumRegistr() + ", datsRegistr=" + this.getDatsRegistr() + ", prim=" + this.getPrim() + ", tecVznos=" + this.getTecVznos() + ", lsvznos=" + this.getLsvznos() + ", dtime=" + this.getDtime() + ", indpred=" + this.getIndpred() + ", photo=" + java.util.Arrays.toString(this.getPhoto()) + ", kodSubject=" + this.getKodSubject() + ", fioBuxg=" + this.getFioBuxg() + ", tipZayom=" + this.getTipZayom() + ", sostRegistr=" + this.getSostRegistr() + ", sysNumber=" + this.getSysNumber() + ", datsIzm=" + this.getDatsIzm() + ", uchred=" + this.getUchred() + ", kodObl=" + this.getKodObl() + ", kodRayon=" + this.getKodRayon() + ")";
    }
}

