package com.crud.practise.service;

import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.Suppliers;

public interface SuppliersService {
	
	public FinalResponse getAllSupplierDetails();
	
	public FinalResponse getSupplierById(int supplierId);
	
	public FinalResponse insertSupplierDetails(Suppliers suppliers);
	
	public FinalResponse updateSupplierDetailsById(int supplierId, Suppliers suppliers);
	
	public FinalResponse deleteSupplierDetailsById(int supplierId) throws Exception;

}
