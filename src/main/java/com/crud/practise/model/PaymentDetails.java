package com.crud.practise.model;

import java.sql.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PaymentDetails {
	
	private int paymentId;
	
	@NotEmpty(message = "Please enter the Payment Type")
	private String paymentType;
	
	@NotEmpty(message = "Please enter the ShipMode")
	private String shipMode;
	
	@NotEmpty(message = "Please enter the Payment Status")
	private String status;
	
	private Date createdDate;
	private Date updatedDate;

}
