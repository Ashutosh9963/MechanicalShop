package com.crud.practise.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Invoice {
	
	private int invoiceId;
	
	@Min(1)
	@Max(200)
	@Digits(fraction = 0, integer = 3)
	private int qty;
	
	@DecimalMax(value = "10000.00")
	@DecimalMin( value = "1.0", message = "Please enter a valid amount")
	private double price;
	
	private int orderId;
	private int paymentId;
	private int createdDate;
	private int updatedDate;
	
	

}
