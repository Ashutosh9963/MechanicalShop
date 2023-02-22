package com.crud.practise.serviceImpl;

import java.util.InputMismatchException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.Warehouse;
import com.crud.practise.repository.WarehouseRepository;
import com.crud.practise.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService {
	
	@Autowired
	private WarehouseRepository repository;
	
	// Creating the Logger Object
	Logger logger = LoggerFactory.getLogger(SupplierOrdersServiceImpl.class);

	@Override
	public FinalResponse getAllWarehouseDetails() {
		logger.info("Fetching all the WarehouseDetails");
		FinalResponse finalResponse = new FinalResponse();
		Object warehouseList = null; 
		try {
			warehouseList = repository.getAllWarehouseDetails();
		} catch (Exception e) {
			logger.error("Cannot fetch the WarehouseDetails List" + e.getMessage());
		}
		if (warehouseList != null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Records Inserted Successfully");
			finalResponse.setData(warehouseList);
			return finalResponse;
		}
		else {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record cannot Inserted");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse getWarehouseDetailsById(int warehouseId) {
		logger.info("Fetching WarehouseDetails by Id: " + warehouseId);
		FinalResponse finalResponse = new FinalResponse();
		Object warehouseDetail = null; 
		try {
			warehouseDetail = repository.getWarehouseDetailById(warehouseId);
		} catch (InputMismatchException e) {
			logger.error("Cannot fetcht the WarehouseDetails by Id " + warehouseId + e.getMessage());
		}
		if(warehouseDetail!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Record is Available for ID" + warehouseId);
			finalResponse.setData(warehouseDetail);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Records not Available");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse insertWarehouseDetails(Warehouse warehouse) {
		FinalResponse finalResponse = new FinalResponse();
		logger.info("Inserting one Recored::input::WarehouseDetail: " + warehouse );
		Object warehouseDetail =   repository.insertWarehouseDetail(warehouse);
		if(warehouseDetail!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("201");
			finalResponse.setMessage("Record is inserted");
			finalResponse.setData(warehouseDetail);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not inserted");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse updateWarehouseDetailsById(int warehouseId, Warehouse warehouse) {
		logger.info("Updating WarehouseDetails by Id: " + warehouseId);
		FinalResponse finalResponse = new FinalResponse();
		Object updatedDetail = null;
		try {
			updatedDetail = repository.updateWarehouseById(warehouseId, warehouse);
		} catch (InputMismatchException e) {
			e.printStackTrace();
			logger.error("Cannot Updated the WarehouseDetails for Id:" + warehouseId);
		}
		if(updatedDetail!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Record Updated Successfully for ID" + warehouseId);
			finalResponse.setData(updatedDetail);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not Updated for Id" + warehouseId);
			return finalResponse;
		}
	}

	@Override
	public FinalResponse deleteWarehouseById(int warehouseId) throws Exception {
		logger.info("Deleting WarehouseDetails by Id: " + warehouseId);
		FinalResponse finalResponse = new FinalResponse();
		try {
			repository.deleteSupplierDetailById(warehouseId);
		} catch (InputMismatchException e) {
			logger.error("Cannot deleted the WarehouseDetails by ID" + warehouseId + e.getMessage());
		}
		if(warehouseId !=0) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("204");
			finalResponse.setMessage("Record is deleted for Id" + warehouseId);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not Deleted for ID" + warehouseId);
			return finalResponse;
		}
	}

	@Override
	public FinalResponse insertMultipleWarehouseDetails(List<Warehouse> warehouse) {
		FinalResponse finalResponse = new FinalResponse();
		logger.info("Inserting one Recored::input::WarehouseDetail: " + warehouse );
		List<Warehouse> warehouseDetail = repository.insertMultipleDetails(warehouse);
		if(warehouseDetail!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("201");
			finalResponse.setMessage("Records are inserted");
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not inserted");
			return finalResponse;
		}
	}

}
