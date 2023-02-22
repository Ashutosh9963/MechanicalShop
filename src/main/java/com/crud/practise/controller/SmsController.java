package com.crud.practise.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.crud.practise.utility.SendSMS;
/*
 * The HTTP requests are handled by a controller. These components are identified by the @RestController annotation. 
 * This indicates that it is a controller layer of the application. This code uses Spring @RestController annotation 
 * which marks the class as a controller where every method returns the domain object instead of a view. 
 * It is shorthand for including both @Controller and @ResponseBody.
 * */

@RestController

public class SmsController {
	
	@Autowired
	private SendSMS sendSMS;
	
	@GetMapping("/sendSMS")
	public ResponseEntity<String> sendSMS(@RequestBody String textSMS) {
		return sendSMS.sendSMS(textSMS);
	}
	
	@GetMapping("/sendOTPSMS")
	public String sendOTPSMS() {
		return sendSMS.sendOTP();
	}
	
	@GetMapping("/sendVoiceCall")
	public ResponseEntity<String> sendVoiceCall() {
		return sendSMS.sendVoiceCall();
	}

}
