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
        dto.setSumma(kredit.getSumma());
        dto.setGrkiClaimId(kredit.getGrkiClaimId());
        dto.setGrkiContractId(kredit.getGrkiContractId());
        return dto;
    }
}
