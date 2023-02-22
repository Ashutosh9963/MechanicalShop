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
import com.crud.practise.model.Invoice;
import com.crud.practise.serviceImpl.InvoiceServiceImpl;

import jakarta.validation.Valid;

@RestController
public class InvoiceController {
	
	@Autowired
	private InvoiceServiceImpl service;
	
	@GetMapping("/getAllInvoiceDetails")
	public FinalResponse getAllCategoryDetails() {
		return service.getAllInvoiceDetails();
	}
	
	@GetMapping("/getInvoiceDetailsById/{id}")
	public FinalResponse getCategoryDetailById(@PathVariable int id) {
		return service.getInvoiceDetailsById(id);
	}
	
	@PutMapping("/updateInvoiceDetailById/{id}")
	public FinalResponse updateCategoryDetail(@PathVariable int id, @RequestBody @Valid Invoice invoice) {
		return service.updateInvoiceDetailsById(id, invoice);
	}
	
	@PostMapping("/createInvoice")
	public FinalResponse saveCategoryDetails(@RequestBody @Valid Invoice invoice) {
		return service.insertInvoiceDetails(invoice);
	}
	
	@DeleteMapping("/deleteInvoiceById/{id}")
	public FinalResponse deleteCustomerById(@PathVariable  int id ) throws Throwable {
		return service.deleteInvoiceById(id);
	}

}
