package com.grkicbreport.repository;

import com.grkicbreport.model.Dok;
import com.grkicbreport.model.Dokument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DokRepository extends JpaRepository<Dok, Long> {
    List<Dok> findAllByDatProv(Date dats);

    List<Dok> getDokumentByLsAndDats(String ls, LocalDate dats);

    List<Dok> getDokumentByLscorAndDats(String lscor, LocalDate dats);
}
