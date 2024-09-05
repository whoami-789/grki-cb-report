package com.grkicbreport.repository;

import com.grkicbreport.model.Dok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface DokRepository extends JpaRepository<Dok, Long> {
    List<Dok> findAllByDatProv(Date dats);
}
