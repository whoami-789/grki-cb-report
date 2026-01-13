package com.grkicbreport.service;

import com.grkicbreport.repository.AzolikFizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientMahallaService {

    private final AzolikFizRepository repository;

    @Transactional
    public void saveMahalla(String clientCode, String mahallaCode) {
        repository.updateMahalla(clientCode, mahallaCode);
    }
}