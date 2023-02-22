package com.crud.practise.model;

import java.sql.Date;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Products {
	
	private int productId;
	
	@NotEmpty(message = "ProductName feild should not be Empty ")
	private String productName;
	
	@Min(1)
	@Max(200)
	@Digits(fraction = 0, integer = 3)
	private int qty;
	
	@DecimalMax(value = "10000.00")
	@DecimalMin( value = "1.0", message = "Please enter a valid amount")
	private double itemPrice;
	

//	@NotNull(message = "CategoryId feild should not be null ")
	private int categoryId;
	

//	@NotNull(message = "Qty feild should not be null ")
	private int warehouseId;
	
	private Date createdDate; 
	private Date updateDate; 

}
