package com.crud.practise.model;

import java.sql.Date; 

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Warehouse {

	private int warehouseId;

	@NotEmpty(message = "Please enter a WarehouseName")
	private String warehouseName;

	@Min(1)
	@Max(500)
	@Digits(fraction = 0, integer = 3)
	private int qty;

	private int supplierId;

	private Date createdDate;
	private Date updatedDate;

}
