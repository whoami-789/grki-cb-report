package com.grkicbreport.repository;

import com.grkicbreport.model.Dokument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DokumRepository extends JpaRepository<Dokument, Long> {
    List<Dokument> getDokumentByLs(String ls);
    List<Dokument> getDokumentByLscor(String lscor);
}
