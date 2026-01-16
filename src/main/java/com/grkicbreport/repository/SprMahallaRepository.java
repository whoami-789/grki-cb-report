package com.grkicbreport.repository;

import com.grkicbreport.model.SprMahalla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SprMahallaRepository extends JpaRepository<SprMahalla, Long> {

    Optional<SprMahalla> findByMahallaInn(String mahallaInn);

}