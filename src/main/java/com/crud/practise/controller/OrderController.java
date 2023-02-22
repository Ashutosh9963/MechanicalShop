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
import com.crud.practise.model.OrderDetails;
import com.crud.practise.serviceImpl.OrderDetailsServiceImpl;

import jakarta.validation.Valid;

@RestController
public class OrderController {
	
	@Autowired
	private OrderDetailsServiceImpl service;
	
	@GetMapping("/getAllOrderDetails")
	public FinalResponse getOrderDetails() {
		return service.getAllOrderDetails();
	}
	
	@GetMapping("/getOrderDetailById/{id}")
	public FinalResponse getOrderDetailById(@PathVariable int id) {
		return service.getOrderDetailById(id);
	}
	
	@PutMapping("/updateOrderById/{id}")
	public FinalResponse updateOrderDetailsById(@PathVariable int id, @RequestBody @Valid OrderDetails orderDetails) {
		return service.updateOrder(id, orderDetails);
	}
	
	@PostMapping("/saveOrderDetails")
	public FinalResponse saveOrderDetails(@RequestBody @Valid OrderDetails orderDetails) {
		return service.placeOrder(orderDetails);
	}
	
	@DeleteMapping("/deleteOrderDetailsById/{id}")
	public FinalResponse deleteOrderById(@PathVariable int id) throws Throwable {
		return service.deleteOrderById(id);
	}

}
