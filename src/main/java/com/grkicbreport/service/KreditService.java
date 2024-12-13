package com.grkicbreport.service;

import com.grkicbreport.model.Kredit;
import com.grkicbreport.repository.KreditRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KreditService {

    private final KreditRepository kreditRepository;

    public KreditService(KreditRepository kreditRepository) {
        this.kreditRepository = kreditRepository;
    }

    public List<Kredit> findCreditsByStatus(Byte status) {
        return kreditRepository.findByStatus(status);
    }
}
