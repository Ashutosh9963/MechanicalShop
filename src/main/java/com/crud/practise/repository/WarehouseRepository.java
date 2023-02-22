package com.crud.practise.repository;

import java.util.List;

import com.crud.practise.model.Warehouse;

public interface WarehouseRepository {
	
	public List<Object[]> getAllWarehouseDetails();
	
	public Object getWarehouseDetailById(int warehouseId);
	
	public Warehouse insertWarehouseDetail(Warehouse warehouse);
	
	public Object updateWarehouseById(int warehouseId, Warehouse warehouse);
	
	public Warehouse deleteSupplierDetailById(int warehouseId) throws Exception;
	
	public List<Warehouse> insertMultipleDetails(List<Warehouse> warehouse);

}
