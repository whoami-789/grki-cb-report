package com.grkicbreport.repository;

import com.grkicbreport.model.AzolikFiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface AzolikFizRepository extends JpaRepository<AzolikFiz, String> {

    Optional<AzolikFiz> findByKodchlen(String kodchlen);

}
