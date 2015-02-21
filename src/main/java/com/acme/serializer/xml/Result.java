package com.acme.serializer.xml;

import lombok.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * From by http://info.appdirect.com/developers/docs/api_integration/endpoint_urls/event_notification_urls/
 */
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "result")
public class Result {
    private Boolean success = Boolean.TRUE;
    private String message = "ok";
    private Long accountIdentifier;
    
    public Result(final Exception e) {
        success = Boolean.FALSE;
        message = e.getMessage();
    }
}
