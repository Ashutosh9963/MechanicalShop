package com.crud.practise.serviceImpl;

import java.io.IOException; 

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.crud.practise.utility.NotificationRequest;
import com.crud.practise.utility.SubscriptionRequest;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationService {
	
    @Value("${app.firebase-config}")
    private String firebaseConfig;
    
    private FirebaseApp firebaseApp; 
    
    @PostConstruct
    private void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfig).getInputStream())).build();

            if (FirebaseApp.getApps().isEmpty()) {
                this.firebaseApp = FirebaseApp.initializeApp(options);
            } else {
                this.firebaseApp = FirebaseApp.getInstance();
            }
        } catch (IOException e) {
            log.error("Create FirebaseApp Error", e);
        }
    }
    
    public void subscribeToTopic(SubscriptionRequest subscriptionRequest) {
        try {
            FirebaseMessaging.getInstance(firebaseApp).subscribeToTopic(subscriptionRequest.getTokens(),
            		subscriptionRequest.getTopicName());
        } catch (FirebaseMessagingException e) {
            log.error("Firebase subscribe to topic fail", e);
        }
    }

    public void unsubscribeFromTopic(SubscriptionRequest subscriptionRequest) {
        try {
            FirebaseMessaging.getInstance(firebaseApp).unsubscribeFromTopic(subscriptionRequest.getTokens(),
            		subscriptionRequest.getTopicName());
        } catch (FirebaseMessagingException e) {
            log.error("Firebase unsubscribe from topic fail", e);
        }
    }

    public String sendPnsToDevice(NotificationRequest notificationRequest) {
		Message message = Message.builder()
                .setToken(notificationRequest.getTarget())
                .setNotification(new Notification(notificationRequest.getTitle(), notificationRequest.getBody()))
                .putData("content", notificationRequest.getTitle())
                .putData("body", notificationRequest.getBody())
                .build();

        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Message sent successfully");
        } catch (FirebaseMessagingException e) {
            log.error("Fail to send firebase notification", e);
        }

        return response;
    }

    public String sendPnsToTopic(NotificationRequest notificationRequest) {
        Message message = Message.builder()
                .setTopic(notificationRequest.getTarget())
                .setNotification(new Notification(notificationRequest.getTitle(), notificationRequest.getBody()))
                .putData("content", notificationRequest.getTitle())
                .putData("body", notificationRequest.getBody())
                .build();

        String response = null;
        try {
        	response= FirebaseMessaging.getInstance().send(message);
        	System.out.println(response);
        } catch (FirebaseMessagingException e) {
            log.error("Fail to send firebase notification", e);
        }

        return response;
    }

}
