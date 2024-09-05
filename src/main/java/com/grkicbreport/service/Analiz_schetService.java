package com.grkicbreport.service;

import com.grkicbreport.model.Analiz_schetDTO;
import com.grkicbreport.repository.KreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class Analiz_schetService {

    private final KreditRepository kreditRepository;

    @Autowired
    public Analiz_schetService(KreditRepository kreditRepository) {
        this.kreditRepository = kreditRepository;
    }

    @Transactional(readOnly = true) // Используем Spring's @Transactional
    public List<Analiz_schetDTO> getAnaliz_schet(Date date, String bal) {
        return kreditRepository.callAnaliz_schet(date, bal);
    }
    @Transactional(readOnly = true) // Используем Spring's @Transactional
    public List<String> Analiz_schet(Date date, String bal) {
        return kreditRepository.calAnaliz_schet(date, bal);
    }
}
