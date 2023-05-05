package com.crud.practise.model;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseContent {
	
	private int categoryId;
	
	private JSONObject categoryDocument;
	
	private String contentVideo;

}
