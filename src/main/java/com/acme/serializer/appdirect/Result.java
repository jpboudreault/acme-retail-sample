package com.acme.serializer.appdirect;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
public class Result {
    private Boolean success;
    private String message;
    private String accountIdentifier; // for 'create' events
}
