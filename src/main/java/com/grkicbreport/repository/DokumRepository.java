package com.grkicbreport.repository;

import com.grkicbreport.model.Dokument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DokumRepository extends JpaRepository<Dokument, Long> {
    List<Dokument> getDokumentByLsAndDats(String ls, LocalDate dats);
    List<Dokument> getDokumentByLscor(String lscor);
}
