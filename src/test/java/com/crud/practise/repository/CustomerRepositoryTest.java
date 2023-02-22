package com.crud.practise.repository;

import static org.assertj.core.api.Assertions.assertThat;     
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crud.practise.model.CustomerDetails;
import com.crud.practise.repositoryImpl.CustomerRepositoryImpl;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for Repository with Mockito")
public class CustomerRepositoryTest {
	
	@Mock
	private CustomerRepositoryImpl customerRepositoryImpl;
	
	@Test
	@DisplayName("Test to  getAllCustomerDetails, Positive Case : expected OK ")
	void testGetAllCustomerDetailsRepository() {
		Object[] customer1 =  {
				"customerId" , 1,
			    "customername" , "RAGHUPATI SINGH",
			    "mobile" , "9878458745",
			    "address" , "HARYANA",
			    "createdDate" , "2016-08-20",
			    "updateDate" , "2016-08-21"
		};
		
		Object[] customer2 =  {
				"customerId" , 2,
			    "customername" , "RAGHUPATI NAYAK",
			    "mobile" , "9878458745",
			    "address" , "HARYANA",
			    "createdDate" , "2016-08-20",
			    "updateDate" , "2016-08-21"
		};
		List<Object[]> customerList = new ArrayList<>();
		customerList.add(customer1);
		customerList.add(customer2);
		when(customerRepositoryImpl.getAllCustomerDetails()).thenReturn(customerList);
		List<Object[]> newCustomerList = customerRepositoryImpl.getAllCustomerDetails();
		assertThat(customerList).isEqualTo(newCustomerList);
		assertEquals(2, newCustomerList.size());
	}
	
	@Test
	@DisplayName("Test to check insertCustomerDetail, expected OK")
	void testinsertCustomerDetailRepository() {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCustomername("CHANDRAGUPTA");
		customerDetails.setMobile("9988773322");
		customerDetails.setAddress("HYDERABAD");
		customerDetails.setCreatedDate(java.sql.Date.valueOf("2016-02-22"));
		customerDetails.setUpdateDate(java.sql.Date.valueOf("2016-02-23"));
		
		when(customerRepositoryImpl.insertCustomerDetail(any(CustomerDetails.class))).thenReturn(customerDetails);
		Object newCustomer = customerRepositoryImpl.insertCustomerDetail(customerDetails);
		assertThat(customerDetails).isEqualTo(newCustomer);
		assertThat(customerDetails).usingRecursiveComparison().isEqualTo(newCustomer);
	}
	
	@Test
	@DisplayName("Test to check updateDetail, expected OK")
	void testUpdateDetail() {
		
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCustomerid(1);
		customerDetails.setCustomername("CHANDRAGUPTA");
		customerDetails.setMobile("9988773322");
		customerDetails.setAddress("HYDERABAD");
		customerDetails.setCreatedDate(java.sql.Date.valueOf("2016-02-22"));
		customerDetails.setUpdateDate(java.sql.Date.valueOf("2016-02-23"));
		
		when(customerRepositoryImpl.updateDetail(anyInt(), any(CustomerDetails.class))).thenReturn(customerDetails);
		CustomerDetails updatedCustomerDetails = customerRepositoryImpl.updateDetail(1, customerDetails);
		assertThat(customerDetails).isEqualTo(updatedCustomerDetails);
		assertThat(updatedCustomerDetails).usingRecursiveComparison().isEqualTo(updatedCustomerDetails);
	}
	
	@Test
	@DisplayName(" Test to check deleteRecordById, expected OK")
	void testDeleteRecordById() {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCustomerid(1);
		customerDetails.setCustomername("CHANDRAGUPTA");
		customerDetails.setMobile("9988773322");
		customerDetails.setAddress("HYDERABAD");
		customerDetails.setCreatedDate(java.sql.Date.valueOf("2016-02-22"));
		customerDetails.setUpdateDate(java.sql.Date.valueOf("2016-02-23"));
		when(customerRepositoryImpl.deleteRecordById(1)).thenReturn(1);
		int actual = customerRepositoryImpl.deleteRecordById(1);
		assertThat(actual).isEqualTo(1);
	}
	
	@Test
	@DisplayName("Test to check getAllCustomerDetailsById, expected OK")
	void testGetAllCustomerDetailsById() {
		Object[] customer1 =  {
				"customerId" , 1,
			    "customername" , "RAGHUPATI SINGH",
			    "mobile" , "9878458745",
			    "address" , "HARYANA",
			    "createdDate" , "2016-08-20",
			    "updateDate" , "2016-08-21"
		};
		when(customerRepositoryImpl.getAllCustomerDetailsById(1)).thenReturn(customer1);
		Object[] fetchedData = customerRepositoryImpl.getAllCustomerDetailsById(1);
		assertThat(fetchedData).isEqualTo(customer1);
		assertThat(fetchedData).usingRecursiveComparison().isEqualTo(customer1);
	}
}
