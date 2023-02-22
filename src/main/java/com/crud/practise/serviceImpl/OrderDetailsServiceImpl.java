package com.crud.practise.serviceImpl;
import java.util.InputMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.OrderDetails;
import com.crud.practise.repository.OrderdetailsRepository;
import com.crud.practise.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{
	
	@Autowired
	private OrderdetailsRepository repository;
	
	Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Override
	public FinalResponse getAllOrderDetails() {
		logger.info("Fetching all the Order Details");
		FinalResponse finalResponse = new FinalResponse();
		Object ordersList = null; 
		try {
			ordersList = repository.getAllOrderDetails();
		} catch (Exception e) {
			logger.error("Cannot fetch OrdersDetails List" + e.getMessage());
		}
		if (ordersList != null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Records present");
			finalResponse.setData(ordersList);
			return finalResponse;
		}
		else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record not available to display");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse getOrderDetailById(int orderId) {
		logger.info("Fetching the OrderDetails by Id: " + orderId);
		FinalResponse finalResponse = new FinalResponse();
		Object orderDetails = null; 
		try {
			orderDetails = repository.getOrderDetailsById(orderId);
		} catch (InputMismatchException e) {
			logger.error("Cannot fetcht the Category Details by Id " + orderId + e.getMessage());
		}
		if(orderDetails!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Records Available");
			finalResponse.setData(orderDetails);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Records not Available");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse placeOrder(OrderDetails orderDetails) {
		logger.info("Inserting one Recored::input::orderDetails: " + orderDetails );
		Object newRecord = repository.saveOrderDetail(orderDetails);
		FinalResponse finalResponse = new FinalResponse();
		if(newRecord!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("201");
			finalResponse.setMessage("Record is inserted");
			finalResponse.setData(newRecord);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setMessage("Record is not inserted");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse updateOrder(int orderId, OrderDetails orderDetails) {
		logger.info("Updating orderDetails by Id: " + orderId);
		FinalResponse finalResponse = new FinalResponse();
		Object updatedDetail = null;
		try {
			updatedDetail = repository.updateOrderDetail(orderId, orderDetails);;
		} catch (InputMismatchException e) {
			logger.error("Cannot Updated the orderDetails for Id:" + orderId);
		}
		if(updatedDetail!=null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Record Updated Successfully");
			finalResponse.setData(updatedDetail);
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setMessage("Record is not Updated");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse deleteOrderById(int orderId) throws Throwable {
		logger.info("Deleting Category Details by Id: " + orderId);
		FinalResponse finalResponse = new FinalResponse();
		try {
			repository.deleteOrderById(orderId);
		} catch (InputMismatchException e) {
			logger.error("Cannot deleted the OrderDetail by ID" + orderId + e.getMessage());
		}
		if(orderId !=0) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("204");
			finalResponse.setMessage("Record is deleted");
			return finalResponse;
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setMessage("Record is not deleted");
			return finalResponse;
		}
	}
}
