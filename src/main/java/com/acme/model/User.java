package com.acme.model;

import com.acme.serializer.json.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.soap.Detail;

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

    @JsonView(value = Detail.class)
    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    private Company company;

    // sadly Email annotation is hibernate only
    @JsonView(value = Detail.class)
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
