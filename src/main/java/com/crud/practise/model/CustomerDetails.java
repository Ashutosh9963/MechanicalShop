 package com.crud.practise.model;

import java.sql.Date; 

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString; 

@Data
@ToString
public class CustomerDetails {
	
	private int customerid;
	
	@NotEmpty(message = "Customer name cannot be empty")
	private String customername;
	
	@NotEmpty
	@Pattern(regexp="(^$|[0-9]{10})",  message = "Invalid mobile number entered, please enter 10 Dig Mobile No")
	private String mobile;
	
	@NotEmpty(message = "Address cannot be empty")
	private String address;
	
	private Date createdDate;
	private Date updateDate;
}
