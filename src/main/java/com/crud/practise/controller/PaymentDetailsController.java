package com.crud.practise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.PaymentDetails;
import com.crud.practise.serviceImpl.PaymentDetailsServiceImpl;

import jakarta.validation.Valid;

@RestController
public class PaymentDetailsController {
	
	@Autowired
	private PaymentDetailsServiceImpl service;
	
	@GetMapping("/getAllPaymentDetails")
	public FinalResponse getAllCategoryDetails() {
		return service.getAllPaymentDetails();
	}
	
	@GetMapping("/getPaymentDetailsById/{id}")
	public FinalResponse getCategoryDetailById(@PathVariable int id) {
		return service.getPaymentDetailsById(id);
	}
	
	@PutMapping("/updatePaymentDetailById/{id}")
	public FinalResponse updateCategoryDetail(@PathVariable int id, @RequestBody @Valid PaymentDetails paymentDetails) {
		return service.updatePaymentDetailsById(id, paymentDetails);
	}
	
	@PostMapping("/insertPaymentDetail")
	public FinalResponse saveCategoryDetails(@RequestBody @Valid PaymentDetails paymentDetails) {
		return service.insertPaymentDetails(paymentDetails);
	}
	
	@DeleteMapping("/deletePaymentDetailById/{id}")
	public FinalResponse deleteCustomerById(@PathVariable  int id ) throws Throwable {
		return service.deletePaymentDetailsById(id);
	}

}
