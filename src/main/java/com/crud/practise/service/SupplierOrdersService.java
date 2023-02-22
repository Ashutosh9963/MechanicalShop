package com.crud.practise.service;

import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.SupplierOrders;

public interface SupplierOrdersService {
	
	public FinalResponse getAllSupplierOrderDetails();
	
	public FinalResponse getSupplierOrderById(int supplierOrderId);
	
	public FinalResponse insertSupplierOrderDetails(SupplierOrders supplierOrders);
	
	public FinalResponse updateSupplierOrderDetailsById(int supplierOrderId, SupplierOrders supplierOrders);
	
	public FinalResponse deleteDetailsById(int supplierOrderId) throws Exception;

}
