package com.crud.practise.utility;

import lombok.Data;

@Data
public class EmailWithAttachment {
	
	private String toEmail;
	private String subject;
	private String body;
	private String attachmentPath;
}
