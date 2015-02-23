package com.acme.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Tightly coupled to AppDirect's company model
 */
@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    @NotNull
    @Column(unique = true)
    private String name;

    private String notice;

    @NonNull
    @NotNull
    @Column(unique = true)
    private String uuid;

    @NonNull
    @NotNull
    private String editionCode;
}
