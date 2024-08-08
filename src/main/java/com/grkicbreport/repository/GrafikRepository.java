package com.grkicbreport.repository;

import org.creditbureaureport.model.Grafik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface GrafikRepository extends JpaRepository<Grafik, String> {

    int countGrafikByNumdog(String numdog);

    @Query(value = "SELECT COALESCE( " +
            "CASE " +
            "WHEN :dats > (SELECT MAX(g.dats) FROM Grafik g WHERE g.numdog = :numdog) THEN " +
            "DATEDIFF(MONTH, (SELECT MAX(g.dats) FROM Grafik g WHERE g.numdog = :numdog), :dats) " +
            "ELSE (SELECT count(g.dats) FROM Grafik g " +
            "WHERE g.numdog = :numdog " +
            "AND month(g.dats) = month(:dats) AND year(g.dats) = year(:dats)) " +
            "END, 0)",
            nativeQuery = true)
    Integer findGrafikByNumdogAndDats(@Param("numdog") String numdog, @Param("dats") LocalDate dats);
}
