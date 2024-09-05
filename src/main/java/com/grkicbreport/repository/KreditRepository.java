package com.grkicbreport.repository;

import com.grkicbreport.model.Analiz_schetDTO;
import com.grkicbreport.model.Kredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface KreditRepository extends JpaRepository<Kredit, String> {
    Optional<Kredit> findByNumdog(String kod);

    @Query(value = "EXEC analiz_schet :date, :bal", nativeQuery = true)
    List<Analiz_schetDTO> callAnaliz_schet(@Param("date") Date date, @Param("bal") String bal);

    @Query(value = "EXEC analiz_schet :date, :bal", nativeQuery = true)
    List<String> calAnaliz_schet(@Param("date") Date date, @Param("bal") String bal);


}
