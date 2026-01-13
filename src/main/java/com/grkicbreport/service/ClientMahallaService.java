package com.grkicbreport.service;

import com.grkicbreport.model.SprMahalla;
import com.grkicbreport.repository.AzolikFizRepository;
import com.grkicbreport.repository.MahallaRepository;
import com.grkicbreport.repository.SprMahallaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientMahallaService {

    private final AzolikFizRepository repository;
    private final SprMahallaRepository sprMahallaRepository;

    @Transactional
    public void saveMahalla(String clientCode, String mahallaInn) {
        Optional<SprMahalla> mahallaCode = sprMahallaRepository.findByMahallaInn(mahallaInn);

        repository.updateMahalla(clientCode, mahallaCode.get().getUzKad());
    }
}