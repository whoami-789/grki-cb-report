package com.grkicbreport.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "kredit")
@DynamicUpdate
public class Kredit {

    @Id
    @Column(name = "kod", nullable = false, columnDefinition = "nchar(8)")
    private String kod;

    @Column(name = "numdog", nullable = false, columnDefinition = "nchar(12)")
    private String numdog;

    @Column(name = "datadog", nullable = false)
    private LocalDate datadog;

    @Column(name = "dats")
    private LocalDate dats;

    @Column(name = "summa")
    private BigDecimal summa;

    @Column(name = "vidvalut", nullable = false, columnDefinition = "nchar(3)")
    private String vidvalut;

    @Column(name = "vidzalog", nullable = false)
    private Byte vidzalog;

    @Column(name = "vidsrok")
    private Byte vidsrok;

    @Column(name = "prosent", nullable = false)
    private BigDecimal prosent;

    @Column(name = "maqsad")
    private Byte maqsad;

    @Column(name = "sost", nullable = false)
    private Byte sost;

    @Column(name = "status")
    private Byte status;

    @Column(name = "prim")
    private String prim;

    @Transient
    private String yurfiz;

    @Column(name = "tipkred")
    private Short tipkred;

    @Column(name = "srokkred")
    private Byte srokkred;

    @Column(name = "users")
    private Short users;

    @Column(name = "lskred", nullable = false, columnDefinition = "nchar(20)")
    private String lskred;

    @Column(name = "lsproc", nullable = false, columnDefinition = "nchar(20)")
    private String lsproc;

    @Column(name = "lsprosr_kred")
    private String lsprosrKred;

    @Column(name = "ls_spiskred")
    private String ls_spiskred;

    @Column(name = "sms")
    private Byte sms;

    @Column(name = "tel")
    private Byte tel;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kod_dog", updatable = false, nullable = false)
    private Integer kodDog;

    @Column(name = "procpeni")
    private Long procpeni;

    @Transient
    private Boolean indpred;

    @Column(name = "sost_ls")
    private Byte sostLs;

    @Column(name = "dopsogl")
    private String dopsogl;

    @Column(name = "lssud_kred")
    private String lssudKred;

    @Column(name = "nalbeznal")
    private Byte nalbeznal;

    @Column(name = "vazvnalbeznal")
    private Byte vazvnalbeznal;

    @Column(name = "sost_proc")
    private Byte sostProc;

    @Column(name = "rejnach")
    private Byte rejnach;

    @Column(name = "autoe")
    private Byte autoe;

    @Column(name = "autos")
    private Byte autos;

    @Column(name = "autokred")
    private Byte autokred;

    @Column(name = "autoproc")
    private Byte autoproc;

    @Column(name = "dney")
    private Byte dney;

    @Column(name = "chas")
    private Byte chas;

    @Transient
    private String lsDox;

    @Column(name = "lsprocvne")
    private String lsprocvne;

    @Column(name = "vidkred")
    private Integer vidkred;

    @Column(name = "lsrezerv")
    private String lsrezerv;

    @Transient
    private Integer tip;

    @Column(name = "minvznos")
    private BigDecimal minvznos;

    @Column(name = "komissy")
    private BigDecimal komissy;

    @Column(name = "lgot")
    private Byte lgot;

    @Column(name = "progress")
    private Byte progress;

    @Column(name = "lizpredmet", columnDefinition = "TEXT")
    private String lizpredmet;

    @Column(name = "lizprodovec", columnDefinition = "TEXT")
    private String lizprodovec;

    @Column(name = "dats_zakr")
    private LocalDate datsZakr;

    @Column(name = "dats_prosr")
    private Date datsProsr;

    @Column(name = "dopsogl_dats")
    private Date dopsoglDats;

    @Column(name = "spec")
    private Byte spec;

    @Column(name = "tipliz")
    private Byte tipliz;

    @Column(name = "ls_peres")
    private String lsPeres;

    @Column(name = "graf")
    private Byte graf;

    @Column(name = "autop")
    private Byte autop;

    @Transient
    private String lsKontrvne;

    @Transient
    private String lsSpiskred;

    @Column(name = "dats_izm")
    private LocalDate datsIzm;

    @Column(name = "dats_izm_grafik")
    private Date datsIzmGrafik;

    @Column(name = "dats_izm_zalog")
    private Date datsIzmZalog;

    @Column(name = "objekt")
    private String objekt;

    @Column(name = "valut")
    private Byte valut;

    @Column(name = "klass")
    private Byte klass;

    @Column(name = "lsprosr_proc")
    private String lsprosrProc;

    @Column(name = "ls22812")
    private String ls22812;

    @Column(name = "dats_izm_asoki")
    private Date datsIzmAsoki;

    @Column(name = "xatar")
    private Byte xatar;

    @Column(name = "lspeni")
    private String lspeni;

    @Column(name = "grki-claim-id")
    private String grkiClaimId;

    @Column(name = "grki-agreement-id")
    private String grkiAgreementId;

    @Column(name = "grki-contract-id")
    private String grkiContractId;

    @OneToMany(mappedBy = "kredit")
    private List<Grafik> grafiks;

    @OneToMany(mappedBy = "kredit")
    private List<Zalog> zalogs;

    @OneToMany(mappedBy = "kredit")
    private List<ZalogXranenie> zalogXranenieList;

    @ManyToOne
    @JoinColumn(name = "kod", referencedColumnName = "kodchlen", insertable = false, updatable = false)
    private AzolikFiz azolikFiz;

    public Kredit() {
    }


    protected boolean canEqual(final Object other) {
        return other instanceof Kredit;
    }

    public String getKod() {
        return this.kod;
    }

    public String getNumdog() {
        return this.numdog;
    }

    public LocalDate getDatadog() {
        return this.datadog;
    }

    public LocalDate getDats() {
        return this.dats;
    }

    public BigDecimal getSumma() {
        return this.summa;
    }

    public String getVidvalut() {
        return this.vidvalut;
    }

    public Byte getVidzalog() {
        return this.vidzalog;
    }

    public Byte getVidsrok() {
        return this.vidsrok;
    }

    public BigDecimal getProsent() {
        return this.prosent;
    }

