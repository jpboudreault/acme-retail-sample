package com.acme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Only support AppDirect's users (no passw and orphans)
 */
@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    private Company company;

    // sadly Email annotation is hibernate only
    @NonNull
    @NotNull
    @Column(unique = true)
    private String email;

    @NonNull
    @NotNull
    private String firstName;

    @NonNull
    @NotNull
    private String lastName;

    @JsonIgnore
    @NonNull
    @NotNull
    @Column(unique = true)
    private String openId;

    @JsonIgnore
    @NonNull
    @NotNull
    @Column(unique = true)
    private String uuid;
}
