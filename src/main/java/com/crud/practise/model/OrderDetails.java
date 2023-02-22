package com.crud.practise.model;

import java.sql.Date;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderDetails {
	
	private int orderId;
	
	@Min(1)
	@Max(200)
	@Digits(fraction = 0, integer = 3)
	private int orderQty;
	
	@DecimalMax(value = "10000.00")
	@DecimalMin( value = "1.0", message = "Please enter a valid amount")
	private double price;
	
	private int productId;
	
	private int customerId;
	
	private int employeeId;
	
	private Date createdDate;
	private Date updateDate;
}
