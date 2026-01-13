package com.grkicbreport.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "spr_mahalla")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SprMahalla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uz_kad")
    private Long uzKad1;

    @Column(name = "code_1c")
    private Long code1c;

    @Column(name = "uz_kad_2")
    private Long uzKad2;

    @Column(name = "mahalla_inn", length = 32)
    private String mahallaInn;

    @Column(name = "soato_region")
    private Integer soatoRegion;

    @Column(name = "soato_district")
    private Integer soatoDistrict;

    @Column(name = "cb_district", length = 32)
    private String cbDistrict;

    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "date_active")
    private LocalDate dateActive;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @Column(name = "active_flag", length = 1)
    private String activeFlag;
}