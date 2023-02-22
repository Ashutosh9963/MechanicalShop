package com.crud.practise.utility;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class SendSMS {
	
	private final String TWILIO_ACCOUNT_SID = "AC14cee21341f7ca9ae9d0b7bbd8cabe73";
	private final String TWILIO_AUTH_TOKEN = "253cbddf640d81ced96603324220948c";
	
	public ResponseEntity<String> sendSMS(String textSMS) {
		// to initialize the twilio account with SID and TOKEN
		Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
		// To send the SMS, we need to provide a from-number and to-number to the Message.create() method.
		// syntax:  Message.creator(new PhoneNumber("to-number"), new PhoneNumber("from-number"),"Message from Spring Boot Application").create();
		Message.creator(new PhoneNumber("+919963190891"), new PhoneNumber("+12183221445"), textSMS).create();
		return new ResponseEntity<String>("Message sent Successfully", HttpStatus.OK);
	}
	
	public String generateOTP() {
		int randomPin =  (int) ((Math.random()*900000)+100000);
		String otpNum = String.valueOf(randomPin);
		return "Your OTP Number is " + otpNum;
	}
	
	public String sendOTP() {
		// to initialize the twilio account with SID and TOKEN
		Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
		// To send the SMS, we need to provide a from-number and to-number to the Message.create() method.
		// syntax:  Message.creator(new PhoneNumber("to-number"), new PhoneNumber("from-number"),"Message from Spring Boot Application").create();
		Message.creator(new PhoneNumber("+919963190891"), new PhoneNumber("+12183221445"), generateOTP()).create();
		return "OTP sent Successfully";
	}
	
	public ResponseEntity<String> sendVoiceCall() {

		// to initialize the twilio account with SID and TOKEN
		Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
		// To send the SMS, we need to provide a from-number and to-number to the Message.create() method.
		// Syntax: Call.creator(new PhoneNumber("<to-number>"), new PhoneNumber("<from-number>"),new URI("http://demo.twilio.com/docs/voice.xml")).create();
		try {
			Call.creator(new PhoneNumber("+919963190891"), new PhoneNumber("+12183221445"),
					   new URI("http://demo.twilio.com/docs/voice.xml")).create();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Message sent Successfully", HttpStatus.OK);
	}
	
	
	
	

}
