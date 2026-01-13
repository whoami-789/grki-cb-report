package com.grkicbreport.repository;

import com.grkicbreport.model.AzolikFiz;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface AzolikFizRepository extends JpaRepository<AzolikFiz, String> {

    Optional<AzolikFiz> findByKodchlen(String kodchlen);

    List<AzolikFiz> findCrifByKodchlen(String kodchlen);

    @Query("SELECT s.nameu FROM SprRayon s WHERE s.kod = :kod")
    Optional<String> findNameuByKod(String kod);

    @Modifying
    @Transactional
    @Query(value = """
        update dbo.azolik_fiz
        set mahalla = :mahallaCode
        where kodchlen = :clientCode
    """, nativeQuery = true)
    int updateMahalla(
            @Param("clientCode") String clientCode,
            @Param("mahallaCode") String mahallaCode
    );
}
