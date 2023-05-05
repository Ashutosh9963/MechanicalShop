package com.crud.practise.repository;

import java.util.List;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.crud.practise.model.CategoryDetails;
import com.crud.practise.model.CourseContent;

public interface CategoryRepository {
	
	public List<Object[]> getAllCategoryDetails();
	
	public List<Object[]> getCategoryDetailById(int categoryId);
	
	public CategoryDetails saveDetails(CategoryDetails categoryDetails);
	
	public CategoryDetails updateCategoryDetails(int categoryId, CategoryDetails categoryDetails);
	
	public int uploadCertificate(int categoryId, MultipartFile certificate);
	
	public int deleteById(int categoryId);
	
	public CourseContent saveContent(CourseContent courseContent, JSONObject categoryDocument);
	
	public  List<Object[]> getCourseDetailsById(int categoryId);
	
	

}
