 package com.crud.practise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.practise.model.CategoryDetails;
import com.crud.practise.model.CourseContent;
import com.crud.practise.model.FinalResponse;
import com.crud.practise.serviceImpl.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
//@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	@GetMapping("/getAllCategoryDetails")
	public FinalResponse getAllCategoryDetails() {
		return categoryServiceImpl.getAllCategoryDetails();
	}
	
	@GetMapping("/getCategoryDetailsById/{id}")
	public FinalResponse getCategoryDetailById(@PathVariable int id) {
		return categoryServiceImpl.getCategoryDetailsById(id);
	}
	
	@PutMapping("/updateCategoryDetailById/{id}")
	public FinalResponse updateCategoryDetail(@PathVariable int id, @RequestBody @Valid CategoryDetails categoryDetails) {
		return categoryServiceImpl.updateDetailsById(id, categoryDetails);
	}
	
	@PostMapping("/saveCategoryDetail")
	public FinalResponse saveCategoryDetails(@RequestBody @Valid CategoryDetails categoryDetails) {
		return categoryServiceImpl.insertCategoryDetails(categoryDetails);
	}
	
	@DeleteMapping("/deleteCategoryById/{id}")
	public FinalResponse deleteCustomerById(@PathVariable  int id ) {
		return categoryServiceImpl.deleteDetailsById(id);
	}
	
	// This is updating the certificate 
	@PutMapping(value = "/fileOfCertificate", consumes = {
			org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE })
	public FinalResponse fileOfCertificate( CategoryDetails categoryDetails) {
		return categoryServiceImpl.uploadCertificate(categoryDetails.getCategoryId(), categoryDetails.getCertificate());
	}
	
	@PostMapping("/saveCourseContent")
	public FinalResponse saveCourseContent(@RequestBody CourseContent courseContent) {
		return categoryServiceImpl.saveCourseContent(courseContent, courseContent.getCategoryDocument());
	}
	
	@GetMapping("/getCourseDetailsById/{id}")
	public FinalResponse getCourseDetailsById(@PathVariable int id) {
		return categoryServiceImpl.getCourseById(id);
	}
	
	
	
	
	
}
