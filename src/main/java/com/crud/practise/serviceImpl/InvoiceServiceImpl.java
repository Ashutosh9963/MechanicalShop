package com.crud.practise.serviceImpl;

import java.util.InputMismatchException; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.Invoice;
import com.crud.practise.repository.InvoiceRepository;
import com.crud.practise.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private InvoiceRepository repository;
	
	// Creating the Logger Object
	Logger logger = LoggerFactory.getLogger(SupplierOrdersServiceImpl.class);

	@Override
	public FinalResponse getAllInvoiceDetails() {
		logger.info("Fetching all the InvoiceDetails");
		FinalResponse finalResponse = new FinalResponse();
		Object invoiceList = null; 
		try {
			invoiceList = repository.getAllInvoiceDetails();
		} catch (Exception e) {
			logger.error("Cannot fetch the InvoiceDetails List" + e.getMessage());
		}
		if (invoiceList != null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Records Inserted Successfully");
			finalResponse.setData(invoiceList);
			return finalResponse;
		}
		else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record cannot Inserted");
			return finalResponse;
		}

	}

	@Override
	public FinalResponse getInvoiceDetailsById(int invoiceId) {
		logger.info("Fetching InvoiceDetails by Id: " + invoiceId);
		FinalResponse finalResponse = new FinalResponse();
		Object invoiceDetail = null; 
		try {
			invoiceDetail = repository.getInvoiceDetailById(invoiceId);
		} catch (InputMismatchException e) {
			logger.error("Cannot fetcht the InvoiceDetails by Id " + invoiceId + e.getMessage());
		}
		if(invoiceDetail!=null) {
			logger.info("Fetched the InvoiceDetails by Id : " + invoiceId);
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Records Available");
			finalResponse.setData(invoiceDetail);
			return finalResponse;
		}else {
			logger.error("Cannot fetch the InvoiceDetails by Id : " + invoiceId);
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Records not Available");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse insertInvoiceDetails(Invoice invoice) {
		FinalResponse finalResponse = new FinalResponse();
		logger.info("Inserting one Recored::input::invoiceDetail: " + invoice );
		Object invoiceDetail =   repository.createInvoiceDetails(invoice);
		if(invoiceDetail!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("201");
			finalResponse.setMessage("Record is inserted");
			finalResponse.setData(invoiceDetail);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not inserted");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse updateInvoiceDetailsById(int invoiceId, Invoice invoice) {
		logger.info("Updating InvoiceDetail by Id: " + invoiceId);
		FinalResponse finalResponse = new FinalResponse();
		Object updatedDetail = null;
		try {
			updatedDetail = repository.updateSupplierOrderById(invoiceId, invoice);
		} catch (InputMismatchException e) {
			logger.error("Cannot Updated the InvoiceDetail for Id:" + invoiceId);
		}
		if(updatedDetail!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Record Updated Successfully");
			finalResponse.setData(updatedDetail);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not Updated");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse deleteInvoiceById(int invoiceId) throws Exception {
		logger.info("Deleting InvoiceDetail by Id: " + invoiceId);
		FinalResponse finalResponse = new FinalResponse();
		try {
			repository.deleteInvoiceById(invoiceId);
		} catch (InputMismatchException e) {
			e.printStackTrace();
			logger.error("Cannot deleted the InvoiceDetail by ID" + invoiceId + e.getMessage());
		}
		if(invoiceId !=0) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("204");
			finalResponse.setMessage("Record is deleted");
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not deleted");
			return finalResponse;
		}
	}
}
