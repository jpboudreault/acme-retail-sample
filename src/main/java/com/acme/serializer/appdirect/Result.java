package com.acme.serializer.appdirect;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    private Boolean success;
    private String message;
    private String accountIdentifier; // for 'create' events
}
