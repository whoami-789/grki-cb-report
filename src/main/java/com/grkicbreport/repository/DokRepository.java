package com.grkicbreport.repository;

import com.grkicbreport.model.Dok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DokRepository extends JpaRepository<Dok, Long> {
    List<Dok> findAllByDatProv(Date dats);

    List<Dok> getDokumentByLsAndDats(String ls, LocalDate dats);

    List<Dok> getDokumentByLscorAndDats(String lscor, LocalDate dats);

    @Procedure(name = "analiz_schet")
    List<String> analiz_schet(@Param("dats") String dats, @Param("bal")  String bal);}
