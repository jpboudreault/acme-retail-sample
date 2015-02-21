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

    // for now ignore this one in json to avoid the need of creating a custom view when it's not loaded
    // FIXME create a json view if time allows and clean the need for all the jsonignore
    @JsonIgnore
    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    private Company company;

    // sadly Email annotation is hibernate only
    @JsonIgnore
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
