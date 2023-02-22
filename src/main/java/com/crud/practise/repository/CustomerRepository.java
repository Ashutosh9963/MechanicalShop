package com.crud.practise.repository;

import java.util.List; 

import com.crud.practise.model.CustomerDetails;

public interface CustomerRepository {
	
	public List<Object[]> getAllCustomerDetails();
	
	public Object[] getAllCustomerDetailsById(int id);
		
	public Object insertCustomerDetail(CustomerDetails customerDetails);
	
	public CustomerDetails updateDetail(int customerid, CustomerDetails customerDetails);
	
	public int deleteRecordById(int customerid);
	
}
