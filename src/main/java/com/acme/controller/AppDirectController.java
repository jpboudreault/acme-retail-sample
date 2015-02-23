package com.acme.controller;

import com.acme.serializer.xml.Company;
import com.acme.serializer.xml.Event;
import com.acme.serializer.xml.Result;
import com.acme.serializer.xml.User;
import com.acme.service.EventExtractionService;
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

/**
 * Controller specifically for AppDirect notifications
 */
@Controller
@RequestMapping("api/app-direct")
@Transactional
public class AppDirectController {
    private Log LOG = LogFactory.getLog(AppDirectController.class);

    @Autowired
    OAuthRestTemplate appDirectRestTemplate;

    @Autowired
    EventExtractionService eventExtractionService;

    @Autowired
    SynchronizationService synchronizationService;

    @RequestMapping(value = "subscription-order", params = "url", produces = "application/xml")
    @ResponseBody
    public Result subscriptionOrder(@RequestParam String url) {
        LOG.info(String.format("subscription order received at %s", url));

        try {
            Event event = appDirectRestTemplate.getForObject(url, Event.class);

            Company company = eventExtractionService.extractCompany(event);
            String editionCode = eventExtractionService.extractCompanyEditionCode(event);

            Long companyId = synchronizationService.createCompany(company, editionCode).getId();

            User user = eventExtractionService.extractCreator(event);

            synchronizationService.createUser(user, companyId);

            // prepare the result, this is a special case we need to provide the account id
            Result result = new Result();
            result.setAccountIdentifier(companyId);
            return result;
        } catch (Exception e) {
            LOG.error("subscription order error", e);
            return new Result(e);
        }
    }

    @RequestMapping(value = "subscription-change", params = "url", produces = "application/xml")
    @ResponseBody
    public Result subscriptionChange(@RequestParam String url) {
        LOG.info(String.format("subscription change received at %s", url));

        try {
            Event event = appDirectRestTemplate.getForObject(url, Event.class);

            String editionCode = eventExtractionService.extractCompanyEditionCode(event);
            Long companyId = eventExtractionService.extractCompanyIdentifier(event);

            synchronizationService.updateSubscription(companyId, editionCode);

            return new Result();
        } catch (Exception e) {
            LOG.error("subscription change error", e);
            return new Result(e);
        }
    }

    @RequestMapping(value = "subscription-cancel", params = "url", produces = "application/xml")
    @ResponseBody
    public Result subscriptionCancel(@RequestParam String url) {
        LOG.info(String.format("subscription cancel at %s", url));

        try {
            Event event = appDirectRestTemplate.getForObject(url, Event.class);

            Long companyId = eventExtractionService.extractCompanyIdentifier(event);

            synchronizationService.cancelSubscription(companyId);

            return new Result();
        } catch (Exception e) {
            LOG.error("subscription cancel error", e);
            return new Result(e);
        }
    }

    @RequestMapping(value = "subscription-notice", params = "url", produces = "application/xml")
    @ResponseBody
    public Result subscriptionNotice(@RequestParam String url) {
        LOG.info("subscription notice event at url " + url);

        try {
            Event event = appDirectRestTemplate.getForObject(url, Event.class);

            Long companyId = eventExtractionService.extractCompanyIdentifier(event);
            String notice = eventExtractionService.extractNotice(event);

            synchronizationService.applyNotice(companyId, notice);

            return new Result();
        } catch (Exception e) {
            LOG.error("subscription notice error", e);
            return new Result(e);
        }
    }

    @RequestMapping(value = "user-assignment", params = "url", produces = "application/xml")
    @ResponseBody
    public Result userAssignment(@RequestParam String url) {
        LOG.info("user assignment event at url " + url);

        try {
            Event event = appDirectRestTemplate.getForObject(url, Event.class);

            Long companyId = eventExtractionService.extractCompanyIdentifier(event);
            User user = eventExtractionService.extractUser(event);

            synchronizationService.createUser(user, companyId);

            return new Result();
        } catch (Exception e) {
            LOG.error("user assignment error", e);
            return new Result(e);
        }
    }

    @RequestMapping(value = "user-unassignment", params = "url", produces = "application/xml")
    @ResponseBody
    public Result userUnassignment(@RequestParam String url) {
        LOG.info("user unassignment event at url " + url);

        try {
            Event event = appDirectRestTemplate.getForObject(url, Event.class);

            Long companyId = eventExtractionService.extractCompanyIdentifier(event);
            String openId = eventExtractionService.extractUser(event).getOpenId();

            synchronizationService.removeUser(openId, companyId);

            return new Result();
        } catch (Exception e) {
            LOG.error("user unassignment error", e);
            return new Result(e);
        }
    }
}