package com.grkicbreport.repository;

import com.grkicbreport.model.AzolikFiz;
import com.grkicbreport.model.AzolikYur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface AzolikYurRepository extends JpaRepository<AzolikYur, String>{

    Optional<AzolikYur> findByKodchlen(String kodchlen);

}
