package com.crud.practise.utility;

import java.util.List;

import lombok.Data;

@Data
public class SubscriptionRequest {
	
	String topicName;
	List<String> tokens;

}
