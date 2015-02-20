package com.acme.controller;

import com.acme.serializer.appdirect.Event;
import com.acme.serializer.appdirect.Result;
import com.acme.service.SynchronizationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller specifically for AppDirect
 */
//secure with oauth
@Controller
@RequestMapping("/api/app-direct")
public class AppDirectController {
    private final Log LOG = LogFactory.getLog(AppDirectController.class);

    @Autowired
    OAuthRestTemplate appDirectRestTemplate;

    @Autowired
    SynchronizationService synchronizationService;

    @RequestMapping(value = "/subscription-order", params = "url", produces = "application/xml")
    public Result subscriptionOrder(@RequestParam final String url) {
        LOG.info("subscription order at url " + url);

        // FIXME response format is a concern of this controller not the service

        Event event = appDirectRestTemplate.getForObject(url, Event.class);

        LOG.info("subscription order at url " + event.toString());

        return null;
//		return subscriptionService.subscriptionOrder(url);
    }

    @RequestMapping(value = "/subscription-change", params = "url", produces = "application/xml")
    public Result subscriptionChange(@RequestParam final String url) {
        return null;
    }
}

