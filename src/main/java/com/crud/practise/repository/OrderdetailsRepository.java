package com.crud.practise.repository;

import java.util.List;

import com.crud.practise.model.OrderDetails;

public interface OrderdetailsRepository {
	
		public Object[] getOrderDetailsById(int id);

		public List<Object[]> getAllOrderDetails();
	
		public OrderDetails saveOrderDetail(OrderDetails orderDetails);

		public OrderDetails updateOrderDetail(int orderId, OrderDetails orderDetails);

		public OrderDetails deleteOrderById(int orderId) throws Exception;
	
}
