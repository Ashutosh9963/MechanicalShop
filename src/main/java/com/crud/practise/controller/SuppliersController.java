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
import com.crud.practise.model.Suppliers;
import com.crud.practise.serviceImpl.SuppliersServiceImpl;

import jakarta.validation.Valid;

@RestController
public class SuppliersController {
	
	@Autowired
	private SuppliersServiceImpl service;
	
	@GetMapping("/getAllSupplierDetails")
	public FinalResponse getAllSupplierDetails() {
		return service.getAllSupplierDetails();
	}
	
	@GetMapping("/getSupplierDetailsById/{id}")
	public FinalResponse getSupplierDetailById(@PathVariable int id) {
		return service.getSupplierById(id);
	}
	
	@PutMapping("/updateSupplierDetailById/{id}")
	public FinalResponse updateSupplierDetail(@PathVariable int id, @RequestBody @Valid Suppliers suppliers) {
		return service.updateSupplierDetailsById(id, suppliers);
	}
	
	@PostMapping("/insertSupplierDetail")
	public FinalResponse insertSupplierDetails(@RequestBody @Valid Suppliers suppliers) {
		return service.insertSupplierDetails(suppliers);
	}
	
	@DeleteMapping("/deleteSupplierDetailById/{id}")
	public FinalResponse deleteSupplierById(@PathVariable  int id ) throws Throwable {
		return service.deleteSupplierDetailsById(id);
	}


}
