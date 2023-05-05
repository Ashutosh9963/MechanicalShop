package com.crud.practise.repositoryImpl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.crud.practise.model.CategoryDetails;
import com.crud.practise.model.CourseContent;
import com.crud.practise.repository.CategoryRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class CategoryRepositoryImpl  implements CategoryRepository{
	
	@Autowired
	private  EntityManager entityManager;
	
	@Override
	public List<Object[]> getAllCategoryDetails() {
		Query query = entityManager.createNativeQuery("SELECT CATEGORYID, CATEGORYNAME, MODEL, MFGYEAR, CREATED_DATE, UPDATED_DATE FROM CATEGORYDETAILS");
		List<Object[]> categoryList = null;
		try {
			categoryList = query.getResultList();
		} catch (Exception e) {
			e.getMessage();
		}
		return categoryList;
	}
	
	@Override
	public List<Object[]> getCategoryDetailById(int categoryId) {
		Query query = entityManager.createNativeQuery("SELECT CATEGORYID, CATEGORYNAME, MODEL, MFGYEAR, CREATED_DATE, UPDATED_DATE FROM CATEGORYDETAILS WHERE CATEGORYID = ? ");
		query.setParameter(1, categoryId );
		List<Object[]> categoryDetail = null;
		try {
			categoryDetail =  query.getResultList();
		} catch (InputMismatchException e) {
			e.getMessage();
		}
		return categoryDetail;
	}

	@Override
	@Transactional
	public CategoryDetails saveDetails(CategoryDetails categoryDetails) {
		Query query = entityManager.createNativeQuery("INSERT INTO CATEGORYDETAILS ( CATEGORYNAME, MODEL, MFGYEAR, CREATED_DATE)  VALUES (?,?,?,?)");
		query.setParameter(1, categoryDetails.getCategoryName());
		query.setParameter(2, categoryDetails.getModel());
		query.setParameter(3, categoryDetails.getMfgYear());
		Date date = new Date();
		query.setParameter(4, date);
		int recordInserted = 0;
		try {
			recordInserted = query.executeUpdate();
		} catch (IllegalStateException e) {
			e.getMessage();
		}
		if (recordInserted > 0) {
		return categoryDetails;
		}
		else return null;
	}

	@Override
	@Transactional
	public CategoryDetails updateCategoryDetails(int categoryId, CategoryDetails categoryDetails) {
		Query query = entityManager.createNativeQuery("UPDATE CATEGORYDETAILS SET CATEGORYNAME=?, MODEL=?, MFGYEAR=?, UPDATED_DATE=? WHERE CATEGORYID = ? ");
		query.setParameter(1, categoryDetails.getCategoryName());
		query.setParameter(2, categoryDetails.getModel());
		query.setParameter(3, categoryDetails.getMfgYear());
		Date date = new Date();
		query.setParameter(4, date);
		query.setParameter(5, categoryId);
		int recordUpdated = 0;
		try {
			recordUpdated =  query.executeUpdate();
		} catch (InputMismatchException e) {
			e.getMessage();
		}
		if (recordUpdated != 0) {
			return categoryDetails;
		} else 
			return null;
	}

	@Override
	@Transactional
	public int deleteById(int categoryId)  {
		Query query = entityManager.createNativeQuery("DELETE FROM CATEGORYDETAILS WHERE CATEGORYID = ? ");
		query.setParameter(1, categoryId);
		int deleteRecord = 0; 
		try {
			deleteRecord = query.executeUpdate();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		if (deleteRecord == 1) {
			deleteRecord = 1;
		}
		return deleteRecord;
	}

	@Override
	@Transactional
	public int uploadCertificate(int categoryId, MultipartFile certificate) {
		Query query = entityManager
				.createNativeQuery("UPDATE CATEGORYDETAILS SET CERTIFICATES = ? WHERE CATEGORYID = ? ");
		int result = 0;
		try {
			byte[] byteFile = certificate.getBytes();
			try {
				Blob blobFile = new SerialBlob(byteFile);
				query.setParameter(1, blobFile);
				query.setParameter(2, categoryId);
				result = query.executeUpdate();
			} catch (SerialException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@Transactional
	public CourseContent saveContent(CourseContent courseContent, JSONObject categoryDocument) {
		Query query = entityManager
				.createNativeQuery("INSERT INTO COURSECONTENT ( CATEGORYDOCUMENT, CONTENTVIDEO ) VALUES  (?, ?) ");
		String stringData = categoryDocument.toString();
		int result = 0 ;
		try {
			byte[] byteData = stringData.getBytes();
			Blob blobData = new SerialBlob(byteData);
//			JSONObject jsonObject = new JSONObject(blobData);
			System.out.println(blobData);
			query.setParameter(1, blobData);
			query.setParameter(2, courseContent.getContentVideo());
			result = query.executeUpdate();
		} 
		 catch (SQLException e) {
			e.printStackTrace();
		}
		if (result == 1) {
			return courseContent;
		} return null;
		
	}

	@Override
	public  List<Object[]> getCourseDetailsById(int categoryId) {
		Query query = entityManager.createNativeQuery("SELECT * FROM coursecontent WHERE CATEGORYID = ? ");
		query.setParameter(1, categoryId );
		 List<Object[]> categoryDetail = null;
		try {
			categoryDetail =  query.getResultList();
		} catch (InputMismatchException e) {
			e.getMessage();
		}
		return categoryDetail;
	}
	
	
}
