package com.crud.practise.repository;

import java.util.List;

import com.crud.practise.model.SupplierOrders;

public interface SupplierOrdersRepository {
	
	public List<Object[]> getAllSupplierOrdereDetails();
	
	public Object getSupplierOrderDetailById(int supplierOrderId);
	
	public SupplierOrders insertSupplierOrder(SupplierOrders supplierOrders);
	
	public Object updateSupplierOrderById(int supplierOrderId, SupplierOrders supplierOrders);
	
	public SupplierOrders deleteSupplierOrderDetailById(int supplierOrderId) throws Exception;

}
