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

    @Column(name = "uz_kad", length = 16)
    private String uzKad;

    @Column(name = "code_1c", length = 16)
    private String code1c;

    @Column(name = "uz_kad_2", length = 16)
    private String uzKad2;

    @Column(name = "mahalla_inn", length = 32)
    private String mahallaInn;

    @Column(name = "soato_region", length = 16)
    private String soatoRegion;

    @Column(name = "soato_district", length = 16)
    private String soatoDistrict;

    @Column(name = "cb_district", length = 16)
    private String cbDistrict;

    @Column(name = "name_uz", length = 256)
    private String nameUz;

    @Column(name = "name_ru", length = 256)
    private String nameRu;

    @Column(name = "name_en", length = 256)
    private String nameEn;

    @Column(name = "date_active")
    private LocalDate dateActive;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @Column(name = "active_flag", length = 1)
    private String activeFlag;
}