    public Byte getMaqsad() {
        return this.maqsad;
    }

    public Byte getSost() {
        return this.sost;
    }

    public Byte getStatus() {
        return this.status;
    }

    public String getPrim() {
        return this.prim;
    }

    public String getYurfiz() {
        return this.yurfiz;
    }

    public Short getTipkred() {
        return this.tipkred;
    }

    public Byte getSrokkred() {
        return this.srokkred;
    }

    public Short getUsers() {
        return this.users;
    }

    public String getLskred() {
        return this.lskred;
    }

    public String getLsproc() {
        return this.lsproc;
    }

    public String getLsprosrKred() {
        return this.lsprosrKred;
    }

    public String getLs_spiskred() {
        return this.ls_spiskred;
    }

    public Byte getSms() {
        return this.sms;
    }

    public Byte getTel() {
        return this.tel;
    }

    public Integer getKodDog() {
        return this.kodDog;
    }

    public Long getProcpeni() {
        return this.procpeni;
    }

    public Boolean getIndpred() {
        return this.indpred;
    }

    public Byte getSostLs() {
        return this.sostLs;
    }

    public String getDopsogl() {
        return this.dopsogl;
    }

    public String getLssudKred() {
        return this.lssudKred;
    }

    public Byte getNalbeznal() {
        return this.nalbeznal;
    }

    public Byte getVazvnalbeznal() {
        return this.vazvnalbeznal;
    }

    public Byte getSostProc() {
        return this.sostProc;
    }

    public Byte getRejnach() {
        return this.rejnach;
    }

    public Byte getAutoe() {
        return this.autoe;
    }

    public Byte getAutos() {
        return this.autos;
    }

    public Byte getAutokred() {
        return this.autokred;
    }

    public Byte getAutoproc() {
        return this.autoproc;
    }

    public Byte getDney() {
        return this.dney;
    }

    public Byte getChas() {
        return this.chas;
    }

    public String getLsDox() {
        return this.lsDox;
    }

    public String getLsprocvne() {
        return this.lsprocvne;
    }

    public Integer getVidkred() {
        return this.vidkred;
    }

    public String getLsrezerv() {
        return this.lsrezerv;
    }

    public Integer getTip() {
        return this.tip;
    }

    public BigDecimal getMinvznos() {
        return this.minvznos;
    }

    public BigDecimal getKomissy() {
        return this.komissy;
    }

    public Byte getLgot() {
        return this.lgot;
    }

    public Byte getProgress() {
        return this.progress;
    }

    public String getLizpredmet() {
        return this.lizpredmet;
    }

    public String getLizprodovec() {
        return this.lizprodovec;
    }

    public LocalDate getDatsZakr() {
        return this.datsZakr;
    }

    public Date getDatsProsr() {
        return this.datsProsr;
    }

    public Date getDopsoglDats() {
        return this.dopsoglDats;
    }

    public Byte getSpec() {
        return this.spec;
    }

    public Byte getTipliz() {
        return this.tipliz;
    }

    public String getLsPeres() {
        return this.lsPeres;
    }

    public Byte getGraf() {
        return this.graf;
    }

    public Byte getAutop() {
        return this.autop;
    }

    public String getLsKontrvne() {
        return this.lsKontrvne;
    }

    public String getLsSpiskred() {
        return this.lsSpiskred;
    }

    public LocalDate getDatsIzm() {
        return this.datsIzm;
    }

    public Date getDatsIzmGrafik() {
        return this.datsIzmGrafik;
    }

    public Date getDatsIzmZalog() {
        return this.datsIzmZalog;
    }

    public String getObjekt() {
        return this.objekt;
    }

    public Byte getValut() {
        return this.valut;
    }

    public Byte getKlass() {
        return this.klass;
    }

    public String getLsprosrProc() {
        return this.lsprosrProc;
    }

    public String getLs22812() {
        return this.ls22812;
    }

    public Date getDatsIzmAsoki() {
        return this.datsIzmAsoki;
    }

    public Byte getXatar() {
        return this.xatar;
    }

    public String getLspeni() {
        return this.lspeni;
    }

    public String getGrkiClaimId() {
        return this.grkiClaimId;
    }

    public String getGrkiAgreementId() {
        return this.grkiAgreementId;
    }

    public String getGrkiContractId() {
        return this.grkiContractId;
    }

    public List<Grafik> getGrafiks() {
        return this.grafiks;
    }

    public List<Zalog> getZalogs() {
        return this.zalogs;
    }

    public List<ZalogXranenie> getZalogXranenieList() {
        return this.zalogXranenieList;
    }

