package com.crud.practise.service;


import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.crud.practise.model.CategoryDetails;
import com.crud.practise.model.CourseContent;
import com.crud.practise.model.FinalResponse;

public interface CategoryService {
	
	public FinalResponse getAllCategoryDetails();
	
	public FinalResponse getCategoryDetailsById(int categoryId);
	
	public FinalResponse insertCategoryDetails(CategoryDetails categoryDetails);
	
	public FinalResponse updateDetailsById(int categoryId, CategoryDetails categoryDetails);
	
	public FinalResponse deleteDetailsById(int categoryId) throws Exception;
	
	public FinalResponse uploadCertificate(int id, MultipartFile certificate);
	
	public FinalResponse saveCourseContent(CourseContent courseContent,  JSONObject categoryDocument);
	
	public FinalResponse getCourseById(int categoryId);
	
}
