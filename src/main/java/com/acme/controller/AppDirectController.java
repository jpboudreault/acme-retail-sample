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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * Controller specifically for AppDirect notifications
 */
//secure with oauth
@Controller
@RequestMapping("api/app-direct")
@Transactional
public class AppDirectController {
    private final Log LOG = LogFactory.getLog(AppDirectController.class);

    @Autowired
    OAuthRestTemplate appDirectRestTemplate;

    @Autowired
    EventExtractorService eventExtractorService;

    @Autowired
    SynchronizationService synchronizationService;

    @RequestMapping(value = "subscription-order", params = "url", produces = "application/xml")
    @ResponseBody
    public Result subscriptionOrder(@RequestParam final String url) {
        LOG.info("subscription order event at url " + url);

        final Event event = appDirectRestTemplate.getForObject(url, Event.class);

        Company company = eventExtractorService.extractCompany(event);
        String editionCode = eventExtractorService.extractCompanyEditionCode(event);
        
        long companyId = synchronizationService.createCompany(company, editionCode).getId();

        User user = eventExtractorService.extractUser(event);

        synchronizationService.createUser(user, companyId);

        // prepare the result, this is a special case we need to provide the account id
		Result result = new Result();
        result.setAccountIdentifier(companyId);
        
        return result;
    }

    @RequestMapping(value = "subscription-change", params = "url", produces = "application/xml")
    @ResponseBody
    public Result subscriptionChange(@RequestParam final String url) {
        LOG.info("subscription change event at url " + url);

        final Event event = appDirectRestTemplate.getForObject(url, Event.class);

        String editionCode = eventExtractorService.extractCompanyEditionCode(event);
        long companyId = eventExtractorService.extractCompanyIdentifier(event);
        synchronizationService.updateSubscription(companyId, editionCode);

        return new Result();
    }

    @RequestMapping(value = "subscription-cancel", params = "url", produces = "application/xml")
    @ResponseBody
    public Result subscriptionCancel(@RequestParam final String url) {
        LOG.info("subscription cancel event at url " + url);

        final Event event = appDirectRestTemplate.getForObject(url, Event.class);

        long companyId = eventExtractorService.extractCompanyIdentifier(event);
        synchronizationService.cancelSubscription(companyId);

        return new Result();
    }
    
    @RequestMapping(value = "subscription-notice", params = "url", produces = "application/xml")
    @ResponseBody
    public Result subscriptionNotice(@RequestParam final String url) {
        LOG.info("subscription change event at url " + url);

        // TODO do me!
        
        return new Result();
    }
}