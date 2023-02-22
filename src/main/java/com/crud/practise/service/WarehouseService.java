package com.crud.practise.service;

import java.util.List;

import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.Warehouse;

public interface WarehouseService {
	
	public FinalResponse getAllWarehouseDetails();
	
	public FinalResponse getWarehouseDetailsById(int warehouseId);
	
	public FinalResponse insertWarehouseDetails(Warehouse warehouse);
	
	public FinalResponse updateWarehouseDetailsById(int warehouseId, Warehouse warehouse);
	
	public FinalResponse deleteWarehouseById(int warehouseId) throws Exception;

	public FinalResponse insertMultipleWarehouseDetails(List<Warehouse> warehouse);
	
}
