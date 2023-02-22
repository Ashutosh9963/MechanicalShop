package com.crud.practise.unitTestCases;

import static org.assertj.core.api.Assertions.assertThat; 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crud.practise.model.CustomerDetails;
import com.crud.practise.repositoryImpl.CustomerRepositoryImpl;

@SpringBootTest
@DisplayName("Iam a Unit Test Class")
class UnitTestCasesForApis {
	
	@Autowired
	private CustomerRepositoryImpl customerRepositoryImpl;
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
//	private CustomerDetails customer;
//	private CustomerDetails customer1;
	
//	@BeforeEach
//	void init() {
//	}
	
	@Test
	@DisplayName("Test for Data insertion, expected OK")
	 void insertDetailsTest() {
		CustomerDetails customer = new CustomerDetails();
		customer.setCustomerid(2642);  
		customer.setCustomername("PARAM HANS");
		customer.setMobile("8585887745");
		customer.setAddress("Kolkata");
		customer.setCreatedDate(java.sql.Date.valueOf("2017-10-26"));
		customer.setUpdateDate(java.sql.Date.valueOf("2017-11-27"));
		Object testResult  = customerRepositoryImpl.insertCustomerDetail(customer);		assertNotNull(testResult);
		assertThat(customer).usingRecursiveComparison().isEqualTo(testResult);
		assertEquals(customer, testResult);
	}
	
	@Test
	@DisplayName("Test for Data Updation, expected OK")
	void updateDetailsTest() {
		CustomerDetails customer = new CustomerDetails();
		customer.setCustomername("Jitendra Mishra");
		customer.setMobile("7877887755");
		customer.setAddress("Hyderabad");
		customer.setCreatedDate(java.sql.Date.valueOf("2016-09-22"));
		customer.setUpdateDate(java.sql.Date.valueOf("2016-09-23"));
		CustomerDetails testResult = customerRepositoryImpl.updateDetail(2985, customer);
		assertThat(testResult).isEqualTo(customer);
//		assertThat(testResult).isEqualToComparingFieldByField(customer);
		assertThat(testResult).usingRecursiveComparison().isEqualTo(customer);
	}
	
	@Test
	@DisplayName("Test for Data deletion, expected OK")
	void deleteReordByIdTest() throws Exception {
//		String expected =  "Record deleted Successfully...";
//		String actual = customerRepositoryImpl.deleteRecordById(2555);
//		assertThat(expected.equals(actual));
		assertThat(customerRepositoryImpl.deleteRecordById(2642)).isEqualTo(null);
	}
	
	// error in this test case
	@Test
	@DisplayName("Test to fetch details by ID, expected OK")
	void getCustomerDetailsByIdTest() {
		Object[] newCustomer = customerRepositoryImpl.getAllCustomerDetailsById(23);
		assertThat(newCustomer.length).isGreaterThan(0);
//		assertThat(2905, newCustomer.getCustomerid());
	}
	
	@Test
	@DisplayName("Test to fetch details by ID, expected OK")
	void getAllCustomerDetailsTest() {
		List<Object[]> customers = customerRepositoryImpl.getAllCustomerDetails();
//		assertThat(customers.size()).isGreaterThan(0);
		assertNotNull(customers);
		assertThat(customers).hasSizeGreaterThan(0);
	}

	// Test cases with Mockito
	
	// Here we created a fake Repository by mock 
	CustomerRepositoryImpl customerRepositoryImpl2 = Mockito.mock(CustomerRepositoryImpl.class);
	
	
	@Test
	@DisplayName("Test insert Data with Mockito")
	void testInsertDataWithMock() {
		CustomerDetails customer = new CustomerDetails();
		customer.setCustomerid(2662);  
		customer.setCustomername("PARAM");
		customer.setMobile("8585887745");
		customer.setAddress("Kolkata");
		customer.setCreatedDate(java.sql.Date.valueOf("2017-10-26"));
		customer.setUpdateDate(java.sql.Date.valueOf("2017-11-27"));
		when(customerRepositoryImpl2.insertCustomerDetail(any(CustomerDetails.class))).thenReturn(customer);
		Object newCustomer = customerRepositoryImpl2.insertCustomerDetail(customer);
		assertEquals(newCustomer, customer);
	}

}
