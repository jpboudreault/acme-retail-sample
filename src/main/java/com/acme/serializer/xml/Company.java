package com.acme.serializer.xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {
    private String uuid;
    private String email;
    private String name;
    private String phoneNumber;
    private String website;
}
