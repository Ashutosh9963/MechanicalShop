package com.crud.practise.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailSender {

	@NotEmpty(message = "Please enter Email address")
	private String toEmail;
	@NotEmpty(message = "Please enter Email Subject")
	private String subject;
	@NotEmpty(message = "Please enter Email Body")
	private String body;

}
