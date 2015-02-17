package com.acme.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * For this demo this it bootstrapped but in the real world this would map a materialized-view
 */
@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class MonthlySale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    private Product product;

    @NonNull
    @NotNull
    private Integer year;

    @NonNull
    @NotNull
    private Integer month;
    
    @NonNull
    @NotNull
    private Integer sales;
}
