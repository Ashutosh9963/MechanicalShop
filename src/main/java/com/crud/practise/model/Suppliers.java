package com.crud.practise.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Suppliers {
	
	private int supplierId;
	
	@NotEmpty(message = "Please enter a SupplierName")
	private String supplierName;
	
	@NotEmpty
	@Pattern(regexp="(^$|[0-9]{10})",  message = "Invalid mobile number entered, please enter 10 Dig Mobile No")
	private String contactNo;
	
	@NotEmpty(message = "Please enter a CountryName")
	private String country;
	
	private int createdDate;
	private int updatedDate;

}
