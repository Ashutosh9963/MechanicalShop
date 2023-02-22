package com.crud.practise.repositoryImpl;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crud.practise.model.Invoice;
import com.crud.practise.repository.InvoiceRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class InvoiceRepositoryImpl implements InvoiceRepository{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Object[]> getAllInvoiceDetails() {
		Query query = entityManager.createNativeQuery("SELECT * FROM INVOICE");
		List<Object[]> invoiceList = null;
		try {
			invoiceList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invoiceList;
	}

	@Override
	public Object getInvoiceDetailById(int invoiceId) {
		Query query = entityManager.createNativeQuery("SELECT * FROM  INVOICE WHERE INVOICEID = ? ");
		query.setParameter(1, invoiceId );
		Object invoiceDetail = null;
		try {
			invoiceDetail =  query.getSingleResult();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		return invoiceDetail;
	}

	@Override
	@Transactional
	public Invoice createInvoiceDetails(Invoice invoice) {
		Query query = entityManager.createNativeQuery("INSERT INTO INVOICE (QTY, PRICE, ORDERID, PAYMENTID, CREATED_DATE) VALUES (?,?,?,?,?)");
		query.setParameter(1, invoice.getQty());
		query.setParameter(2, invoice.getPrice());
		query.setParameter(3, invoice.getOrderId());
		query.setParameter(4, invoice.getPaymentId());
		Date date = new Date();
		query.setParameter(5, date);
		int recordInserted = 0;
		try {
			recordInserted = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (recordInserted > 0) {
		return invoice;
		}
		else return null;
	}

	@Override
	@Transactional
	public Object updateSupplierOrderById(int invoiceId, Invoice invoice) {
		Query query = entityManager.createNativeQuery("UPDATE INVOICE SET QTY=?, PRICE=?, ORDERID=?, PAYMENTID=?, UPDATED_DATE=? WHERE INVOICEID = ? ");
		query.setParameter(1, invoice.getQty());
		query.setParameter(2, invoice.getPrice());
		query.setParameter(3, invoice.getOrderId());
		query.setParameter(4, invoice.getPaymentId());
		Date date = new Date();
		query.setParameter(5, date);
		query.setParameter(6, invoiceId);
		int recordUpdated = 0;
		try {
			recordUpdated =  query.executeUpdate();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		if (recordUpdated != 0) {
			return invoice;
		} else 
			return null;
	}

	@Override
	@Transactional
	public Invoice deleteInvoiceById(int invoiceId) throws Exception {
		Query query = entityManager.createNativeQuery("DELETE FROM INVOICE WHERE INVOICEID = ? ");
		query.setParameter(1, invoiceId);
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
			throw new Exception("Category not found the given ID");
	}

}
