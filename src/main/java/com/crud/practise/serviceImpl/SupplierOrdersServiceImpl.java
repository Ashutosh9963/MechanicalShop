package com.crud.practise.serviceImpl;

import java.util.InputMismatchException; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.SupplierOrders;
import com.crud.practise.repository.SupplierOrdersRepository;
import com.crud.practise.service.SupplierOrdersService;

@Service
public class SupplierOrdersServiceImpl implements SupplierOrdersService {
	
	@Autowired
	private SupplierOrdersRepository repository;
	
	// Creating the Logger Object
	Logger logger = LoggerFactory.getLogger(SupplierOrdersServiceImpl.class);

	@Override
	public FinalResponse getAllSupplierOrderDetails() {
		logger.info("Fetching all the SupplierOrderdsDetails");
		FinalResponse finalResponse = new FinalResponse();
		Object supplierOrderList = null; 
		try {
			supplierOrderList = repository.getAllSupplierOrdereDetails();
		} catch (Exception e) {
			logger.error("Cannot fetch the SupplierOrdersDetails List" + e.getMessage());
		}
		if (supplierOrderList != null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Records Inserted Successfully");
			finalResponse.setData(supplierOrderList);
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
	public FinalResponse getSupplierOrderById(int supplierOrderId) {
		logger.info("Fetching SupplierOrderDetails by Id: " + supplierOrderId);
		FinalResponse finalResponse = new FinalResponse();
		Object supplierOrderDetail = null; 
		try {
			supplierOrderDetail = repository.getSupplierOrderDetailById(supplierOrderId);
		} catch (InputMismatchException e) {
			logger.error("Cannot fetcht the SupplierOrderDetails by Id " + supplierOrderId + e.getMessage());
		}
		if(supplierOrderDetail!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Records Available");
			finalResponse.setData(supplierOrderDetail);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setMessage("Records not Available");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse insertSupplierOrderDetails(SupplierOrders supplierOrders) {
		FinalResponse finalResponse = new FinalResponse();
		logger.info("Inserting one Recored::input::supplierOrderDetail: " + supplierOrders );
		Object supplierOrderDetail =   repository.insertSupplierOrder(supplierOrders);
		if(supplierOrderDetail!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("201");
			finalResponse.setMessage("Record is inserted");
			finalResponse.setData(supplierOrderDetail);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not inserted");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse updateSupplierOrderDetailsById(int supplierOrderId, SupplierOrders supplierOrders) {
		logger.info("Updating SupplierOrderDetails by Id: " + supplierOrderId);
		FinalResponse finalResponse = new FinalResponse();
		Object updatedDetail = null;
		try {
			updatedDetail = repository.updateSupplierOrderById(supplierOrderId, supplierOrders);
		} catch (InputMismatchException e) {
			logger.error("Cannot Updated the SupplierOrderDetails for Id:" + supplierOrderId);
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
			finalResponse.setMessage("Record is not Updated");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse deleteDetailsById(int supplierOrderId) throws Exception {
		logger.info("Deleting SupplierOrderDetails by Id: " + supplierOrderId);
		FinalResponse finalResponse = new FinalResponse();
		try {
			repository.deleteSupplierOrderDetailById(supplierOrderId);
		} catch (InputMismatchException e) {
			logger.error("Cannot deleted the SupplierOrderDetails by ID" + supplierOrderId + e.getMessage());
		}
		if(supplierOrderId !=0) {
			logger.info("Deleted the data for SupplierOrderDetails by Id: " + supplierOrderId);
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
