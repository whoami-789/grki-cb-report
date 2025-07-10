package com.grkicbreport.repository;

import com.grkicbreport.model.Analiz_schetDTO;
import com.grkicbreport.model.Dok;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
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
}
