package com.grkicbreport.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "mahalla"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mahalla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dict_code", nullable = false)
    private Integer dictCode;

    @Column(name = "mahalla_inn", nullable = false, length = 32)
    private String inn;

    @Column(name = "mahalla_name", nullable = false)
    private String name;

    @Column(name = "mahalla_code", nullable = false, length = 32)
    private String code;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "region_cb_code")
    private String regionCbCode;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "sector_no")
    private Integer sectorNo;

    @Column(name = "district_soato_code")
    private String districtSoatoCode;

    @Column(name = "district_cb_code")
    private String districtCbCode;

    @Column(name = "date_active")
    private LocalDate dateActive;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @Column(name = "active_flag", length = 1)
    private String activeFlag;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "name_norm")
    private String nameNorm;
}