package com.acme.serializer.appdirect;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    private String type;
    private Marketplace marketplace;
    private Payload payload;
    private Creator creator;

    private Boolean success;
    private String message;
    private String accountIdentifier; // for 'create' events
}
