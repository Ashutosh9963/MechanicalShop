package com.crud.practise.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crud.practise.model.CategoryDetails;
import com.crud.practise.model.FinalResponse;
import com.crud.practise.repository.CategoryRepository;
import com.crud.practise.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Override
	public FinalResponse getAllCategoryDetails() {
		logger.info("Fetching all the Category Details");
		FinalResponse finalResponse = new FinalResponse();
		List<Object[]> categoryDetails = repository.getAllCategoryDetails();
		List<Object> categoryList = new ArrayList<>();
		if (categoryDetails != null) {
			for (int i = 0; i < categoryDetails.size(); i++) {
				Object[] category = categoryDetails.get(i);
				CategoryDetails categoryNew = new CategoryDetails();
				Integer id = Integer.parseInt(String.valueOf(category[0]));
				categoryNew.setCategoryId(id);
				categoryNew.setCategoryName(String.valueOf(category[1]));
				categoryNew.setModel(String.valueOf(category[2]));
				categoryNew.setMfgYear(String.valueOf(category[3]));
				Date createdDate = (Date) category[4];
				Date updatedDate = (Date) category[5];
				categoryNew.setCreatedDate(createdDate);
				categoryNew.setUpdateDate(updatedDate);
				categoryList.add(categoryNew);
			}
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Category list fetched successfully ");
			finalResponse.setData(categoryList);
			return finalResponse;
		}
		finalResponse.setStatus(false);
		finalResponse.setStatusCode("501");
		finalResponse.setErrorMessage("Record not available to display");
		return finalResponse;
	}

	@Override
	public FinalResponse getCategoryDetailsById(int categoryId) {
		logger.info("Fetching Category Details by Id: " + categoryId);
		FinalResponse finalResponse = new FinalResponse();
		try {
			List<Object[]> categoryDetails = repository.getCategoryDetailById(categoryId);
			LinkedHashMap<String, String> category = new LinkedHashMap<>();
			for (Object[] obj : categoryDetails) {
				category.put("categoryId", String.valueOf(obj[0]));
				category.put("categoryName", String.valueOf(obj[1]));
				category.put("categoryModel", String.valueOf(obj[2]));
				category.put("categoryMfgYear", String.valueOf(obj[3]));
				category.put("createdDate", String.valueOf(obj[4]));
				category.put("updatedDate", String.valueOf(obj[5]));

				finalResponse.setStatus(true);
				finalResponse.setStatusCode("200");
				finalResponse.setMessage("Records Available");
				finalResponse.setData(category);
			}
		} catch (InputMismatchException e) {
			logger.error("Cannot fetcht the Category Details by Id " + categoryId + e.getMessage());
		}

		finalResponse.setStatus(false);
		finalResponse.setStatusCode("501");
		finalResponse.setMessage("Records not Available");
		return finalResponse;

	}

	@Override
	public FinalResponse insertCategoryDetails(CategoryDetails categoryDetails) {
		FinalResponse finalResponse = new FinalResponse();
		logger.info("Inserting one Recored::input::categoryDetails: " + categoryDetails);
		Object categoryDetail = repository.saveDetails(categoryDetails);
		if (categoryDetail != null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("201");
			finalResponse.setMessage("Record is inserted");
			finalResponse.setData(categoryDetail);
			return finalResponse;
		} else {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not inserted");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse updateDetailsById(int categoryId, CategoryDetails categoryDetails) {
		logger.info("Updating Category Details by Id: " + categoryId);
		FinalResponse finalResponse = new FinalResponse();
		Object updatedDetail = null;
		try {
			updatedDetail = repository.updateCategoryDetails(categoryId, categoryDetails);
		} catch (InputMismatchException e) {
			logger.error("Cannot Updated the CategoryDetails for Id:" + categoryId);
		}
		if (updatedDetail != null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Record Updated Successfully");
			finalResponse.setData(updatedDetail);
			return finalResponse;
		} else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setMessage("Record is not Updated");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse deleteDetailsById(int categoryId) {
		logger.info("deleteDetailsById : input" + categoryId);
		FinalResponse finalResponse = new FinalResponse();
		int deleteRecord = 0;
		try {
			deleteRecord = repository.deleteById(categoryId);
		} catch (InputMismatchException e) {
			logger.error("Cannot deleted the CategoryDetail by ID" + categoryId + e.getMessage());
		}
		if (deleteRecord == 1) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("204");
			finalResponse.setMessage("Record is deleted for Id" + categoryId);
			return finalResponse;
		} else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not deleted for Id" + categoryId);
			return finalResponse;
		}
	}

	@Override
	public FinalResponse uploadCertificate(int id, MultipartFile certificate) {
		logger.info("deleteDetailsById : input" + id);
		FinalResponse finalResponse = new FinalResponse();
		int result = 0;
		try {
			result = repository.uploadCertificate(id, certificate);
		} catch (Exception e) {
			logger.error("uploadCertificate : Exception" + e.getMessage());
		}
		if (result == 1) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("204");
			finalResponse.setMessage("File uploaded Successfully");
			return finalResponse;
		} else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("File upload is unsuccessfull");
			return finalResponse;
		}
		
	}
}
