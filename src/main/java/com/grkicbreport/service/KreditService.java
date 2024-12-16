package com.grkicbreport.service;

import com.grkicbreport.dto.KreditDTO;
import com.grkicbreport.model.Kredit;
import com.grkicbreport.repository.KreditRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KreditService {

    private final KreditRepository kreditRepository;

    public KreditService(KreditRepository kreditRepository) {
        this.kreditRepository = kreditRepository;
    }

    public List<KreditDTO> findCreditsByStatus() {
        List<Kredit> kredits = kreditRepository.findByDatsZakrIsNull();
        return kredits.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private KreditDTO convertToDTO(Kredit kredit) {
        KreditDTO dto = new KreditDTO();
        dto.setKod(kredit.getKod());
        dto.setNumdog(kredit.getNumdog());
        dto.setDatadog(kredit.getDatadog());
        dto.setDats(kredit.getDats());
        dto.setSumma(kredit.getSumma());
        dto.setVidvalut(kredit.getVidvalut());
        dto.setVidzalog(kredit.getVidzalog());
        dto.setVidsrok(kredit.getVidsrok());
        dto.setProsent(kredit.getProsent());
        dto.setMaqsad(kredit.getMaqsad());
        dto.setSost(kredit.getSost());
        dto.setStatus(kredit.getStatus());
        dto.setPrim(kredit.getPrim());
        dto.setYurfiz(kredit.getYurfiz());
        dto.setTipkred(kredit.getTipkred());
        dto.setSrokkred(kredit.getSrokkred());
        dto.setUsers(kredit.getUsers());
        dto.setLskred(kredit.getLskred());
        dto.setLsproc(kredit.getLsproc());
        dto.setLsprosrKred(kredit.getLsprosrKred());
        dto.setSms(kredit.getSms());
        dto.setTel(kredit.getTel());
        dto.setKodDog(kredit.getKodDog());
        dto.setIndpred(kredit.getIndpred());
        dto.setSostLs(kredit.getSostLs());
        dto.setDopsogl(kredit.getDopsogl());
        dto.setLssudKred(kredit.getLssudKred());
        dto.setNalbeznal(kredit.getNalbeznal());
        dto.setVazvnalbeznal(kredit.getVazvnalbeznal());
        dto.setSostProc(kredit.getSostProc());
        dto.setRejnach(kredit.getRejnach());
        dto.setAutoe(kredit.getAutoe());
        dto.setAutos(kredit.getAutos());
        dto.setAutokred(kredit.getAutokred());
        dto.setAutoproc(kredit.getAutoproc());
        dto.setDney(kredit.getDney());
        dto.setChas(kredit.getChas());
        dto.setLsDox(kredit.getLsDox());
        dto.setLsprocvne(kredit.getLsprocvne());
        dto.setVidkred(kredit.getVidkred());
        dto.setLsrezerv(kredit.getLsrezerv());
        dto.setTip(kredit.getTip());
        dto.setMinvznos(kredit.getMinvznos());
        dto.setKomissy(kredit.getKomissy());
        dto.setLgot(kredit.getLgot());
        dto.setProgress(kredit.getProgress());
        dto.setLizpredmet(kredit.getLizpredmet());
        dto.setLizprodovec(kredit.getLizprodovec());
        dto.setDatsZakr(kredit.getDatsZakr());
        dto.setDatsProsr(kredit.getDatsProsr());
        dto.setDopsoglDats(kredit.getDopsoglDats());
        dto.setSpec(kredit.getSpec());
        dto.setTipliz(kredit.getTipliz());
        dto.setLsPeres(kredit.getLsPeres());
        dto.setGraf(kredit.getGraf());
        dto.setAutop(kredit.getAutop());
        dto.setLsKontrvne(kredit.getLsKontrvne());
        dto.setLsSpiskred(kredit.getLsSpiskred());
        dto.setDatsIzm(kredit.getDatsIzm());
        dto.setDatsIzmGrafik(kredit.getDatsIzmGrafik());
        dto.setDatsIzmZalog(kredit.getDatsIzmZalog());
        dto.setObjekt(kredit.getObjekt());
        dto.setValut(kredit.getValut());
        dto.setKlass(kredit.getKlass());
        dto.setLsprosrProc(kredit.getLsprosrProc());
        dto.setLs22812(kredit.getLs22812());
        dto.setDatsIzmAsoki(kredit.getDatsIzmAsoki());
        dto.setXatar(kredit.getXatar());
        dto.setLspeni(kredit.getLspeni());
        dto.setGrkiClaimId(kredit.getGrkiClaimId());
        dto.setGrkiAgreementId(kredit.getGrkiAgreementId());
        dto.setGrkiContractId(kredit.getGrkiContractId());
        dto.setProcpeni(kredit.getProcpeni());
        return dto;
    }
}
