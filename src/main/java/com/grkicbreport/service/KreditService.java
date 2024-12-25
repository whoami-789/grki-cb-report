package com.grkicbreport.service;

import com.grkicbreport.dto.KreditDTO;
import com.grkicbreport.model.Kredit;
import com.grkicbreport.repository.KreditRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KreditService {

    private final KreditRepository kreditRepository;

    public KreditService(KreditRepository kreditRepository) {
        this.kreditRepository = kreditRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<KreditDTO> findCreditsByStatus() {
        // Выполняем нативный SQL-запрос
        String sql = "SELECT * FROM kredit WHERE dats_zakr IS NULL";
        Query query = entityManager.createNativeQuery(sql, Kredit.class);

        // Приводим результат к списку объектов Kredit
        List<Kredit> kredits = query.getResultList();

        // Конвертируем в DTO
        return kredits.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private KreditDTO convertToDTO(Kredit kredit) {
        KreditDTO dto = new KreditDTO();
        dto.setKod(kredit.getKod());
        dto.setDats_zakr(kredit.getDatsZakr());
        dto.setNumdog(kredit.getNumdog());
        dto.setDatadog(kredit.getDatadog());
        dto.setSumma(kredit.getSumma());
        dto.setGrkiClaimId(kredit.getGrkiClaimId());
        dto.setGrkiContractId(kredit.getGrkiContractId());
        return dto;
    }
}