    public AzolikFiz getAzolikFiz() {
        return this.azolikFiz;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public void setNumdog(String numdog) {
        this.numdog = numdog;
    }

    public void setDatadog(LocalDate datadog) {
        this.datadog = datadog;
    }

    public void setDats(LocalDate dats) {
        this.dats = dats;
    }

    public void setSumma(BigDecimal summa) {
        this.summa = summa;
    }

    public void setVidvalut(String vidvalut) {
        this.vidvalut = vidvalut;
    }

    public void setVidzalog(Byte vidzalog) {
        this.vidzalog = vidzalog;
    }

    public void setVidsrok(Byte vidsrok) {
        this.vidsrok = vidsrok;
    }

    public void setProsent(BigDecimal prosent) {
        this.prosent = prosent;
    }

    public void setMaqsad(Byte maqsad) {
        this.maqsad = maqsad;
    }

    public void setSost(Byte sost) {
        this.sost = sost;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

    public void setYurfiz(String yurfiz) {
        this.yurfiz = yurfiz;
    }

    public void setTipkred(Short tipkred) {
        this.tipkred = tipkred;
    }

    public void setSrokkred(Byte srokkred) {
        this.srokkred = srokkred;
    }

    public void setUsers(Short users) {
        this.users = users;
    }

    public void setLskred(String lskred) {
        this.lskred = lskred;
    }

    public void setLsproc(String lsproc) {
        this.lsproc = lsproc;
    }

    public void setLsprosrKred(String lsprosrKred) {
        this.lsprosrKred = lsprosrKred;
    }

    public void setLs_spiskred(String ls_spiskred) {
        this.ls_spiskred = ls_spiskred;
    }

    public void setSms(Byte sms) {
        this.sms = sms;
    }

    public void setTel(Byte tel) {
        this.tel = tel;
    }

    public void setKodDog(Integer kodDog) {
        this.kodDog = kodDog;
    }

    public void setProcpeni(Long procpeni) {
        this.procpeni = procpeni;
    }

    public void setIndpred(Boolean indpred) {
        this.indpred = indpred;
    }

    public void setSostLs(Byte sostLs) {
        this.sostLs = sostLs;
    }

    public void setDopsogl(String dopsogl) {
        this.dopsogl = dopsogl;
    }

    public void setLssudKred(String lssudKred) {
        this.lssudKred = lssudKred;
    }

    public void setNalbeznal(Byte nalbeznal) {
        this.nalbeznal = nalbeznal;
    }

    public void setVazvnalbeznal(Byte vazvnalbeznal) {
        this.vazvnalbeznal = vazvnalbeznal;
    }

    public void setSostProc(Byte sostProc) {
        this.sostProc = sostProc;
    }

    public void setRejnach(Byte rejnach) {
        this.rejnach = rejnach;
    }

    public void setAutoe(Byte autoe) {
        this.autoe = autoe;
    }

    public void setAutos(Byte autos) {
        this.autos = autos;
    }

    public void setAutokred(Byte autokred) {
        this.autokred = autokred;
    }

    public void setAutoproc(Byte autoproc) {
        this.autoproc = autoproc;
    }

    public void setDney(Byte dney) {
        this.dney = dney;
    }

    public void setChas(Byte chas) {
        this.chas = chas;
    }

    public void setLsDox(String lsDox) {
        this.lsDox = lsDox;
    }

    public void setLsprocvne(String lsprocvne) {
        this.lsprocvne = lsprocvne;
    }

    public void setVidkred(Integer vidkred) {
        this.vidkred = vidkred;
    }

    public void setLsrezerv(String lsrezerv) {
        this.lsrezerv = lsrezerv;
    }

    public void setTip(Integer tip) {
        this.tip = tip;
    }

    public void setMinvznos(BigDecimal minvznos) {
        this.minvznos = minvznos;
    }

    public void setKomissy(BigDecimal komissy) {
        this.komissy = komissy;
    }

    public void setLgot(Byte lgot) {
        this.lgot = lgot;
    }

    public void setProgress(Byte progress) {
        this.progress = progress;
    }

    public void setLizpredmet(String lizpredmet) {
        this.lizpredmet = lizpredmet;
    }

    public void setLizprodovec(String lizprodovec) {
        this.lizprodovec = lizprodovec;
    }

    public void setDatsZakr(LocalDate datsZakr) {
        this.datsZakr = datsZakr;
    }

    public void setDatsProsr(Date datsProsr) {
        this.datsProsr = datsProsr;
    }

    public void setDopsoglDats(Date dopsoglDats) {
        this.dopsoglDats = dopsoglDats;
    }

    public void setSpec(Byte spec) {
        this.spec = spec;
    }

    public void setTipliz(Byte tipliz) {
        this.tipliz = tipliz;
    }

    public void setLsPeres(String lsPeres) {
        this.lsPeres = lsPeres;
    }

    public void setGraf(Byte graf) {
        this.graf = graf;
    }

    public void setAutop(Byte autop) {
        this.autop = autop;
    }

    public void setLsKontrvne(String lsKontrvne) {
        this.lsKontrvne = lsKontrvne;
    }

    public void setLsSpiskred(String lsSpiskred) {
        this.lsSpiskred = lsSpiskred;
    }

    public void setDatsIzm(LocalDate datsIzm) {
        this.datsIzm = datsIzm;
    }

    public void setDatsIzmGrafik(Date datsIzmGrafik) {
        this.datsIzmGrafik = datsIzmGrafik;
    }

    public void setDatsIzmZalog(Date datsIzmZalog) {
        this.datsIzmZalog = datsIzmZalog;
    }

    public void setObjekt(String objekt) {
        this.objekt = objekt;
    }

    public void setValut(Byte valut) {
        this.valut = valut;
    }

    public void setKlass(Byte klass) {
        this.klass = klass;
    }

    public void setLsprosrProc(String lsprosrProc) {
        this.lsprosrProc = lsprosrProc;
    }

    public void setLs22812(String ls22812) {
        this.ls22812 = ls22812;
    }

    public void setDatsIzmAsoki(Date datsIzmAsoki) {
        this.datsIzmAsoki = datsIzmAsoki;
    }

    public void setXatar(Byte xatar) {
        this.xatar = xatar;
    }

    public void setLspeni(String lspeni) {
        this.lspeni = lspeni;
    }

    public void setGrkiClaimId(String grkiClaimId) {
        this.grkiClaimId = grkiClaimId;
    }

    public void setGrkiAgreementId(String grkiAgreementId) {
        this.grkiAgreementId = grkiAgreementId;
    }

    public void setGrkiContractId(String grkiContractId) {
        this.grkiContractId = grkiContractId;
    }

    public void setGrafiks(List<Grafik> grafiks) {
        this.grafiks = grafiks;
    }

    public void setZalogs(List<Zalog> zalogs) {
        this.zalogs = zalogs;
    }

    public void setZalogXranenieList(List<ZalogXranenie> zalogXranenieList) {
        this.zalogXranenieList = zalogXranenieList;
    }

    public void setAzolikFiz(AzolikFiz azolikFiz) {
        this.azolikFiz = azolikFiz;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Kredit)) return false;
        final Kredit other = (Kredit) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$kod = this.getKod();
        final Object other$kod = other.getKod();
        if (this$kod == null ? other$kod != null : !this$kod.equals(other$kod)) return false;
        final Object this$numdog = this.getNumdog();
        final Object other$numdog = other.getNumdog();
        if (this$numdog == null ? other$numdog != null : !this$numdog.equals(other$numdog)) return false;
        final Object this$datadog = this.getDatadog();
        final Object other$datadog = other.getDatadog();
        if (this$datadog == null ? other$datadog != null : !this$datadog.equals(other$datadog)) return false;
        final Object this$dats = this.getDats();
        final Object other$dats = other.getDats();
        if (this$dats == null ? other$dats != null : !this$dats.equals(other$dats)) return false;
        final Object this$summa = this.getSumma();
        final Object other$summa = other.getSumma();
        if (this$summa == null ? other$summa != null : !this$summa.equals(other$summa)) return false;
        final Object this$vidvalut = this.getVidvalut();
        final Object other$vidvalut = other.getVidvalut();
        if (this$vidvalut == null ? other$vidvalut != null : !this$vidvalut.equals(other$vidvalut)) return false;
        final Object this$vidzalog = this.getVidzalog();
        final Object other$vidzalog = other.getVidzalog();
        if (this$vidzalog == null ? other$vidzalog != null : !this$vidzalog.equals(other$vidzalog)) return false;
        final Object this$vidsrok = this.getVidsrok();
        final Object other$vidsrok = other.getVidsrok();
        if (this$vidsrok == null ? other$vidsrok != null : !this$vidsrok.equals(other$vidsrok)) return false;
        final Object this$prosent = this.getProsent();
        final Object other$prosent = other.getProsent();
        if (this$prosent == null ? other$prosent != null : !this$prosent.equals(other$prosent)) return false;
        final Object this$maqsad = this.getMaqsad();
        final Object other$maqsad = other.getMaqsad();
        if (this$maqsad == null ? other$maqsad != null : !this$maqsad.equals(other$maqsad)) return false;
        final Object this$sost = this.getSost();
        final Object other$sost = other.getSost();
        if (this$sost == null ? other$sost != null : !this$sost.equals(other$sost)) return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$prim = this.getPrim();
        final Object other$prim = other.getPrim();
        if (this$prim == null ? other$prim != null : !this$prim.equals(other$prim)) return false;
        final Object this$yurfiz = this.getYurfiz();
        final Object other$yurfiz = other.getYurfiz();
        if (this$yurfiz == null ? other$yurfiz != null : !this$yurfiz.equals(other$yurfiz)) return false;
        final Object this$tipkred = this.getTipkred();
        final Object other$tipkred = other.getTipkred();
        if (this$tipkred == null ? other$tipkred != null : !this$tipkred.equals(other$tipkred)) return false;
        final Object this$srokkred = this.getSrokkred();
        final Object other$srokkred = other.getSrokkred();
        if (this$srokkred == null ? other$srokkred != null : !this$srokkred.equals(other$srokkred)) return false;
        final Object this$users = this.getUsers();
        final Object other$users = other.getUsers();
        if (this$users == null ? other$users != null : !this$users.equals(other$users)) return false;
        final Object this$lskred = this.getLskred();
        final Object other$lskred = other.getLskred();
        if (this$lskred == null ? other$lskred != null : !this$lskred.equals(other$lskred)) return false;
        final Object this$lsproc = this.getLsproc();
        final Object other$lsproc = other.getLsproc();
        if (this$lsproc == null ? other$lsproc != null : !this$lsproc.equals(other$lsproc)) return false;
        final Object this$lsprosrKred = this.getLsprosrKred();
        final Object other$lsprosrKred = other.getLsprosrKred();
        if (this$lsprosrKred == null ? other$lsprosrKred != null : !this$lsprosrKred.equals(other$lsprosrKred))
            return false;
        final Object this$ls_spiskred = this.getLs_spiskred();
        final Object other$ls_spiskred = other.getLs_spiskred();
        if (this$ls_spiskred == null ? other$ls_spiskred != null : !this$ls_spiskred.equals(other$ls_spiskred))
            return false;
        final Object this$sms = this.getSms();
        final Object other$sms = other.getSms();
        if (this$sms == null ? other$sms != null : !this$sms.equals(other$sms)) return false;
        final Object this$tel = this.getTel();
        final Object other$tel = other.getTel();
        if (this$tel == null ? other$tel != null : !this$tel.equals(other$tel)) return false;
        final Object this$kodDog = this.getKodDog();
        final Object other$kodDog = other.getKodDog();
        if (this$kodDog == null ? other$kodDog != null : !this$kodDog.equals(other$kodDog)) return false;
        final Object this$procpeni = this.getProcpeni();
        final Object other$procpeni = other.getProcpeni();
        if (this$procpeni == null ? other$procpeni != null : !this$procpeni.equals(other$procpeni)) return false;
        final Object this$indpred = this.getIndpred();
        final Object other$indpred = other.getIndpred();
        if (this$indpred == null ? other$indpred != null : !this$indpred.equals(other$indpred)) return false;
        final Object this$sostLs = this.getSostLs();
        final Object other$sostLs = other.getSostLs();
        if (this$sostLs == null ? other$sostLs != null : !this$sostLs.equals(other$sostLs)) return false;
        final Object this$dopsogl = this.getDopsogl();
        final Object other$dopsogl = other.getDopsogl();
        if (this$dopsogl == null ? other$dopsogl != null : !this$dopsogl.equals(other$dopsogl)) return false;
        final Object this$lssudKred = this.getLssudKred();
        final Object other$lssudKred = other.getLssudKred();
        if (this$lssudKred == null ? other$lssudKred != null : !this$lssudKred.equals(other$lssudKred)) return false;
        final Object this$nalbeznal = this.getNalbeznal();
        final Object other$nalbeznal = other.getNalbeznal();
        if (this$nalbeznal == null ? other$nalbeznal != null : !this$nalbeznal.equals(other$nalbeznal)) return false;
        final Object this$vazvnalbeznal = this.getVazvnalbeznal();
        final Object other$vazvnalbeznal = other.getVazvnalbeznal();
        if (this$vazvnalbeznal == null ? other$vazvnalbeznal != null : !this$vazvnalbeznal.equals(other$vazvnalbeznal))
            return false;
        final Object this$sostProc = this.getSostProc();
        final Object other$sostProc = other.getSostProc();
        if (this$sostProc == null ? other$sostProc != null : !this$sostProc.equals(other$sostProc)) return false;
        final Object this$rejnach = this.getRejnach();
        final Object other$rejnach = other.getRejnach();
        if (this$rejnach == null ? other$rejnach != null : !this$rejnach.equals(other$rejnach)) return false;
        final Object this$autoe = this.getAutoe();
        final Object other$autoe = other.getAutoe();
        if (this$autoe == null ? other$autoe != null : !this$autoe.equals(other$autoe)) return false;
        final Object this$autos = this.getAutos();
        final Object other$autos = other.getAutos();
        if (this$autos == null ? other$autos != null : !this$autos.equals(other$autos)) return false;
        final Object this$autokred = this.getAutokred();
        final Object other$autokred = other.getAutokred();
        if (this$autokred == null ? other$autokred != null : !this$autokred.equals(other$autokred)) return false;
        final Object this$autoproc = this.getAutoproc();
        final Object other$autoproc = other.getAutoproc();
        if (this$autoproc == null ? other$autoproc != null : !this$autoproc.equals(other$autoproc)) return false;
        final Object this$dney = this.getDney();
        final Object other$dney = other.getDney();
        if (this$dney == null ? other$dney != null : !this$dney.equals(other$dney)) return false;
        final Object this$chas = this.getChas();
        final Object other$chas = other.getChas();
        if (this$chas == null ? other$chas != null : !this$chas.equals(other$chas)) return false;
        final Object this$lsDox = this.getLsDox();
        final Object other$lsDox = other.getLsDox();
        if (this$lsDox == null ? other$lsDox != null : !this$lsDox.equals(other$lsDox)) return false;
        final Object this$lsprocvne = this.getLsprocvne();
        final Object other$lsprocvne = other.getLsprocvne();
        if (this$lsprocvne == null ? other$lsprocvne != null : !this$lsprocvne.equals(other$lsprocvne)) return false;
        final Object this$vidkred = this.getVidkred();
        final Object other$vidkred = other.getVidkred();
        if (this$vidkred == null ? other$vidkred != null : !this$vidkred.equals(other$vidkred)) return false;
        final Object this$lsrezerv = this.getLsrezerv();
        final Object other$lsrezerv = other.getLsrezerv();
        if (this$lsrezerv == null ? other$lsrezerv != null : !this$lsrezerv.equals(other$lsrezerv)) return false;
        final Object this$tip = this.getTip();
        final Object other$tip = other.getTip();
        if (this$tip == null ? other$tip != null : !this$tip.equals(other$tip)) return false;
        final Object this$minvznos = this.getMinvznos();
        final Object other$minvznos = other.getMinvznos();
        if (this$minvznos == null ? other$minvznos != null : !this$minvznos.equals(other$minvznos)) return false;
        final Object this$komissy = this.getKomissy();
        final Object other$komissy = other.getKomissy();
        if (this$komissy == null ? other$komissy != null : !this$komissy.equals(other$komissy)) return false;
        final Object this$lgot = this.getLgot();
        final Object other$lgot = other.getLgot();
        if (this$lgot == null ? other$lgot != null : !this$lgot.equals(other$lgot)) return false;
        final Object this$progress = this.getProgress();
        final Object other$progress = other.getProgress();
        if (this$progress == null ? other$progress != null : !this$progress.equals(other$progress)) return false;
        final Object this$lizpredmet = this.getLizpredmet();
        final Object other$lizpredmet = other.getLizpredmet();
        if (this$lizpredmet == null ? other$lizpredmet != null : !this$lizpredmet.equals(other$lizpredmet))
            return false;
        final Object this$lizprodovec = this.getLizprodovec();
        final Object other$lizprodovec = other.getLizprodovec();
        if (this$lizprodovec == null ? other$lizprodovec != null : !this$lizprodovec.equals(other$lizprodovec))
            return false;
        final Object this$datsZakr = this.getDatsZakr();
        final Object other$datsZakr = other.getDatsZakr();
        if (this$datsZakr == null ? other$datsZakr != null : !this$datsZakr.equals(other$datsZakr)) return false;
        final Object this$datsProsr = this.getDatsProsr();
        final Object other$datsProsr = other.getDatsProsr();
        if (this$datsProsr == null ? other$datsProsr != null : !this$datsProsr.equals(other$datsProsr)) return false;
        final Object this$dopsoglDats = this.getDopsoglDats();
        final Object other$dopsoglDats = other.getDopsoglDats();
        if (this$dopsoglDats == null ? other$dopsoglDats != null : !this$dopsoglDats.equals(other$dopsoglDats))
            return false;
        final Object this$spec = this.getSpec();
        final Object other$spec = other.getSpec();
        if (this$spec == null ? other$spec != null : !this$spec.equals(other$spec)) return false;
        final Object this$tipliz = this.getTipliz();
        final Object other$tipliz = other.getTipliz();
        if (this$tipliz == null ? other$tipliz != null : !this$tipliz.equals(other$tipliz)) return false;
        final Object this$lsPeres = this.getLsPeres();
        final Object other$lsPeres = other.getLsPeres();
        if (this$lsPeres == null ? other$lsPeres != null : !this$lsPeres.equals(other$lsPeres)) return false;
        final Object this$graf = this.getGraf();
        final Object other$graf = other.getGraf();
        if (this$graf == null ? other$graf != null : !this$graf.equals(other$graf)) return false;
        final Object this$autop = this.getAutop();
        final Object other$autop = other.getAutop();
        if (this$autop == null ? other$autop != null : !this$autop.equals(other$autop)) return false;
        final Object this$lsKontrvne = this.getLsKontrvne();
        final Object other$lsKontrvne = other.getLsKontrvne();
        if (this$lsKontrvne == null ? other$lsKontrvne != null : !this$lsKontrvne.equals(other$lsKontrvne))
            return false;
        final Object this$lsSpiskred = this.getLsSpiskred();
        final Object other$lsSpiskred = other.getLsSpiskred();
        if (this$lsSpiskred == null ? other$lsSpiskred != null : !this$lsSpiskred.equals(other$lsSpiskred))
            return false;
        final Object this$datsIzm = this.getDatsIzm();
        final Object other$datsIzm = other.getDatsIzm();
        if (this$datsIzm == null ? other$datsIzm != null : !this$datsIzm.equals(other$datsIzm)) return false;
        final Object this$datsIzmGrafik = this.getDatsIzmGrafik();
        final Object other$datsIzmGrafik = other.getDatsIzmGrafik();
        if (this$datsIzmGrafik == null ? other$datsIzmGrafik != null : !this$datsIzmGrafik.equals(other$datsIzmGrafik))
            return false;
        final Object this$datsIzmZalog = this.getDatsIzmZalog();
        final Object other$datsIzmZalog = other.getDatsIzmZalog();
        if (this$datsIzmZalog == null ? other$datsIzmZalog != null : !this$datsIzmZalog.equals(other$datsIzmZalog))
            return false;
        final Object this$objekt = this.getObjekt();
        final Object other$objekt = other.getObjekt();
        if (this$objekt == null ? other$objekt != null : !this$objekt.equals(other$objekt)) return false;
        final Object this$valut = this.getValut();
        final Object other$valut = other.getValut();
        if (this$valut == null ? other$valut != null : !this$valut.equals(other$valut)) return false;
        final Object this$klass = this.getKlass();
        final Object other$klass = other.getKlass();
        if (this$klass == null ? other$klass != null : !this$klass.equals(other$klass)) return false;
        final Object this$lsprosrProc = this.getLsprosrProc();
        final Object other$lsprosrProc = other.getLsprosrProc();
        if (this$lsprosrProc == null ? other$lsprosrProc != null : !this$lsprosrProc.equals(other$lsprosrProc))
            return false;
        final Object this$ls22812 = this.getLs22812();
        final Object other$ls22812 = other.getLs22812();
        if (this$ls22812 == null ? other$ls22812 != null : !this$ls22812.equals(other$ls22812)) return false;
        final Object this$datsIzmAsoki = this.getDatsIzmAsoki();
        final Object other$datsIzmAsoki = other.getDatsIzmAsoki();
        if (this$datsIzmAsoki == null ? other$datsIzmAsoki != null : !this$datsIzmAsoki.equals(other$datsIzmAsoki))
            return false;
        final Object this$xatar = this.getXatar();
        final Object other$xatar = other.getXatar();
        if (this$xatar == null ? other$xatar != null : !this$xatar.equals(other$xatar)) return false;
        final Object this$lspeni = this.getLspeni();
        final Object other$lspeni = other.getLspeni();
        if (this$lspeni == null ? other$lspeni != null : !this$lspeni.equals(other$lspeni)) return false;
        final Object this$grkiClaimId = this.getGrkiClaimId();
        final Object other$grkiClaimId = other.getGrkiClaimId();
        if (this$grkiClaimId == null ? other$grkiClaimId != null : !this$grkiClaimId.equals(other$grkiClaimId))
            return false;
        final Object this$grkiAgreementId = this.getGrkiAgreementId();
        final Object other$grkiAgreementId = other.getGrkiAgreementId();
        if (this$grkiAgreementId == null ? other$grkiAgreementId != null : !this$grkiAgreementId.equals(other$grkiAgreementId))
            return false;
        final Object this$grkiContractId = this.getGrkiContractId();
        final Object other$grkiContractId = other.getGrkiContractId();
        if (this$grkiContractId == null ? other$grkiContractId != null : !this$grkiContractId.equals(other$grkiContractId))
            return false;
        final Object this$grafiks = this.getGrafiks();
        final Object other$grafiks = other.getGrafiks();
        if (this$grafiks == null ? other$grafiks != null : !this$grafiks.equals(other$grafiks)) return false;
        final Object this$zalogs = this.getZalogs();
        final Object other$zalogs = other.getZalogs();
        if (this$zalogs == null ? other$zalogs != null : !this$zalogs.equals(other$zalogs)) return false;
        final Object this$zalogXranenieList = this.getZalogXranenieList();
        final Object other$zalogXranenieList = other.getZalogXranenieList();
        if (this$zalogXranenieList == null ? other$zalogXranenieList != null : !this$zalogXranenieList.equals(other$zalogXranenieList))
            return false;
        final Object this$azolikFiz = this.getAzolikFiz();
        final Object other$azolikFiz = other.getAzolikFiz();
        if (this$azolikFiz == null ? other$azolikFiz != null : !this$azolikFiz.equals(other$azolikFiz)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $kod = this.getKod();
        result = result * PRIME + ($kod == null ? 43 : $kod.hashCode());
        final Object $numdog = this.getNumdog();
        result = result * PRIME + ($numdog == null ? 43 : $numdog.hashCode());
        final Object $datadog = this.getDatadog();
        result = result * PRIME + ($datadog == null ? 43 : $datadog.hashCode());
        final Object $dats = this.getDats();
        result = result * PRIME + ($dats == null ? 43 : $dats.hashCode());
        final Object $summa = this.getSumma();
        result = result * PRIME + ($summa == null ? 43 : $summa.hashCode());
        final Object $vidvalut = this.getVidvalut();
        result = result * PRIME + ($vidvalut == null ? 43 : $vidvalut.hashCode());
        final Object $vidzalog = this.getVidzalog();
        result = result * PRIME + ($vidzalog == null ? 43 : $vidzalog.hashCode());
        final Object $vidsrok = this.getVidsrok();
        result = result * PRIME + ($vidsrok == null ? 43 : $vidsrok.hashCode());
        final Object $prosent = this.getProsent();
        result = result * PRIME + ($prosent == null ? 43 : $prosent.hashCode());
        final Object $maqsad = this.getMaqsad();
        result = result * PRIME + ($maqsad == null ? 43 : $maqsad.hashCode());
        final Object $sost = this.getSost();
        result = result * PRIME + ($sost == null ? 43 : $sost.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $prim = this.getPrim();
        result = result * PRIME + ($prim == null ? 43 : $prim.hashCode());
        final Object $yurfiz = this.getYurfiz();
        result = result * PRIME + ($yurfiz == null ? 43 : $yurfiz.hashCode());
        final Object $tipkred = this.getTipkred();
        result = result * PRIME + ($tipkred == null ? 43 : $tipkred.hashCode());
        final Object $srokkred = this.getSrokkred();
        result = result * PRIME + ($srokkred == null ? 43 : $srokkred.hashCode());
        final Object $users = this.getUsers();
        result = result * PRIME + ($users == null ? 43 : $users.hashCode());
        final Object $lskred = this.getLskred();
        result = result * PRIME + ($lskred == null ? 43 : $lskred.hashCode());
        final Object $lsproc = this.getLsproc();
        result = result * PRIME + ($lsproc == null ? 43 : $lsproc.hashCode());
        final Object $lsprosrKred = this.getLsprosrKred();
        result = result * PRIME + ($lsprosrKred == null ? 43 : $lsprosrKred.hashCode());
        final Object $ls_spiskred = this.getLs_spiskred();
        result = result * PRIME + ($ls_spiskred == null ? 43 : $ls_spiskred.hashCode());
        final Object $sms = this.getSms();
        result = result * PRIME + ($sms == null ? 43 : $sms.hashCode());
        final Object $tel = this.getTel();
        result = result * PRIME + ($tel == null ? 43 : $tel.hashCode());
        final Object $kodDog = this.getKodDog();
        result = result * PRIME + ($kodDog == null ? 43 : $kodDog.hashCode());
        final Object $procpeni = this.getProcpeni();
        result = result * PRIME + ($procpeni == null ? 43 : $procpeni.hashCode());
        final Object $indpred = this.getIndpred();
        result = result * PRIME + ($indpred == null ? 43 : $indpred.hashCode());
        final Object $sostLs = this.getSostLs();
        result = result * PRIME + ($sostLs == null ? 43 : $sostLs.hashCode());
        final Object $dopsogl = this.getDopsogl();
        result = result * PRIME + ($dopsogl == null ? 43 : $dopsogl.hashCode());
        final Object $lssudKred = this.getLssudKred();
        result = result * PRIME + ($lssudKred == null ? 43 : $lssudKred.hashCode());
        final Object $nalbeznal = this.getNalbeznal();
        result = result * PRIME + ($nalbeznal == null ? 43 : $nalbeznal.hashCode());
        final Object $vazvnalbeznal = this.getVazvnalbeznal();
        result = result * PRIME + ($vazvnalbeznal == null ? 43 : $vazvnalbeznal.hashCode());
        final Object $sostProc = this.getSostProc();
        result = result * PRIME + ($sostProc == null ? 43 : $sostProc.hashCode());
        final Object $rejnach = this.getRejnach();
        result = result * PRIME + ($rejnach == null ? 43 : $rejnach.hashCode());
        final Object $autoe = this.getAutoe();
        result = result * PRIME + ($autoe == null ? 43 : $autoe.hashCode());
        final Object $autos = this.getAutos();
        result = result * PRIME + ($autos == null ? 43 : $autos.hashCode());
        final Object $autokred = this.getAutokred();
        result = result * PRIME + ($autokred == null ? 43 : $autokred.hashCode());
        final Object $autoproc = this.getAutoproc();
        result = result * PRIME + ($autoproc == null ? 43 : $autoproc.hashCode());
        final Object $dney = this.getDney();
        result = result * PRIME + ($dney == null ? 43 : $dney.hashCode());
        final Object $chas = this.getChas();
        result = result * PRIME + ($chas == null ? 43 : $chas.hashCode());
        final Object $lsDox = this.getLsDox();
        result = result * PRIME + ($lsDox == null ? 43 : $lsDox.hashCode());
        final Object $lsprocvne = this.getLsprocvne();
        result = result * PRIME + ($lsprocvne == null ? 43 : $lsprocvne.hashCode());
        final Object $vidkred = this.getVidkred();
        result = result * PRIME + ($vidkred == null ? 43 : $vidkred.hashCode());
        final Object $lsrezerv = this.getLsrezerv();
        result = result * PRIME + ($lsrezerv == null ? 43 : $lsrezerv.hashCode());
        final Object $tip = this.getTip();
        result = result * PRIME + ($tip == null ? 43 : $tip.hashCode());
        final Object $minvznos = this.getMinvznos();
        result = result * PRIME + ($minvznos == null ? 43 : $minvznos.hashCode());
        final Object $komissy = this.getKomissy();
        result = result * PRIME + ($komissy == null ? 43 : $komissy.hashCode());
        final Object $lgot = this.getLgot();
        result = result * PRIME + ($lgot == null ? 43 : $lgot.hashCode());
        final Object $progress = this.getProgress();
        result = result * PRIME + ($progress == null ? 43 : $progress.hashCode());
        final Object $lizpredmet = this.getLizpredmet();
        result = result * PRIME + ($lizpredmet == null ? 43 : $lizpredmet.hashCode());
        final Object $lizprodovec = this.getLizprodovec();
        result = result * PRIME + ($lizprodovec == null ? 43 : $lizprodovec.hashCode());
        final Object $datsZakr = this.getDatsZakr();
        result = result * PRIME + ($datsZakr == null ? 43 : $datsZakr.hashCode());
        final Object $datsProsr = this.getDatsProsr();
        result = result * PRIME + ($datsProsr == null ? 43 : $datsProsr.hashCode());
        final Object $dopsoglDats = this.getDopsoglDats();
        result = result * PRIME + ($dopsoglDats == null ? 43 : $dopsoglDats.hashCode());
        final Object $spec = this.getSpec();
        result = result * PRIME + ($spec == null ? 43 : $spec.hashCode());
        final Object $tipliz = this.getTipliz();
        result = result * PRIME + ($tipliz == null ? 43 : $tipliz.hashCode());
        final Object $lsPeres = this.getLsPeres();
        result = result * PRIME + ($lsPeres == null ? 43 : $lsPeres.hashCode());
        final Object $graf = this.getGraf();
        result = result * PRIME + ($graf == null ? 43 : $graf.hashCode());
        final Object $autop = this.getAutop();
        result = result * PRIME + ($autop == null ? 43 : $autop.hashCode());
        final Object $lsKontrvne = this.getLsKontrvne();
        result = result * PRIME + ($lsKontrvne == null ? 43 : $lsKontrvne.hashCode());
        final Object $lsSpiskred = this.getLsSpiskred();
        result = result * PRIME + ($lsSpiskred == null ? 43 : $lsSpiskred.hashCode());
        final Object $datsIzm = this.getDatsIzm();
        result = result * PRIME + ($datsIzm == null ? 43 : $datsIzm.hashCode());
        final Object $datsIzmGrafik = this.getDatsIzmGrafik();
        result = result * PRIME + ($datsIzmGrafik == null ? 43 : $datsIzmGrafik.hashCode());
        final Object $datsIzmZalog = this.getDatsIzmZalog();
        result = result * PRIME + ($datsIzmZalog == null ? 43 : $datsIzmZalog.hashCode());
        final Object $objekt = this.getObjekt();
        result = result * PRIME + ($objekt == null ? 43 : $objekt.hashCode());
        final Object $valut = this.getValut();
        result = result * PRIME + ($valut == null ? 43 : $valut.hashCode());
        final Object $klass = this.getKlass();
        result = result * PRIME + ($klass == null ? 43 : $klass.hashCode());
        final Object $lsprosrProc = this.getLsprosrProc();
        result = result * PRIME + ($lsprosrProc == null ? 43 : $lsprosrProc.hashCode());
        final Object $ls22812 = this.getLs22812();
        result = result * PRIME + ($ls22812 == null ? 43 : $ls22812.hashCode());
        final Object $datsIzmAsoki = this.getDatsIzmAsoki();
        result = result * PRIME + ($datsIzmAsoki == null ? 43 : $datsIzmAsoki.hashCode());
        final Object $xatar = this.getXatar();
        result = result * PRIME + ($xatar == null ? 43 : $xatar.hashCode());
        final Object $lspeni = this.getLspeni();
        result = result * PRIME + ($lspeni == null ? 43 : $lspeni.hashCode());
        final Object $grkiClaimId = this.getGrkiClaimId();
        result = result * PRIME + ($grkiClaimId == null ? 43 : $grkiClaimId.hashCode());
        final Object $grkiAgreementId = this.getGrkiAgreementId();
        result = result * PRIME + ($grkiAgreementId == null ? 43 : $grkiAgreementId.hashCode());
        final Object $grkiContractId = this.getGrkiContractId();
        result = result * PRIME + ($grkiContractId == null ? 43 : $grkiContractId.hashCode());
        final Object $grafiks = this.getGrafiks();
        result = result * PRIME + ($grafiks == null ? 43 : $grafiks.hashCode());
        final Object $zalogs = this.getZalogs();
        result = result * PRIME + ($zalogs == null ? 43 : $zalogs.hashCode());
        final Object $zalogXranenieList = this.getZalogXranenieList();
        result = result * PRIME + ($zalogXranenieList == null ? 43 : $zalogXranenieList.hashCode());
        final Object $azolikFiz = this.getAzolikFiz();
        result = result * PRIME + ($azolikFiz == null ? 43 : $azolikFiz.hashCode());
        return result;
    }

    public String toString() {
        return "Kredit(kod=" + this.getKod() + ", numdog=" + this.getNumdog() + ", datadog=" + this.getDatadog() + ", dats=" + this.getDats() + ", summa=" + this.getSumma() + ", vidvalut=" + this.getVidvalut() + ", vidzalog=" + this.getVidzalog() + ", vidsrok=" + this.getVidsrok() + ", prosent=" + this.getProsent() + ", maqsad=" + this.getMaqsad() + ", sost=" + this.getSost() + ", status=" + this.getStatus() + ", prim=" + this.getPrim() + ", yurfiz=" + this.getYurfiz() + ", tipkred=" + this.getTipkred() + ", srokkred=" + this.getSrokkred() + ", users=" + this.getUsers() + ", lskred=" + this.getLskred() + ", lsproc=" + this.getLsproc() + ", lsprosrKred=" + this.getLsprosrKred() + ", ls_spiskred=" + this.getLs_spiskred() + ", sms=" + this.getSms() + ", tel=" + this.getTel() + ", kodDog=" + this.getKodDog() + ", procpeni=" + this.getProcpeni() + ", indpred=" + this.getIndpred() + ", sostLs=" + this.getSostLs() + ", dopsogl=" + this.getDopsogl() + ", lssudKred=" + this.getLssudKred() + ", nalbeznal=" + this.getNalbeznal() + ", vazvnalbeznal=" + this.getVazvnalbeznal() + ", sostProc=" + this.getSostProc() + ", rejnach=" + this.getRejnach() + ", autoe=" + this.getAutoe() + ", autos=" + this.getAutos() + ", autokred=" + this.getAutokred() + ", autoproc=" + this.getAutoproc() + ", dney=" + this.getDney() + ", chas=" + this.getChas() + ", lsDox=" + this.getLsDox() + ", lsprocvne=" + this.getLsprocvne() + ", vidkred=" + this.getVidkred() + ", lsrezerv=" + this.getLsrezerv() + ", tip=" + this.getTip() + ", minvznos=" + this.getMinvznos() + ", komissy=" + this.getKomissy() + ", lgot=" + this.getLgot() + ", progress=" + this.getProgress() + ", lizpredmet=" + this.getLizpredmet() + ", lizprodovec=" + this.getLizprodovec() + ", datsZakr=" + this.getDatsZakr() + ", datsProsr=" + this.getDatsProsr() + ", dopsoglDats=" + this.getDopsoglDats() + ", spec=" + this.getSpec() + ", tipliz=" + this.getTipliz() + ", lsPeres=" + this.getLsPeres() + ", graf=" + this.getGraf() + ", autop=" + this.getAutop() + ", lsKontrvne=" + this.getLsKontrvne() + ", lsSpiskred=" + this.getLsSpiskred() + ", datsIzm=" + this.getDatsIzm() + ", datsIzmGrafik=" + this.getDatsIzmGrafik() + ", datsIzmZalog=" + this.getDatsIzmZalog() + ", objekt=" + this.getObjekt() + ", valut=" + this.getValut() + ", klass=" + this.getKlass() + ", lsprosrProc=" + this.getLsprosrProc() + ", ls22812=" + this.getLs22812() + ", datsIzmAsoki=" + this.getDatsIzmAsoki() + ", xatar=" + this.getXatar() + ", lspeni=" + this.getLspeni() + ", grkiClaimId=" + this.getGrkiClaimId() + ", grkiAgreementId=" + this.getGrkiAgreementId() + ", grkiContractId=" + this.getGrkiContractId() + ", grafiks=" + this.getGrafiks() + ", zalogs=" + this.getZalogs() + ", zalogXranenieList=" + this.getZalogXranenieList() + ", azolikFiz=" + this.getAzolikFiz() + ")";
    }
}


