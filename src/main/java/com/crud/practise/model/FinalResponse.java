package com.crud.practise.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FinalResponse {

	public boolean status;
	public String statusCode;
	public String message;
	public String errorMessage;
	public Object data;

}
