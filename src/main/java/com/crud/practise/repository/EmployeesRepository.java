package com.crud.practise.repository;

import java.util.Date;
import java.util.List;

import com.crud.practise.model.Employees;

public interface EmployeesRepository {
	
	public List<Object[]> getAllEmployeesDetails();
	
	public Object[] getEmployeeDetailsById(int id);
		
	public Employees insertEmployeeDetail(Employees employee);
	
	public Employees updateEmployeeDetail(int empId, Employees employee);
	
	public Employees deleteEmployeeById(int empId) throws Exception;
	
	public Object loginEmployee(String employeeName, String password);
	
	public Object loginEmployeeByMobile(String mobileno, String password);
	
	public List<String> checkUsersName();
	
	public List<String> checkUsersPassword();
	
	public List<String> getAllMobileno();
	
	public String updateOtp(String otp, Date otpDate, String mobileNo);
	
	public String toCheckUserByMobileno(String mobileNo);
	
	public int toCheckUserOtp(String mobileNo);
	
	public String verficationOtp(int otp, String mobileNumber);
	
	public Date toGetDBOtpTime(String mobileNo);

}
