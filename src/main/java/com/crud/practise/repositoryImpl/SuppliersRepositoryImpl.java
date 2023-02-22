package com.crud.practise.repositoryImpl;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crud.practise.model.Suppliers;
import com.crud.practise.repository.SuppliersRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class SuppliersRepositoryImpl implements SuppliersRepository{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Object[]> getAllSupplierDetails() {
		Query query = entityManager.createNativeQuery("SELECT * FROM SUPPLIERS");
		List<Object[]> supplierList = null;
		try {
			supplierList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supplierList;
	}

	@Override
	public Object getSupplierDetailById(int supplierId) {
		Query query = entityManager.createNativeQuery("SELECT * FROM  SUPPLIERS WHERE SUPPLIERID = ? ");
		query.setParameter(1, supplierId );
		Object supplierDetail = null;
		try {
			supplierDetail =  query.getSingleResult();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		return supplierDetail;
	}

	@Override
	@Transactional
	public Suppliers insertSupplierDetail(Suppliers suppliers) {
		Query query = entityManager.createNativeQuery("INSERT INTO SUPPLIERS (SUPPLIERNAME, CONTACTNO, COUNTRY, CREATED_DATE) VALUES (?,?,?,?)");
		query.setParameter(1, suppliers.getContactNo());
		query.setParameter(2, suppliers.getContactNo());
		query.setParameter(3, suppliers.getCountry());
		Date date = new Date();
		query.setParameter(4, date);
		int recordInserted = 0;
		try {
			recordInserted = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (recordInserted > 0) {
		return suppliers;
		}
		else return null;
	}

	@Override
	@Transactional
	public Object updateSupplierById(int supplierId, Suppliers suppliers) {
		Query query = entityManager.createNativeQuery("UPDATE SUPPLIERS SET SUPPLIERNAME=?, CONTACTNO=?, COUNTRY=?, UPDATED_DATE=? WHERE SUPPLIERID = ? ");
		query.setParameter(1, suppliers.getSupplierName());
		query.setParameter(2, suppliers.getContactNo());
		query.setParameter(3, suppliers.getCountry());
		Date date = new Date();
		query.setParameter(4, date);
		query.setParameter(5, supplierId);
		int recordUpdated = 0;
		try {
			recordUpdated =  query.executeUpdate();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		if (recordUpdated != 0) {
			return suppliers;
		} else 
			return null;
	}

	@Override
	@Transactional
	public Suppliers deleteSupplierDetailById(int supplierId) throws Exception {
		Query query = entityManager.createNativeQuery("DELETE FROM SUPPLIERS WHERE SUPPLIERID = ? ");
		query.setParameter(1, supplierId);
		int deleteRecord = 0; 
		try {
			deleteRecord = query.executeUpdate();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		if (deleteRecord > 0) {
			return null;
		}
		else
			throw new Exception("SupplierOrderDetails not found the given ID");
	}

}
