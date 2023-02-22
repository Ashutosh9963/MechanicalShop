package com.crud.practise.model;

import java.sql.Date;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SupplierOrders {
	
	private  int supplierOrderId;
	
	@Min(1)
	@Max(200)
	@Digits(fraction = 0, integer = 3)
	private  int orderedQty;
	
	private  int supplierId;
	private  int productId;
	
	private	Date createdDate;
	private Date updatedDate;
	

}
