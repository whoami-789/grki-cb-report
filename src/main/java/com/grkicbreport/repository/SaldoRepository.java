package com.grkicbreport.repository;

import com.grkicbreport.model.Saldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface SaldoRepository extends JpaRepository<Saldo, Long> {
    @Query("select distinct s from Saldo s WHERE (s.ls = :lskred OR s.ls = :lsproc OR s.ls = :lsprosrKred OR s.ls = :lsprosrProc OR s.ls = :lspeni or s.ls = :lssud)")
    List<Saldo> findByDokumentLscor(@Param("lskred") String lskred,
                                    @Param("lsproc") String lsproc,
                                    @Param("lsprosrKred") String lsprosrKred,
                                    @Param("lsprosrProc") String lsprosrProc,
                                    @Param("lspeni") String lspeni,
                                    @Param("lssud") String lssud);

    @Query(value = "SELECT TOP 1 s.sums FROM Saldo s " +
            "WHERE s.ls = :ls " +
            "AND ((MONTH(s.dats) = MONTH(:date) AND YEAR(s.dats) = YEAR(:date)) " +
            "OR (EXISTS(SELECT 1 FROM Saldo s_inner WHERE s_inner.ls = :ls " +
            "AND YEAR(s_inner.dats) = YEAR(:date) " +
            "AND MONTH(s_inner.dats) < MONTH(:date)))) " +
            "ORDER BY ABS(DATEDIFF(day, s.dats, :date)) ASC", nativeQuery = true)
    BigDecimal findSumsByLsAndClosestDats(@Param("ls") String ls, @Param("date") LocalDate date);

    @Query(value = "SELECT TOP 1 s.dats FROM Saldo s " +
            "WHERE s.ls = :ls " +
            "AND s.dats <= :date " +
            "ORDER BY s.dats DESC", nativeQuery = true)
    LocalDate findClosestDateByLs(@Param("ls") String ls, @Param("date") LocalDate date);

    @Query(value = "SELECT s.sums FROM Saldo s " +
            "WHERE s.ls = :ls " +
            "AND s.dats = :dats", nativeQuery = true)
    BigDecimal findSumsByLsAndDats(@Param("ls") String ls, @Param("dats") LocalDate dats);
}
