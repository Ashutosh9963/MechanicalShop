package com.crud.practise.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
import com.crud.practise.repositoryImpl.CustomerRepositoryImpl;
import com.crud.practise.serviceImpl.CustomerDetailsServiceImpl;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Class for Customer Service")
public class TestCustomerService {
	
	@InjectMocks
	private CustomerDetailsServiceImpl custService;
	
	@Mock
	private CustomerRepositoryImpl repository;
	
	@Test
	@DisplayName("Test getAllCustomerDetails: Positive case, expected OK")
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
		
		List<Object[]> customerList = new ArrayList<>();
		customerList.add(customer1);
		customerList.add(customer2);
		when(repository.getAllCustomerDetails()).thenReturn(customerList);
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("200");
		expected.setMessage("Records present");
		expected.setData(customerList);
		FinalResponse actual = custService.getAllCustomerDetails();
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	@DisplayName("Test getAllCustomerDetails: Negative case, expected OK")
	void testGetAllCustomerDetailsNeg() {
		List<Object[]> customerList = null;
		when(repository.getAllCustomerDetails()).thenReturn(customerList);
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("501");
		expected.setMessage("Record not available to display");
		FinalResponse actual = custService.getAllCustomerDetails();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	@DisplayName("Test getAllCustomerDetailsById: Positive case, expected OK")
	void testgetAllCustomerDetailsById() {
		Object[] customer1 =  {
				"customerId" , 1,
			    "customername" , "RAGHUPATI SINGH",
			    "mobile" , "9878458745",
			    "address" , "HARYANA",
			    "createdDate" , "2016-08-20",
			    "updateDate" , "2016-08-21"
		};
		
//		CustomerDetails customer2 = new CustomerDetails();
//		customer2.setCustomerid(1);
//		customer2.setCustomername("Kishan");;
//		customer2.setMobile("9696969696");;
//		customer2.setAddress("Hyd");;
//		customer2.setCreatedDate(java.sql.Date.valueOf("2023-02-02"));
//		customer2.setUpdateDate(java.sql.Date.valueOf("2023-02-02"));
//		Object[] newCustomer = (Object[])customer2;
		
		when(repository.getAllCustomerDetailsById(1)).thenReturn(customer1);
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("200");
		expected.setMessage("Records Available");
		expected.setData(customer1);
		FinalResponse actual = custService.getAllCustomerDetailsById(1);  
		assertThat(expected).isEqualTo(actual);
	}
	
	// negative test case failing
	
	@Test
	@DisplayName("Test getAllCustomerDetailsById: Negetive case, expected OK")
	void testgetAllCustomerDetailsByIdNeg() {
		
		Object[] customer1 =  null;
		
		when(repository.getAllCustomerDetailsById(1)).thenReturn(customer1);
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("501");
		expected.setMessage("Records not Available");
		FinalResponse actual = custService.getAllCustomerDetailsById(1);  // 
		assertThat(expected).isEqualTo(actual);
	}
	
	@Test
	@DisplayName("Test insertCustomerDetail : Positive Case : Expected OK")
	void testInsertCustomerDetail() {
		CustomerDetails customer1 = new CustomerDetails();
		customer1.setCustomerid(1);
		customer1.setCustomername("Kishan");
		customer1.setMobile("9696969696");
		customer1.setAddress("Hyd");
		customer1.setCreatedDate(java.sql.Date.valueOf("2023-02-02"));
		customer1.setUpdateDate(java.sql.Date.valueOf("2023-02-02"));
		when(repository.insertCustomerDetail(any(CustomerDetails.class))).thenReturn(customer1);
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("201");
		expected.setMessage("Record is inserted");
		expected.setData(customer1);
		FinalResponse actual = custService.insertCustomerDetail(customer1);
		assertThat(expected).isEqualTo(actual);
	}
	
	@Test
	@DisplayName("Test insertCustomerDetail : Negetive Case : Expected OK")
	void testInsertCustomerDetailNeg() {
		CustomerDetails customer1 = new CustomerDetails();
		customer1.setCustomerid(1);
//		customer1.setCustomername("Kishan");
		customer1.setMobile("9696969696");
		customer1.setAddress("Hyd");
		when(repository.insertCustomerDetail(customer1)).thenReturn(null);
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("501");
		expected.setMessage("Record is not inserted");
		FinalResponse actual = custService.insertCustomerDetail(customer1);
		assertThat(expected).isEqualTo(actual);
	}
	
	@Test
	@DisplayName("Test updateDetail : Positive Case : Expected OK")
	void testupdateDetail() {
		CustomerDetails customer1 = new CustomerDetails();
		customer1.setCustomerid(1);
		customer1.setCustomername("Kishan");
		customer1.setMobile("9696969696");
		customer1.setAddress("Hyd");
		customer1.setCreatedDate(java.sql.Date.valueOf("2023-02-02"));
		customer1.setUpdateDate(java.sql.Date.valueOf("2023-02-02"));
		when(repository.updateDetail(1, customer1)).thenReturn(customer1);
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("200");
		expected.setMessage("Record Updated Successfully");
		expected.setData(customer1);
		FinalResponse actual = custService.updateDetail(1, customer1);
		assertThat(expected).isEqualTo(actual);
	}
	
	@Test
	@DisplayName("Test updateDetail : Negetive Case : Expected OK")
	void testupdateDetailNeg() {
		CustomerDetails customer1 = null;
		when(repository.updateDetail(1, customer1)).thenReturn(null);
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("501");
		expected.setMessage("Record is not Updated");
		FinalResponse actual = custService.updateDetail(1, customer1);
		assertThat(expected).isEqualTo(actual);
	}
	
	@Test
	@DisplayName("Test deleteRecordById : Positive Case : Expected OK")
	void testDeleteRecordById()  {
		CustomerDetails customer1 = new CustomerDetails();
		customer1.setCustomerid(1);
		customer1.setCustomername("Kishan");
		customer1.setMobile("9696969696");
		customer1.setAddress("Hyd");
		customer1.setCreatedDate(java.sql.Date.valueOf("2023-02-02"));
		customer1.setUpdateDate(java.sql.Date.valueOf("2023-02-02"));
		when(repository.deleteRecordById(1)).thenReturn(1);
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("204");
		expected.setMessage("Record is deleted for the given ID");
		FinalResponse actual = custService.deleteRecordById(1);
		assertThat(expected).isEqualTo(actual);
	}
	
	@Test
	@DisplayName("Test deleteRecordById : Negetive Case : Expected OK")
	void testDeleteRecordByIdNeg() {
		CustomerDetails customer1 = null;
		when(repository.deleteRecordById(1)).thenReturn(0);
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("501");
		expected.setMessage("Record not available to deleted for the given ID");
		FinalResponse actual = custService.deleteRecordById(1);
		assertThat(expected).isEqualTo(actual);
	}
	
	
	

}
