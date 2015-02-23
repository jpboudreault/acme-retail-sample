package com.acme.serializer.xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String openId;
    private String language;
    private String uuid;
}