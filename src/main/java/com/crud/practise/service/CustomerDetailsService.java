package com.crud.practise.service;


import com.crud.practise.model.CustomerDetails;
import com.crud.practise.model.FinalResponse;

public interface CustomerDetailsService {
	
    public FinalResponse getAllCustomerDetails();
    
    public FinalResponse getAllCustomerDetailsById(int id);
		
	public FinalResponse insertCustomerDetail(CustomerDetails customerDetails);
	
	public FinalResponse updateDetail(int customerid, CustomerDetails customerDetails);
	
	public FinalResponse deleteRecordById(int customerid);
	

}
