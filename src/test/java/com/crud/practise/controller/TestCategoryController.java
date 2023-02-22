package com.crud.practise.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crud.practise.model.CategoryDetails;
import com.crud.practise.model.FinalResponse;
import com.crud.practise.serviceImpl.CategoryServiceImpl;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for Category Controller")
class TestCategoryController {

	@InjectMocks
	private CategoryController categoryController;
	
	@Mock
	private CategoryServiceImpl categoryService;
	
	@Test
	@DisplayName("Test getAllCategoryDetails : Expected OK")
	void testGetAllCategoryDetails() {
		Object[] category1 =  {
				"categoryName" , "LUBRICANTS",
				"model" , "LU789",
				"mfgYear" , "2020",
			    "createdDate" , "2016-08-20",
			    "updateDate" , "2016-08-21"
		};
		Object[] category2 =  {
				"categoryName" , "LUBRICANTS",
				"model" , "LU789",
				"mfgYear" , "2020",
			    "createdDate" , "2016-08-20",
			    "updateDate" , "2016-08-21"
		};
		List<Object[]> list = new ArrayList<>();
		list.add(category1);
		list.add(category2);
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("200");
		expected.setMessage("Records present");
		expected.setData(list);
		when(categoryService.getAllCategoryDetails()).thenReturn(expected);
		FinalResponse actual = categoryController.getAllCategoryDetails();
		assertEquals(expected, actual);
	}
	
	@Test
	@DisplayName("Test getCategoryDetailsById : Expected OK")
	void testGetCategoryDetailsById() {
		Object[] category1 =  {
				"categoryId", 1,
				"categoryName" , "LUBRICANTS",
				"model" , "LU789",
				"mfgYear" , "2020",
			    "createdDate" , "2016-08-20",
			    "updateDate" , "2016-08-21"
		};
		FinalResponse expected  = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("200");
		expected.setMessage("Records Available");
		expected.setData(category1);
		when(categoryService.getCategoryDetailsById(1)).thenReturn(expected);
		FinalResponse actual = categoryController.getCategoryDetailById(1);
		assertThat(expected).isEqualTo(actual);
	}
	
	@Test
	@DisplayName("Test updateCategoryDetailById : Expected OK")
	void testUpdateCategoryDetailById() {
		CategoryDetails category1 = new CategoryDetails();
		category1.setCategoryId(1);
		category1.setCategoryName("WELDING");
		category1.setModel("WEL858");
		category1.setMfgYear("2021");
		category1.setCreatedDate(java.sql.Date.valueOf("2016-02-22"));
		category1.setUpdateDate(java.sql.Date.valueOf("2016-02-23"));
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("200");
		expected.setMessage("Record Updated Successfully");
		expected.setData(category1);
		when(categoryService.updateDetailsById(1, category1)).thenReturn(expected);
		FinalResponse actual = categoryController.updateCategoryDetail(1, category1);
		assertThat(expected).isEqualTo(actual);
	}
	
	@Test
	@DisplayName("Test saveCategoryDetail : Expected OK")
	void testSaveCategoryDetail() {
		CategoryDetails category1 = new CategoryDetails();
		category1.setCategoryId(1);
		category1.setCategoryName("WELDING");
		category1.setModel("WEL858");
		category1.setMfgYear("2021");
		category1.setCreatedDate(java.sql.Date.valueOf("2016-02-22"));
		category1.setUpdateDate(java.sql.Date.valueOf("2016-02-23"));
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("201");
		expected.setMessage("Record is inserted");
		expected.setData(category1);
		when(categoryService.insertCategoryDetails(category1)).thenReturn(expected);
		FinalResponse actual = categoryController.saveCategoryDetails(category1);
		assertThat(expected).isEqualTo(actual);
	}
	
	@Test
	@DisplayName("Test saveCategoryDetail : Expected OK")
	void testDeleteCategoryById() {
		CategoryDetails category1 = new CategoryDetails();
		category1.setCategoryId(1);
		category1.setCategoryName("WELDING");
		category1.setModel("WEL858");
		category1.setMfgYear("2021");
		category1.setCreatedDate(java.sql.Date.valueOf("2016-02-22"));
		category1.setUpdateDate(java.sql.Date.valueOf("2016-02-23"));
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("204");
		expected.setMessage("Record is deleted");
		when(categoryService.deleteDetailsById(1)).thenReturn(expected);
		FinalResponse actual = categoryController.deleteCustomerById(1);
		assertThat(actual).isEqualTo(expected);
	}
}
