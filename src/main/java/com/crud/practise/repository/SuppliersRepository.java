package com.crud.practise.repository;

import java.util.List;

import com.crud.practise.model.Suppliers;

public interface SuppliersRepository {
	
	public List<Object[]> getAllSupplierDetails();
	
	public Object getSupplierDetailById(int supplierId);
	
	public Suppliers insertSupplierDetail(Suppliers suppliers);
	
	public Object updateSupplierById(int supplierId, Suppliers suppliers);
	
	public Suppliers deleteSupplierDetailById(int supplierId) throws Exception;

}
