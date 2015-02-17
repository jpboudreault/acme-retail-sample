package com.acme.serializer.appdirect;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
    private String type;
    private Marketplace marketplace;
    private Payload payload;
    private Creator creator;

    private Boolean success;
    private String message;
    private String accountIdentifier; // for 'create' events
}
