package com.crud.practise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.practise.model.EmailSender;
import com.crud.practise.model.Employees;
import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.Login;
import com.crud.practise.serviceImpl.EmailWithAttacmentService;
import com.crud.practise.serviceImpl.EmployeeServiceImpl;
import com.crud.practise.utility.EmailWithAttachment;

import jakarta.validation.Valid;

@RestController
public class EmployeesController {

	@Autowired
	EmployeeServiceImpl employeeService;
	
	@Autowired
	private EmailWithAttacmentService emailWithAttacmentService;
	
	
	@GetMapping("/getAllEmployeesDetails")
	public FinalResponse getAllCustomerDetails() {
		return employeeService.getAllEmployeesDetails();
	}

	@GetMapping("/getEmployeeDetailsById/{id}")
	public FinalResponse getEmployeeDetailsById(@PathVariable int id) {
		return employeeService.getEmployeeDetailsById(id);
	}

	@PostMapping(value = "/saveEmployeeDetails",  consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public FinalResponse saveEmployeeDetails(Employees employee) {
		return employeeService.insertEmployeeDetail(employee);
	}

	@PutMapping("/updateEmployeeDetailById/{id}")
	public FinalResponse updateEmployeeDetails(@PathVariable int id, @RequestBody @Valid Employees employee) {
		return employeeService.updateEmployeeDetail(id, employee);
	}

	@DeleteMapping("/deleteEmployeeDetailById/{id}")
	public FinalResponse deleteEmployeeDetail(@PathVariable int id) throws Throwable {
		return employeeService.deleteEmployeeById(id);
	}

	// here we need to pass the Authorization > APIKey > header with key value pair as Email and xyz@gmail.com in 
//	@PostMapping("/loginWithMail")
//	public FinalResponse employeeLoginWithMail(@RequestBody Employees employees, @RequestHeader("Email") String email) {
//		// to sent the email to the client 
//		emailSenderService.sendEmail(email, "Login Status", "Login Successfull");
//		return employeeService.userLogin(employees);
//	}
	
	@PostMapping("/login")
	public FinalResponse employeeLogin(@RequestBody  @Valid  Login login) {
//		emailSenderService.sendEmail(email, "Login Status", "Login Successfull");
		return employeeService.userLoginByUsername(login);
	}

	@GetMapping("/authenticate")
	public FinalResponse toVerifyToken(@RequestHeader("Authorization") String token, @RequestBody Login login) {
		return employeeService.validatingToken(token, login);
	}
	
	@PostMapping("/sendMail")
	public FinalResponse sendMyMail(@RequestBody @Valid  EmailSender emailSender) {
		return employeeService.generateEmail(emailSender);
	}
	
	@PostMapping("/sendMailWithAttachment")
	public String sendMailWithAttachment(@RequestBody EmailWithAttachment emailWithAttachment) {
		emailWithAttacmentService.sendEmailWithAttacment(emailWithAttachment.getToEmail(),
				emailWithAttachment.getSubject(), 
				emailWithAttachment.getBody(),
				emailWithAttachment.getAttachmentPath());
		return "Mail with attachment sent Successfully";
	}
	
	@GetMapping("/getOTPByMobileno")
	public FinalResponse getOTPByMobileno( @RequestParam ("username") String username, @RequestParam ("mobileno") String mobileno) {
		return employeeService.generateOtpForUserMobile(username, mobileno);
	}
	
	@GetMapping("/loginByMobileno")
	public FinalResponse loginByMobileno( @RequestParam ("mobileno") String mobileno, @RequestParam ("password") String password ) {
		return employeeService.userLoginByMobileNumber(mobileno ,password);
	}
	
	@GetMapping("/verifyUserByOtp")
	public FinalResponse verifyUserByOtp(@RequestParam("otp") int otp,
            @RequestParam("mobileNumber") String mobileNumber, @RequestParam("toEmailId") String toEmailId) {
		return employeeService.verifyUserByOtp(otp, mobileNumber, toEmailId);
	}
	
	
	
	
}
