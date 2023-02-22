package com.crud.practise.model;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employees {
	
	private int  empId;
	
	@NotEmpty(message = "EmployeeName cannot be empty")
	private String  employeeName;
	
	@NotEmpty
	@Pattern(regexp="(^$|[0-9]{10})",  message = "Invalid mobile number entered, please enter 10 Dig Mobile No")
	private String mobile;
	
	@NotEmpty(message = "Designation cannot be empty")
	private String designation;
	
	@NotEmpty(message = "Please enter a UserName")
	private String  username;
	
	@NotEmpty(message = "Please enter a password")
	private String  password;
	
	private Date hireDate;
	
	private Date createdDate;
	
	private Date updatedDate;
	
	private MultipartFile profileImage;
	
}
