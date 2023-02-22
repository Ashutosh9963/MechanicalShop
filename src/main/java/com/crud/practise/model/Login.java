package com.crud.practise.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
	
	@NotEmpty(message = "Please enter a UserName")
	private String  username;
	
	@NotEmpty(message = "Please enter a password")
	private String  password;

}
