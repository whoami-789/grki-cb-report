package com.grkicbreport.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "dokum")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dokument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kod")
    private Long id;

    @Column(name = "numdok", nullable = false, length = 10)
    private String numdok;

    @Column(name = "dats", nullable = false)
    private LocalDate dats;

    @Column(name = "tipdoc", nullable = false)
    private Byte tipdoc;

    @Column(name = "ls", nullable = false, length = 20)
    private String ls;

    @Column(name = "lscor", nullable = false, length = 20)
    private String lscor;

    @Column(name = "nazn", nullable = false, length = 160)
    private String nazn;

    @Column(name = "sost", nullable = false)
    private Byte sost;

    @Column(name = "users", nullable = false)
    private Short users;

    @Column(name = "dtime", nullable = false)
    private LocalDateTime dtime = LocalDateTime.now();

    @Column(name = "sums", precision = 18, scale = 2)
    private BigDecimal sums;

    @Column(name = "dat_prov")
    private LocalDate datProv;

    @Column(name = "kod_dog", nullable = false, columnDefinition = "int default 0")
    private Integer kodDog = 0;

    @Column(name = "datsproc")
    private LocalDate datsproc;

    @Column(name = "koduch", length = 8)
    private String koduch;

    @Column(name = "prixgroup")
    private Integer prixgroup;

    @Column(name = "groupstr")
    private Byte groupstr;


}
