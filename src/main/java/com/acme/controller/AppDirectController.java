package com.acme.controller;

import com.acme.serializer.AppDirectResponse;
import com.acme.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller specifically for AppDirect
 */
@Controller
@RequestMapping("/api/app-direct")
public class AppDirectController
{
	@Autowired
    SubscriptionService subscriptionService;
	
    /** 
     * When the application is bought
     */
	@RequestMapping(value = "/subscription-order", params = "eventUrl", produces="application/xml")
	public AppDirectResponse subscriptionOrder(@RequestParam final String eventUrl)
	{
		return subscriptionService.subscriptionOrder(eventUrl);
	}
}
