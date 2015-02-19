package com.acme.controller;

import com.acme.serializer.appdirect.Event;
import com.acme.serializer.appdirect.Result;
import com.acme.service.SubscriptionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;

/**
* Controller specifically for AppDirect
*/
//secure with oauth
@Controller
@RequestMapping("/api/app-direct")
public class AppDirectController {
    @Value("${appdirect.oauth.consumer.key}")
    private String appDirectOAuthConsumerKey;

    @Value("${appdirect.oauth.consumer.secret}")
    private String appDirectOAuthConsumerSecret;

    private final Log LOG = LogFactory.getLog(AppDirectController.class);

    // FIXME rename to appdirectConnectionService
	@Autowired
    SubscriptionService subscriptionService;

	@RequestMapping(value = "/subscription-order", params = "url", produces="application/xml")
	public Result subscriptionOrder(@RequestParam final String url)
	{
        LOG.info("=================== subscription order " + url);

        // FIXME response format is a concern of this controller not the service

        fetchEventDetails(url);

        return null;
//		return subscriptionService.subscriptionOrder(url);
	}

    @RequestMapping(value = "/subscription-change", params = "url", produces="application/xml")
    public Result subscriptionChange(@RequestParam final String url)

    private Object fetchEventDetails(String url) {
        try {
            BaseProtectedResourceDetails resourceDetails = new BaseProtectedResourceDetails();
            resourceDetails.setConsumerKey(appDirectOAuthConsumerKey);
            resourceDetails.setSharedSecret(new SharedConsumerSecretImpl(appDirectOAuthConsumerSecret));

            OAuthRestTemplate restTemplate = new OAuthRestTemplate(resourceDetails);

            Event event = restTemplate.getForObject(url, Event.class);
            LOG.info("======= "+ event);
        } catch (Exception e) {

        }
        return null;
    }
}
