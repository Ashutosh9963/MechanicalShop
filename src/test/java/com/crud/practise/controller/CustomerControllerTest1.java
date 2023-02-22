package com.crud.practise.controller;

import static org.assertj.core.api.Assertions.assertThat;  
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.crud.practise.model.CustomerDetails;
import com.crud.practise.model.FinalResponse;
import com.crud.practise.serviceImpl.CustomerDetailsServiceImpl;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for CustomerDetails Controller")
class CustomerControllerTest1 {
	
	@InjectMocks
	private CustomerDetailsController controller;
	
	@Mock
	CustomerDetailsServiceImpl service;

	@Test
	@DisplayName("To test getAllCustomerDetails, expected OK")
	void testGetAllCustomerDetails() {
		
		Object[] customer1 =  {
			    "customername" , "RAGHUPATI SINGH",
			    "mobile" , "9878458745",
			    "address" , "HARYANA",
			    "createdDate" , "2016-08-20",
			    "updateDate" , "2016-08-21"
		};
		
		Object[] customer2 =  {
			    "customername" , "RAGHUPATI NAYAK",
			    "mobile" , "9878458745",
			    "address" , "HARYANA",
			    "createdDate" , "2016-08-20",
			    "updateDate" , "2016-08-21"
		};
		List<Object[]> list = new ArrayList<>();
		list.add(customer1);
		list.add(customer2);
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("200");
		expected.setMessage("Records present");
		expected.setData(list);
		when(service.getAllCustomerDetails()).thenReturn(expected);
		FinalResponse actual = controller.getCustomerDetails();
		assertEquals(expected, actual);
	}
	
	@Test
	@DisplayName("To test insertCustomerDetails, expected OK")
	void testinsertCustomerDetail() {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCustomername("CHANDRAGUPTA");
		customerDetails.setMobile("9988773322");
		customerDetails.setAddress("HYDERABAD");
		customerDetails.setCreatedDate(java.sql.Date.valueOf("2016-02-22"));
		customerDetails.setUpdateDate(java.sql.Date.valueOf("2016-02-23"));
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("201");
		expected.setMessage("Record is inserted");
		expected.setData(customerDetails);
		when(service.insertCustomerDetail(any(CustomerDetails.class))).thenReturn(expected);
		FinalResponse actual = controller.saveCustomerDetails(customerDetails);
		assertThat(expected).isEqualTo(actual);
	}
	
	@Test
	@DisplayName("To test getAllCustomerDetailsByID, expected OK")
	void testGetCustomerDetailById() {
		
		Object[] customer1 =  {
			    "customername" , "RAGHUPATI SINGH",
			    "mobile" , "9878458745",
			    "address" , "HARYANA",
			    "createdDate" , "2016-08-20",
			    "updateDate" , "2016-08-21"
		};
		FinalResponse expected  = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("200");
		expected.setMessage("Records Available");
		expected.setData(customer1);
		when(service.getAllCustomerDetailsById(anyInt())).thenReturn(expected);
		FinalResponse actual = controller.getCustomerDetailById(1);
		assertThat(expected).isEqualTo(actual);
	}
	
	@Test
	@DisplayName("To test UpdateCustomerDetailById, expected OK")
	void testUpdateCustomerDetailById() {
		CustomerDetails customer1 = new CustomerDetails();
		customer1.setCustomerid(1);
		customer1.setCustomername("CHANDRAGUPTA");
		customer1.setMobile("9988773322");
		customer1.setAddress("HYDERABAD");
		customer1.setCreatedDate(java.sql.Date.valueOf("2016-02-22"));
		customer1.setUpdateDate(java.sql.Date.valueOf("2016-02-23"));
		
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("200");
		expected.setMessage("Record Updated Successfully");
		expected.setData(customer1);
		
		when(service.updateDetail(1, customer1)).thenReturn(expected);
		FinalResponse actual = controller.updateCustomerDetail(1, customer1);
		assertThat(expected).isEqualTo(actual);
	}
	
	@Test
	@DisplayName("To test deleteCustomerById, expected OK")
	void testDeleteCustomerById()  {
		CustomerDetails customer1 = new CustomerDetails();
		customer1.setCustomerid(1);
		customer1.setCustomername("CHANDRAGUPTA");
		customer1.setMobile("9988773322");
		customer1.setAddress("HYDERABAD");
		customer1.setCreatedDate(java.sql.Date.valueOf("2016-02-22"));
		customer1.setUpdateDate(java.sql.Date.valueOf("2016-02-23"));
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("204");
		expected.setMessage("Record is deleted");
		when(service.deleteRecordById(1)).thenReturn(expected);
		FinalResponse actual = controller.deleteCustomerById(1);
		assertThat(actual).isEqualTo(expected);
		
	}
}
