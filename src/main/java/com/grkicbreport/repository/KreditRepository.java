package com.grkicbreport.repository;

import com.grkicbreport.model.Analiz_schetDTO;
import com.grkicbreport.model.Kredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Repository
public interface KreditRepository extends JpaRepository<Kredit, String> {

    Optional<Kredit> findByNumdog(String kod);

    @Query(value = "EXEC analiz_schet :date, :bal", nativeQuery = true)
    List<Analiz_schetDTO> callAnaliz_schet(@Param("date") Date date, @Param("bal") String bal);

    @Query(value = "EXEC analiz_schet :date, :bal", nativeQuery = true)
    List<String> calAnaliz_schet(@Param("date") Date date, @Param("bal") String bal);

    @Query(value = "EXEC creat_otch_13_001 :start_date, :end_date", nativeQuery = true)
    List<String> cb_otch(@Param("start_date") Date start_date, @Param("end_date")  Date end_date);

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

    List<Kredit> findByDatsZakrIsNull();

    @Query("SELECT k FROM Kredit k WHERE k.status = :status")
    List<Kredit> findCreditsByStatus(@Param("status") int status);

    @Query(value = "SELECT * FROM kredit WHERE lskred = :lskred", nativeQuery = true)
    Optional<Kredit> findKreditByLskred(@Param("lskred") String lskred);

    @Query(value = "SELECT * FROM kredit WHERE lsprosr_kred = :lskred", nativeQuery = true)
    Optional<Kredit> findKreditByLsProsrkred(@Param("lskred") String lskred);

    @Query(value = "SELECT * FROM kredit WHERE ls_spiskred LIKE :pattern", nativeQuery = true)
    List<Kredit> findByLsSpiskred(@Param("pattern") String pattern);

    @Query(value = "SELECT * FROM kredit WHERE [grki-contract-id] = :grkiContractId", nativeQuery = true)
    Optional<Kredit> findByGrkiContractId(String grkiContractId);


    @Query(value = "EXEC dbo.creat_report_008 :p1, :p2", nativeQuery = true)
    List<String> getReport008(@Param("p1") Date p1, @Param("p2") Date p2);



}
