package com.grkicbreport.repository;

import com.grkicbreport.model.SprMahalla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprMahallaRepository extends JpaRepository<SprMahalla, Long> {
}