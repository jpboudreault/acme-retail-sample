package com.acme.service;

import com.acme.serializer.xml.Company;
import com.acme.serializer.xml.Creator;
import com.acme.serializer.xml.Event;
import com.acme.serializer.xml.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Extracts objects from AppDirect's Events
 */
@Service
@Transactional(readOnly = true)
public class EventExtractionService {
    public Company extractCompany(Event event) {
        return event.getPayload().getCompany();
    }

    public String extractCompanyEditionCode(Event event) {
        return event.getPayload().getOrder().getEditionCode();
    }

    public Long extractCompanyIdentifier(Event event) {
        return event.getPayload().getAccount().getAccountIdentifier();
    }

    public Creator extractCreator(Event event) {
        return event.getCreator();
    }

    public String extractNotice(Event event) {
        return event.getPayload().getNotice().getType();
    }

    public User extractUser(Event event) {
        return event.getPayload().getUser();
    }
}
