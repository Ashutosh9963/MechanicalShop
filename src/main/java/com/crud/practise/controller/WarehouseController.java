package com.crud.practise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.Warehouse;
import com.crud.practise.serviceImpl.WarehouseServiceImpl;

import jakarta.validation.Valid;

@RestController
public class WarehouseController {
	
	@Autowired
	private WarehouseServiceImpl service;
	
	@GetMapping("/getAllWarehouseDetails")
	public FinalResponse getAllWarehouseDetails() {
		return service.getAllWarehouseDetails();
	}
	
	@GetMapping("/getWarehouseDetailsById/{id}")
	public FinalResponse getWarehouseDetailById(@PathVariable int id) {
		return service.getWarehouseDetailsById(id);
	}
	
	@PutMapping("/updateWarehouseDetailById/{id}")
	public FinalResponse updateWarehouseDetail(@PathVariable int id, @RequestBody @Valid Warehouse warehouse) {
		return service.updateWarehouseDetailsById(id, warehouse);
	}
	
	@PostMapping("/insertWarehouseDetail")
	public FinalResponse insertWarehouseDetails(@RequestBody @Valid Warehouse warehouse) {
		return service.insertWarehouseDetails(warehouse);
	}
	
	@DeleteMapping("/deleteWarehouseDetailById/{id}")
	public FinalResponse deleteSupplierById(@PathVariable  int id ) throws Throwable {
		return service.deleteWarehouseById(id);
	}

	@PostMapping("/insertMultipleWarehouseDetails")
	public FinalResponse multipleInsert(@RequestBody List<Warehouse> warehouse) {
		return service.insertMultipleWarehouseDetails(warehouse);
	}
}
