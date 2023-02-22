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
import com.crud.practise.model.SupplierOrders;
import com.crud.practise.serviceImpl.SupplierOrdersServiceImpl;

import jakarta.validation.Valid;

@RestController
public class SupplierOrdersController {
	
	@Autowired
	private SupplierOrdersServiceImpl service;
	
	@GetMapping("/getAllSupplierOrdersDetails")
	public FinalResponse getAllCategoryDetails() {
		return service.getAllSupplierOrderDetails();
	}
	
	@GetMapping("/getSupplierOrderDetailsById/{id}")
	public FinalResponse getSupplierorderDetailById(@PathVariable int id) {
		return service.getSupplierOrderById(id);
	}
	
	@PutMapping("/updateSupplierOrderDetailById/{id}")
	public FinalResponse updateSuppliersOrderDetail(@PathVariable int id, @RequestBody @Valid SupplierOrders supplierOrders) {
		return service.updateSupplierOrderDetailsById(id, supplierOrders);
	}
	
	@PostMapping("/insertSupplierOrderDetail")
	public FinalResponse saveSupplierOrderDetails(@RequestBody @Valid SupplierOrders supplierOrders) {
		return service.insertSupplierOrderDetails(supplierOrders);
	}
	
	@DeleteMapping("/deleteSupplierOrderdetailById/{id}")
	public FinalResponse deleteSupplierOrderDetailrById(@PathVariable  int id ) throws Throwable {
		return service.deleteDetailsById(id);
	}

}
