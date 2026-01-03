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
    List<Dok> findAllByDatProv(Date dats);

    Optional<Dok> getDokumentByLsAndDats(String ls, LocalDate dats);

    Optional<Dok> getDokumentByLscorAndDats(String lscor, LocalDate dats);

    @Query(value = "EXEC analiz_schet :date, :bal", nativeQuery = true)
    List<String> Analiz_schet(@Param("date") Date date, @Param("bal") String bal);

    @Query(value = "EXEC analiz_schet :date, :bal", nativeQuery = true)
    Optional<String> prevAnaliz_schet(@Param("date") Date date, @Param("bal") String bal);

    List<Dok> findByLsAndDats(String account, LocalDate localDate);

    List<Dok> findByLscorAndDats(String account, LocalDate localDate);

    @Query("SELECT d FROM Dok d WHERE d.ls LIKE CONCAT(:prefix, '%') AND d.dats = :date")
    List<Dok> findByLsStartingWithAndDats(@Param("prefix") String prefix, @Param("date") LocalDate date);

    @Query("SELECT d FROM Dok d WHERE d.lscor LIKE CONCAT(:prefix, '%') AND d.dats = :date")
    List<Dok> findByLscorStartingWithAndDats(@Param("prefix") String prefix, @Param("date") LocalDate date);

    List<Dok> findAllByDatsAndSost(LocalDate localDate, int sost);

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
}
