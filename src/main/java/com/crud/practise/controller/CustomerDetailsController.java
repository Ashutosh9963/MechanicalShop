package com.crud.practise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.practise.model.CustomerDetails;
import com.crud.practise.model.FinalResponse;
import com.crud.practise.serviceImpl.CustomerDetailsServiceImpl;

import jakarta.validation.Valid;


@RestController
public class CustomerDetailsController {
	
	@Autowired
	CustomerDetailsServiceImpl customerDetailsServiceImpl;
	
	@GetMapping("/getAllCustomerDetails")
//	@ResponseStatus(HttpStatus.OK)
	public FinalResponse getCustomerDetails() {
		return customerDetailsServiceImpl.getAllCustomerDetails();
	}
	
	@GetMapping("/getCustomerDetailsById/{id}")
//	@ResponseStatus(HttpStatus.OK)
	public FinalResponse getCustomerDetailById(@PathVariable  int id) {
		return customerDetailsServiceImpl.getAllCustomerDetailsById(id);
	}
	
	@PutMapping("/updateCustomerDetailById/{id}")
//	@ResponseStatus(HttpStatus.OK)
	public FinalResponse updateCustomerDetail(@PathVariable("id") int id, @RequestBody @Valid CustomerDetails customerDetails) {
		return customerDetailsServiceImpl.updateDetail(id, customerDetails);
	}
	
	@PostMapping("/saveCustomerDetail")
//	@ResponseStatus(HttpStatus.CREATED)
	public FinalResponse saveCustomerDetails(@RequestBody @Valid  CustomerDetails customerDetails) {
		return customerDetailsServiceImpl.insertCustomerDetail(customerDetails);
	}
	
	@DeleteMapping("/deleteCustomerById/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
	public FinalResponse deleteCustomerById(@PathVariable("id") int id ) {
		return customerDetailsServiceImpl.deleteRecordById(id);
	}

}
