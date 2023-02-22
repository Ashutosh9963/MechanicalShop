package com.crud.practise.serviceImpl;

import java.util.InputMismatchException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.Suppliers;
import com.crud.practise.repository.SuppliersRepository;
import com.crud.practise.service.SuppliersService;

@Service
public class SuppliersServiceImpl implements SuppliersService {
	
	@Autowired
	private SuppliersRepository repository;
	
	// Creating the Logger Object
	Logger logger = LoggerFactory.getLogger(SupplierOrdersServiceImpl.class);

	@Override
	public FinalResponse getAllSupplierDetails() {
		logger.info("Fetching all the SupplierDetails");
		FinalResponse finalResponse = new FinalResponse();
		Object supplierList = null; 
		try {
			supplierList = repository.getAllSupplierDetails();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Cannot fetch the SupplierDetails List" + e.getMessage());
		}
		if (supplierList != null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Records Inserted Successfully");
			finalResponse.setData(supplierList);
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
	public FinalResponse getSupplierById(int supplierId) {
		logger.info("Fetching SupplierDetails by Id: " + supplierId);
		FinalResponse finalResponse = new FinalResponse();
		Object supplierDetail = null; 
		try {
			supplierDetail = repository.getSupplierDetailById(supplierId);
		} catch (InputMismatchException e) {
			logger.error("Cannot fetcht the SupplierDetails by Id " + supplierId + e.getMessage());
		}
		if(supplierDetail!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Records Available");
			finalResponse.setData(supplierDetail);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Records not Available");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse insertSupplierDetails(Suppliers suppliers) {
		FinalResponse finalResponse = new FinalResponse();
		logger.info("Inserting one Recored::input::supplierDetail: " + suppliers );
		Object supplierDetail =   repository.insertSupplierDetail(suppliers);
		if(supplierDetail!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("201");
			finalResponse.setMessage("Record is inserted");
			finalResponse.setData(supplierDetail);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not inserted");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse updateSupplierDetailsById(int supplierId, Suppliers suppliers) {
		logger.info("Updating SupplierDetails by Id: " + supplierId);
		FinalResponse finalResponse = new FinalResponse();
		Object updatedDetail = null;
		try {
			updatedDetail = repository.updateSupplierById(supplierId, suppliers);
		} catch (InputMismatchException e) {
			logger.error("Cannot Updated the SupplierDetails for Id:" + supplierId);
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
	public FinalResponse deleteSupplierDetailsById(int supplierId) throws Exception {
		logger.info("Deleting SupplierDetails by Id: " + supplierId);
		FinalResponse finalResponse = new FinalResponse();
		try {
			repository.deleteSupplierDetailById(supplierId);
		} catch (InputMismatchException e) {
			logger.error("Cannot deleted the SupplierDetails by ID" + supplierId + e.getMessage());
		}
		if(supplierId !=0) {
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
