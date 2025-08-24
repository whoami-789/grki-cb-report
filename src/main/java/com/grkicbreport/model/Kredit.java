package com.grkicbreport.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Column(name = "status")
    private Byte status;

    @Transient
    private String yurfiz;

    @Column(name = "tipkred")
    private Short tipkred;

    @Column(name = "srokkred")
    private Byte srokkred;

    @Column(name = "lskred", nullable = false, columnDefinition = "nchar(20)")
    private String lskred;

    @Column(name = "lsproc", nullable = false, columnDefinition = "nchar(20)")
    private String lsproc;

    @Column(name = "tel")
    private Byte tel;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kod_dog", updatable = false, nullable = false)
    private Integer kodDog;

    @Column(name = "lssud_kred")
    private String lssudKred;

    @Column(name = "nalbeznal")
    private Byte nalbeznal;

    @Column(name = "dats_zakr")
    private LocalDate datsZakr;

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

    public String getLsprosrProc() {
        return lsprosrProc;
    }

    public void setLsprosrProc(String lsprosrProc) {
        this.lsprosrProc = lsprosrProc;
    }

    @Column(name = "lsprosr_proc")
    private String lsprosrProc;

    @ManyToOne
    @JoinColumn(name = "kod", referencedColumnName = "kodchlen", insertable = false, updatable = false)
    private AzolikFiz azolikFiz;

    public Kredit() {
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

    public Byte getStatus() {
        return this.status;
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

    public String getLskred() {
        return this.lskred;
    }

    public String getLsproc() {
        return this.lsproc;
    }

    public Byte getTel() {
        return this.tel;
    }

    public Integer getKodDog() {
        return this.kodDog;
    }

    public String getLssudKred() {
        return this.lssudKred;
    }

    public Byte getNalbeznal() {
        return this.nalbeznal;
    }

    public LocalDate getDatsZakr() {
        return this.datsZakr;
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

    public void setStatus(Byte status) {
        this.status = status;
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

    public void setLskred(String lskred) {
        this.lskred = lskred;
    }

    public void setLsproc(String lsproc) {
        this.lsproc = lsproc;
    }

    public void setTel(Byte tel) {
        this.tel = tel;
    }

    public void setKodDog(Integer kodDog) {
        this.kodDog = kodDog;
    }

    public void setLssudKred(String lssudKred) {
        this.lssudKred = lssudKred;
    }

    public void setNalbeznal(Byte nalbeznal) {
        this.nalbeznal = nalbeznal;
    }

    public void setDatsZakr(LocalDate datsZakr) {
        this.datsZakr = datsZakr;
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
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$yurfiz = this.getYurfiz();
        final Object other$yurfiz = other.getYurfiz();
        if (this$yurfiz == null ? other$yurfiz != null : !this$yurfiz.equals(other$yurfiz)) return false;
        final Object this$tipkred = this.getTipkred();
        final Object other$tipkred = other.getTipkred();
        if (this$tipkred == null ? other$tipkred != null : !this$tipkred.equals(other$tipkred)) return false;
        final Object this$srokkred = this.getSrokkred();
        final Object other$srokkred = other.getSrokkred();
        if (this$srokkred == null ? other$srokkred != null : !this$srokkred.equals(other$srokkred)) return false;
        final Object this$lskred = this.getLskred();
        final Object other$lskred = other.getLskred();
        if (this$lskred == null ? other$lskred != null : !this$lskred.equals(other$lskred)) return false;
        final Object this$lsproc = this.getLsproc();
        final Object other$lsproc = other.getLsproc();
        if (this$lsproc == null ? other$lsproc != null : !this$lsproc.equals(other$lsproc)) return false;
        final Object this$tel = this.getTel();
        final Object other$tel = other.getTel();
        if (this$tel == null ? other$tel != null : !this$tel.equals(other$tel)) return false;
        final Object this$kodDog = this.getKodDog();
        final Object other$kodDog = other.getKodDog();
        if (this$kodDog == null ? other$kodDog != null : !this$kodDog.equals(other$kodDog)) return false;
        final Object this$lssudKred = this.getLssudKred();
        final Object other$lssudKred = other.getLssudKred();
        if (this$lssudKred == null ? other$lssudKred != null : !this$lssudKred.equals(other$lssudKred)) return false;
        final Object this$nalbeznal = this.getNalbeznal();
        final Object other$nalbeznal = other.getNalbeznal();
        if (this$nalbeznal == null ? other$nalbeznal != null : !this$nalbeznal.equals(other$nalbeznal)) return false;
        final Object this$datsZakr = this.getDatsZakr();
        final Object other$datsZakr = other.getDatsZakr();
        if (this$datsZakr == null ? other$datsZakr != null : !this$datsZakr.equals(other$datsZakr)) return false;
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

    protected boolean canEqual(final Object other) {
        return other instanceof Kredit;
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
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $yurfiz = this.getYurfiz();
        result = result * PRIME + ($yurfiz == null ? 43 : $yurfiz.hashCode());
        final Object $tipkred = this.getTipkred();
        result = result * PRIME + ($tipkred == null ? 43 : $tipkred.hashCode());
        final Object $srokkred = this.getSrokkred();
        result = result * PRIME + ($srokkred == null ? 43 : $srokkred.hashCode());
        final Object $lskred = this.getLskred();
        result = result * PRIME + ($lskred == null ? 43 : $lskred.hashCode());
        final Object $lsproc = this.getLsproc();
        result = result * PRIME + ($lsproc == null ? 43 : $lsproc.hashCode());
        final Object $tel = this.getTel();
        result = result * PRIME + ($tel == null ? 43 : $tel.hashCode());
        final Object $kodDog = this.getKodDog();
        result = result * PRIME + ($kodDog == null ? 43 : $kodDog.hashCode());
        final Object $lssudKred = this.getLssudKred();
        result = result * PRIME + ($lssudKred == null ? 43 : $lssudKred.hashCode());
        final Object $nalbeznal = this.getNalbeznal();
        result = result * PRIME + ($nalbeznal == null ? 43 : $nalbeznal.hashCode());
        final Object $datsZakr = this.getDatsZakr();
        result = result * PRIME + ($datsZakr == null ? 43 : $datsZakr.hashCode());
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
        return "Kredit(kod=" + this.getKod() + ", numdog=" + this.getNumdog() + ", datadog=" + this.getDatadog() + ", dats=" + this.getDats() + ", summa=" + this.getSumma() + ", vidvalut=" + this.getVidvalut() + ", vidzalog=" + this.getVidzalog() + ", vidsrok=" + this.getVidsrok() + ", prosent=" + this.getProsent() + ", status=" + this.getStatus() + ", yurfiz=" + this.getYurfiz() + ", tipkred=" + this.getTipkred() + ", srokkred=" + this.getSrokkred() + ", lskred=" + this.getLskred() + ", lsproc=" + this.getLsproc() + ", tel=" + this.getTel() + ", kodDog=" + this.getKodDog() + ", lssudKred=" + this.getLssudKred() + ", nalbeznal=" + this.getNalbeznal() + ", datsZakr=" + this.getDatsZakr() + ", grkiClaimId=" + this.getGrkiClaimId() + ", grkiAgreementId=" + this.getGrkiAgreementId() + ", grkiContractId=" + this.getGrkiContractId() + ")";
    }
}

