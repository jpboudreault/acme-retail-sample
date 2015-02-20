package com.acme.controller;

import com.acme.serializer.appdirect.Company;
import com.acme.serializer.appdirect.Event;
import com.acme.serializer.appdirect.Result;
import com.acme.serializer.appdirect.User;
import com.acme.service.EventExtractorService;
import com.acme.service.SynchronizationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller specifically for AppDirect
 */
//secure with oauth
@Controller
@RequestMapping("/api/app-direct")
@Transactional
public class AppDirectController {
    private final Log LOG = LogFactory.getLog(AppDirectController.class);

    @Autowired
    OAuthRestTemplate appDirectRestTemplate;

    @Autowired
    EventExtractorService eventExtractorService;

    @Autowired
    SynchronizationService synchronizationService;

    @RequestMapping(value = "/subscription-order", params = "url", produces = "application/xml")
    public Result subscriptionOrder(@RequestParam final String url) {
        LOG.info("subscription order event at url " + url);

        Event event = appDirectRestTemplate.getForObject(url, Event.class);

        Company company = eventExtractorService.extractCompany(event);
        String editionCode = eventExtractorService.extractCompanyEditionCode(event);
        synchronizationService.createCompany(company, editionCode);

        String companyId = eventExtractorService.extractCompanyIdentifier(event);
        User user = eventExtractorService.extractUser(event);
        
        synchronizationService.createUser(user, companyId);

		return new Result("ok");
    }

    @RequestMapping(value = "/subscription-change", params = "url", produces = "application/xml")
    public Result subscriptionChange(@RequestParam final String url) {
        LOG.info("subscription change event at url " + url);

        Event event = appDirectRestTemplate.getForObject(url, Event.class);

        String editionCode = eventExtractorService.extractCompanyEditionCode(event);
        String companyId = eventExtractorService.extractCompanyIdentifier(event);
        synchronizationService.updateSubscription(editionCode, companyId);

        return new Result("ok");
    }
}