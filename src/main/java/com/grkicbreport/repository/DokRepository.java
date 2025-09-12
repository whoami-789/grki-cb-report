package com.grkicbreport.repository;

import com.grkicbreport.model.Dok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query("SELECT d FROM Dok d WHERE d.ls LIKE CONCAT(:prefix, '%') AND d.dats = :date")
    List<Dok> findByLsStartingWithAndDats(@Param("prefix") String prefix, @Param("date") LocalDate date);

    @Query("SELECT d FROM Dok d WHERE d.lscor LIKE CONCAT(:prefix, '%') AND d.dats = :date")
    List<Dok> findByLscorStartingWithAndDats(@Param("prefix") String prefix, @Param("date") LocalDate date);

    List<Dok> findAllByDats(LocalDate localDate);

}
