package com.crud.practise.model;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDetails {
	
	private int categoryId;
	
	@NotEmpty(message = "CategoryName feild should not be Empty ")
	private String categoryName;
	

	@NotEmpty(message = "Model feild should not be Empty ")
	private String model;
	
	@NotEmpty(message = "MfgYear feild should not be Empty ")
	private String mfgYear;
	
	private Date createdDate; 
	private Date updateDate; 
	
	private MultipartFile certificate; 

}
