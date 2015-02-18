package com.acme.controller;

import com.acme.serializer.appdirect.Result;
import com.acme.service.SubscriptionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller specifically for AppDirect
 */
@Controller
@RequestMapping("/api/app-direct")
public class AppDirectController {
    private final Log LOG = LogFactory.getLog(AppDirectController.class);

    // FIXME rename to appdirectConnectionService
	@Autowired
    SubscriptionService subscriptionService;

    // FIXME rename eventURL to url if appdirect allows
	@RequestMapping(value = "/subscription-order", params = "url", produces="application/xml")
	public Result subscriptionOrder(@RequestParam final String url)
	{
        LOG.info("=================== subscription order " + url);

        // FIXME response format is a concern of this controller not the service
		return subscriptionService.subscriptionOrder(url);
	}
}
