package com.grkicbreport.repository;

import com.grkicbreport.model.Mahalla;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MahallaRepository extends JpaRepository<Mahalla, Long>, JpaSpecificationExecutor<Mahalla> {

    @Query("""
              select m from Mahalla m
              where m.isActive = true
                and (
                  lower(m.name) like concat('%', :q, '%')
                  or lower(m.regionName) like concat('%', :q, '%')
                  or lower(m.districtName) like concat('%', :q, '%')
                )
              order by m.name asc
            """)
    List<Mahalla> searchByNameRegionDistrict(@Param("q") String q, Pageable pageable);

}