package com.grkicbreport.repository;

import com.grkicbreport.model.Dok;
import com.grkicbreport.model.Dokument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DokRepository extends JpaRepository<Dok, Long> {

    @Query("SELECT d FROM Dokument d " +
            "WHERE (d.ls = :lskred OR d.ls = :lsproc OR d.ls = :lsprosrKred OR d.ls = :lsprosrProc OR d.ls = :lspeni or d.ls like :lsSud) " +
            "AND (d.lscor LIKE '10101%' OR d.lscor LIKE '10503%' or d.lscor like '10509%' or d.lscor like :ls22812) or" +
            " ((d.ls = :lsproc AND d.lscor = :lsprosrProc) OR (d.ls = :lskred AND d.lscor = :lsprosrKred))")
    List<Dokument> findByKreditComplexConditions(@Param("lskred") String lskred,
                                                 @Param("lsproc") String lsproc,
                                                 @Param("lsprosrKred") String lsprosrKred,
                                                 @Param("lsprosrProc") String lsprosrProc,
                                                 @Param("lspeni") String lspeni,
                                                 @Param("ls22812") String ls22812,
                                                 @Param("lsSud") String lsSud);

    List<Dok> findAllByDats(LocalDate localDate);

}
