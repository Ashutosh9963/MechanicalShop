package com.crud.practise.controller;
//package com.crud.practise.testController;
//
//import static org.hamcrest.CoreMatchers.is;    
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.crud.practise.controller.CustomerDetailsController;
//import com.crud.practise.model.CustomerDetails;
//import com.crud.practise.serviceImpl.CustomerDetailsServiceImpl;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@WebMvcTest(CustomerDetailsController.class)
//
//// test that focuses only on Spring MVC components.
//// apply only configuration relevant to MVC tests
//// main enrty point of Spring serverside mvc test
//// Typically @WebMvcTest is used in combination with 
//// @MockBean or @Import to create any collaborators required by your @Controller beans.
//
//public class CustomerDetailsControllerTest {
//	
//	@MockBean // Smiler to mock annotation
//	// if our test case is dependent on spring container bean then use @MockBean 
//	private CustomerDetailsServiceImpl customerDetailsServiceImpl;
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@Autowired
//	private ObjectMapper objectMapper;
//	// to serialize the responses and deserialize the requests
//	
//	private CustomerDetails customer;
//	private CustomerDetails customer1;
//	
//	@BeforeEach
//	void init() {
//		customer = new CustomerDetails();
//		customer.setCustomerid(1);  
//		customer.setCustomername("PARAM");
//		customer.setMobile("8585887745");
//		customer.setAddress("Kolkata");
//		customer.setCreatedDate(java.sql.Date.valueOf("2017-10-26"));
//		customer.setUpdateDate(java.sql.Date.valueOf("2017-10-27"));
//		
//		customer1 = new CustomerDetails();
//		customer1.setCustomerid(2);  
//		customer1.setCustomername("DINESH");
//		customer1.setMobile("8585887745");
//		customer1.setAddress("Kolkata");
//		customer1.setCreatedDate(java.sql.Date.valueOf("2017-10-26"));
//		customer1.setUpdateDate(java.sql.Date.valueOf("2017-10-26"));
//	}
//	
//	@Test
//	@DisplayName("To test the insert Data Controller")
//	void testInsertData() throws Exception { 
//		
//		when(customerDetailsServiceImpl.insertCustomerDetail(any(CustomerDetails.class))).thenReturn(customer);
//		
//		this.mockMvc.perform(post("/insertCustomerDetail")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(customer)))
//		.andExpect(status().isCreated())
//		
//		.andExpect(jsonPath("$.customerid", is(customer.getCustomerid())))
//		.andExpect(jsonPath("$.customername", is(customer.getCustomername())))
//		.andExpect(jsonPath("$.mobile", is(customer.getMobile())))
//		.andExpect(jsonPath("$.address", is(customer.getAddress())))
//		.andExpect(jsonPath("$.createdDate", is(customer.getCreatedDate().toString())))
//		.andExpect(jsonPath("$.updateDate", is(customer.getUpdateDate().toString())));
//	}
//	
//	@Test
//	@DisplayName("Test to fetch all the Details")
//	void testgetAllCustomerDetails() throws Exception {
//		
//		List<CustomerDetails> list = new ArrayList<>();
//		list.add(customer);
//		list.add(customer1);
//		
//		when(customerDetailsServiceImpl.getAllCustomerDetails()).thenReturn(list);
//		
//		this.mockMvc.perform(get("/getAllCustomerDetails"))
//		.andExpect(status().isOk())
//		.andExpect(jsonPath("$.size()", is(list.size())));
//	}
//	
//	@Test
//	@DisplayName("Test getDetailsById Api")
//	void testgetDetailsById() throws Exception {
//		
//		Object[] obj =  {
//				"customerid", "1",
//			    "customername" , "RAGHUPATI",
//			    "mobile" , "9878458745",
//			    "address" , "HARYANA",
//			    "createdDate" , "2016-08-20",
//			    "updateDate" , "2016-08-21"
//		};
//		
//		when(customerDetailsServiceImpl.getAllCustomerDetailsById(anyInt())).thenReturn(obj);
//		this.mockMvc.perform(get("/getDetailsById/{id}", 1))
//			.andExpect(status().isOk());
////			.andExpect(jsonPath("$.customername", is(customer.getCustomername())));
//	}
//	
//	@Test
//	@DisplayName("Test to ccheck the deleteById Api")
//	void testDeleteRecordById() throws Throwable {
//
//		when(customerDetailsServiceImpl.deleteRecordById(anyInt())).thenReturn(null);
//		
////		doNothing().when(customerDetailsServiceImpl.deleteRecordById(anyInt()));
//		
//		this.mockMvc.perform(delete("/deleteRecordById/{id}", 1))
//			.andExpect(status().isNoContent());
//	}
//	
//	@Test
//	@DisplayName("Test the UpdateById Api")
//	void testUpdateCustomerDetailsById() throws JsonProcessingException, Exception {
//		
//		when(customerDetailsServiceImpl.updateDetail(1, customer)).thenReturn(customer);
//		this.mockMvc.perform(put("/updateCustomerDetailsById/{id}", 1)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(customer)))
//				.andExpect(jsonPath("$.customername", is(customer.getCustomername())))
//				.andExpect(jsonPath("$.mobile", is(customer.getMobile())))
//				.andExpect(jsonPath("$.address", is(customer.getAddress())))
//				.andExpect(jsonPath("$.createdDate", is(customer.getCreatedDate().toString())))
//				.andExpect(jsonPath("$.updateDate", is(customer.getUpdateDate().toString())));
//	}
//}
