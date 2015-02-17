package com.acme.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String lastName;

    @NotNull
    private String firstName;

    @Column(unique = true)
    private String openId;

    // sadly Email annotation is hibernate only
    @Column(unique = true)
    @NonNull
    private String email;

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    private Company company;
}
