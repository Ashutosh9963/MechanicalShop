package com.crud.practise.service;

import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.OrderDetails;

public interface OrderDetailsService {
	
    public FinalResponse getAllOrderDetails();
    
    public FinalResponse getOrderDetailById(int id);
	
	public FinalResponse placeOrder(OrderDetails orderDetails);
	
	public FinalResponse updateOrder(int orderId, OrderDetails orderDetails);
	
	public FinalResponse deleteOrderById(int orderId) throws Throwable;

}
