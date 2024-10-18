package com.grkicbreport.repository;

import com.grkicbreport.model.Analiz_schetDTO;
import com.grkicbreport.model.Kredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface KreditRepository extends JpaRepository<Kredit, String> {
    @Query(value = "SELECT * FROM kredit WHERE numdog = :kod", nativeQuery = true)
    Optional<Kredit> findByNumdog(@Param("kod") String kod);

    @Query(value = "EXEC analiz_schet :date, :bal", nativeQuery = true)
    List<Analiz_schetDTO> callAnaliz_schet(@Param("date") Date date, @Param("bal") String bal);

    @Query(value = "EXEC analiz_schet :date, :bal", nativeQuery = true)
    List<String> calAnaliz_schet(@Param("date") Date date, @Param("bal") String bal);

    @Modifying
    @Transactional
    @Query("UPDATE Kredit k SET k.grkiClaimId = :grkiClaimId WHERE k.numdog = :numdog")
    void updateGrkiClaimId(@Param("grkiClaimId") String grkiClaimId, @Param("numdog") String numdog);

    @Modifying
    @Transactional
    @Query("UPDATE Kredit k SET k.grkiAgreementId = :grkiAgreementId WHERE k.numdog = :numdog")
    void updateGrkiAgreementId(@Param("grkiAgreementId") String grkiAgreementId, @Param("numdog") String numdog);

    @Modifying
    @Transactional
    @Query("UPDATE Kredit k SET k.grkiContractId = :grkiContractId WHERE k.numdog = :numdog")
    void updateGrkiContractId(@Param("grkiContractId") String grkiContractId, @Param("numdog") String numdog);



}
