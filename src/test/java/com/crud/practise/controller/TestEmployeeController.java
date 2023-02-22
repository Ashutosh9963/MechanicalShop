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

import com.crud.practise.model.Employees;
import com.crud.practise.model.FinalResponse;
import com.crud.practise.serviceImpl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Case for Employee Controller")
class TestEmployeeController {
	
	@InjectMocks
	private EmployeesController employeesController;
	
	@Mock
	private EmployeeServiceImpl employeeServiceImpl;
	
	@Test
	@DisplayName("Test getAllEmployeesDetails : expected OK")
	void testGetAllEmployeesDetails() {
		
		Object[] employee1 =  {
				"empId", 1,
				"employeeName" , "SANJAY",
				"mobile" , "9878458745",
			    "password" , "SANJU123",
				"address" , "MATHURA",
		};
		Object[] employee2 =  {
				"empId", 1,
				"employeeName" , "SANJAY",
				"mobile" , "9878458745",
			    "password" , "SANJU123",
				"address" , "MATHURA",
		};
		List<Object[]> list = new ArrayList<>();
		list.add(employee1);
		list.add(employee2);
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("200");
		expected.setMessage("Employees list fetched successfully ");
		expected.setData(list);
		when(employeeServiceImpl.getAllEmployeesDetails()).thenReturn(expected);
		FinalResponse actual = employeesController.getAllCustomerDetails();
		assertEquals(expected, actual);
	}
	
	@Test
	@DisplayName("Test getEmployeeDetailsById : expected OK")
	void testGetEmployeeDetailsById() {
		Object[] employee1 =  {
				"empId", 1,
				"employeeName" , "SANJAY",
				"mobile" , "9878458745",
			    "password" , "SANJU123",
				"address" , "MATHURA",
		};
		FinalResponse expected  = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("200");
		expected.setMessage("Records Available");
		expected.setData(employee1);
		when(employeeServiceImpl.getEmployeeDetailsById(1)).thenReturn(expected);
		FinalResponse actual = employeesController.getEmployeeDetailsById(1);
		assertThat(expected).isEqualTo(actual);
	}
	
	@Test
	@DisplayName("Test saveEmployeeDetails : expected OK")
	void testSaveEmployeeDetails() {
		Employees employees = new Employees(1, "Krishna", "9585654585", "sales", "krishna100", "krish07", java.sql.Date.valueOf("2021-05-21"), java.sql.Date.valueOf("2021-05-21"), java.sql.Date.valueOf("2021-05-21"));
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("201");
		expected.setMessage("Record is inserted");
		expected.setData(employees);
		when(employeeServiceImpl.insertEmployeeDetail(any(Employees.class))).thenReturn(expected);
		FinalResponse actual = employeesController.saveEmployeeDetails(employees);
		assertThat(expected).isEqualTo(actual);
	}
	
	@Test
	@DisplayName("Test updateEmployeeDetailById : expected OK")
	void testUpdateEmployeeDetailById() {
		Employees employees = new Employees(1, "Krishna", "9585654585", "sales", "krishna100", "krish07", java.sql.Date.valueOf("2021-05-21"), java.sql.Date.valueOf("2021-05-21"), java.sql.Date.valueOf("2021-05-21"));
		FinalResponse expected = new FinalResponse();
		expected.setStatus(true);
		expected.setStatusCode("200");
		expected.setMessage("Record Updated Successfully");
		expected.setData(employees);
		when(employeeServiceImpl.updateEmployeeDetail(anyInt(), any(Employees.class))).thenReturn(expected);
		FinalResponse actual = employeesController.updateEmployeeDetails(1, employees);
		assertThat(expected).isEqualTo(actual);
	}
	
//	@Test
//	@DisplayName("Test deleteEmployeeDetailById : expected OK")
//	void testDeleteEmployeeDetailById() {
//		Employees employees = new Employees(1, "Krishna", "9585654585", "sales", "krishna100", "krish07", java.sql.Date.valueOf("2021-05-21"), java.sql.Date.valueOf("2021-05-21"), java.sql.Date.valueOf("2021-05-21"));
//		
//	}
	
	

	

}
