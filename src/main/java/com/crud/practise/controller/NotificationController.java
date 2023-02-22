package com.crud.practise.controller;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.practise.serviceImpl.NotificationService;
import com.crud.practise.utility.NotificationRequest;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
//	 @PostMapping("/subscribe")
//	    public void subscribeToTopic(@RequestBody SubscriptionRequest subscriptionRequest) {
//	        notificationService.subscribeToTopic(subscriptionRequest);
//	    }

//	    @PostMapping("/unsubscribe")
//	    public void unsubscribeFromTopic(SubscriptionRequest subscriptionRequest) {
//	        notificationService.unsubscribeFromTopic(subscriptionRequest);
//	    }

//	    @PostMapping("/token")
//	    public String sendPnsToDevice(@RequestBody NotificationRequest notificationRequest) {
//	        return notificationService.sendPnsToDevice(notificationRequest);
//	    }

	    @PostMapping("/topic")
	    public String sendPnsToTopic(@RequestBody NotificationRequest notificationRequest) {
	        return notificationService.sendPnsToTopic(notificationRequest);
	    }


}
