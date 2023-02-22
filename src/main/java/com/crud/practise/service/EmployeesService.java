package com.crud.practise.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.crud.practise.model.EmailSender;
import com.crud.practise.model.Employees;
import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.Login;

public interface EmployeesService {
	
	public FinalResponse getAllEmployeesDetails();
    
    public FinalResponse getEmployeeDetailsById(int empId);
		
	public FinalResponse insertEmployeeDetail(Employees employees);
	
	public FinalResponse updateEmployeeDetail(int empId, Employees employees);
	
	public FinalResponse deleteEmployeeById(int customerid) throws Throwable;
	
	public FinalResponse userLoginByUsername(Login login);
	
	public FinalResponse userLoginByMobileNumber(String mobileNo, String password);
	
	public FinalResponse validatingToken(String token, Login login);

	public Employees loadUserByUsername(String username) throws UsernameNotFoundException;
	
	public FinalResponse generateEmail(EmailSender emailSender);
	
	public FinalResponse generateOtpForUserMobile(String username, String mobileno);
	
	public FinalResponse verifyUserByOtp(int otp, String mobileNumber, String toEmailId);
	

}
