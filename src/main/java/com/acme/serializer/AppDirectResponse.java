package com.acme.serializer;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name="result")
//todo check if this is the best way with spring 3
public class AppDirectResponse {
    private Boolean success;
    private String errorCode;
    private String message;
    private String accountIdentifier; // for 'create' events
}
