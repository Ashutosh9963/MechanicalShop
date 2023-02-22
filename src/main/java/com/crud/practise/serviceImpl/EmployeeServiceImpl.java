package com.crud.practise.serviceImpl;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crud.practise.config.JwtTokenUtil;
import com.crud.practise.model.EmailSender;
import com.crud.practise.model.Employees;
import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.Login;
import com.crud.practise.repository.EmployeesRepository;
import com.crud.practise.service.EmployeesService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Service
public class EmployeeServiceImpl implements EmployeesService {

	@Autowired
	private EmployeesRepository repository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private EmailSenderService emailSenderService; 
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private static final String TWILIO_ACCOUNT_SID = "AC14cee21341f7ca9ae9d0b7bbd8cabe73";
	private static final String TWILIO_AUTH_TOKEN = "253cbddf640d81ced96603324220948c";
	
	private static final long OTP_VALID_DURATION = 1 * 60 * 1000;  // 1 miniute

	Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Override
	public FinalResponse getAllEmployeesDetails() {
		logger.info("Fetching all the Employees Details");
		FinalResponse finalResponse = new FinalResponse();
		Object employeeList = null;
		try {
			employeeList = repository.getAllEmployeesDetails();
		} catch (Exception e) {
			logger.error("Cannot fetch the EmployeesDetails List" + e.getMessage());
		}
		if (employeeList != null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Employees list fetched successfully ");
			finalResponse.setData(employeeList);
			return finalResponse;
		} else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record not available to display");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse getEmployeeDetailsById(int empId) {
		logger.info("Fetching the EmployeeDetails by Id: " + empId);
		FinalResponse finalResponse = new FinalResponse();
		Object employeeDetail = null;
		try {
			employeeDetail = repository.getEmployeeDetailsById(empId);
		} catch (InputMismatchException e) {
			e.printStackTrace();
			logger.error("Cannot fetch the employeeDetails by Id " + empId + e.getMessage());
		}
		if (employeeDetail != null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Records Available for ID" + empId);
			finalResponse.setData(employeeDetail);
			return finalResponse;
		} else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setMessage("Records not Available");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse insertEmployeeDetail(Employees employees) {
		logger.info("Inserting one Recored::input::employeeDetails: " + employees);
		Object newRecord = repository.insertEmployeeDetail(employees);
		FinalResponse finalResponse = new FinalResponse();
		if (newRecord != null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("201");
			finalResponse.setMessage("Record is inserted");
//			finalResponse.setData(newRecord);
			return finalResponse;
		} else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not inserted");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse updateEmployeeDetail(int empId, Employees employees) {
		logger.info("Updating EmployeeDetails by Id: " + empId);
		FinalResponse finalResponse = new FinalResponse();
		Employees updatedDetail = null;
		try {
			updatedDetail = repository.updateEmployeeDetail(empId, employees);
		} catch (InputMismatchException e) {
			e.printStackTrace();
			logger.error("Cannot Updated the EmployeeDetails for Id:" + empId);
		}
		if (updatedDetail != null) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("200");
			finalResponse.setMessage("Record Updated Successfully");
			finalResponse.setData(updatedDetail);
			return finalResponse;
		} else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not Updated");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse deleteEmployeeById(int empId) throws Throwable {
		logger.info("Deleting EmployeeDetails by Id: " + empId);
		FinalResponse finalResponse = new FinalResponse();
		try {
			repository.deleteEmployeeById(empId);
		} catch (InputMismatchException e) {
			logger.error("Cannot deleted the EmployeeDetail by ID" + empId + e.getMessage());
		}
		if (empId > 0) {
			finalResponse.setStatus(true);
			finalResponse.setStatusCode("204");
			finalResponse.setMessage("Record is deleted");
			return finalResponse;
		} else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("501");
			finalResponse.setErrorMessage("Record is not deleted");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse userLoginByUsername(Login login) {
		logger.info("Login::input:: user:" + login);
		FinalResponse finalResponse = new FinalResponse();
		String user = null;
		String password = null;
		List<String> userList = repository.checkUsersName();
		for (int i = 0; i < userList.size(); i++) {
			if ((login.getUsername()).equals(userList.get(i))) {
				user = userList.get(i);
			}
		}
		List<String> passwordList = repository.checkUsersPassword();
		for (int i = 0; i < passwordList.size(); i++) {
			if (passwordList.get(i).equals(login.getPassword())) {
				password = passwordList.get(i);
			}
		}
		if (login.getUsername().equals(user)) {
			if (login.getPassword().equals(password)) {
					finalResponse.setStatus(true);
					finalResponse.setStatusCode("200");
					finalResponse.setMessage("User Login Successfull");
					Object token = jwtTokenUtil.generateToken(login);
					finalResponse.setData(token);
					return finalResponse;
			} else {
				finalResponse.setStatus(false);
				finalResponse.setStatusCode("404");
				finalResponse.setErrorMessage("Password is incorrect!. Please click on the below link for OTP");
				return finalResponse;
			}
		} else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("404");
			finalResponse.setErrorMessage("User does not exist!");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse userLoginByMobileNumber(String mobileNo, String password) {
		logger.info("userLoginByMobile:: " + mobileNo + " : " + password);
		FinalResponse finalResponse = new FinalResponse();
		String userPassword = repository.toCheckUserByMobileno(mobileNo);
		List<String> mobilesList = repository.getAllMobileno();
		for (int i = 0; i < mobilesList.size(); i++) {
			if (mobilesList.get(i).equals(mobileNo)) {
				if (userPassword != null) {
					if (userPassword.equals(password)) {
						try {
//							Object employee = repository.loginEmployeeByMobile(mobileNo, userPassword);
							finalResponse.setStatus(true);
							finalResponse.setStatusCode("200");
							finalResponse.setMessage("Login is Successfully");
							return finalResponse;
						} catch (InputMismatchException e) {
							logger.error("userLoginByMobileNumber:: " + e.getMessage());
						}
					} else {
						finalResponse.setStatus(false);
						finalResponse.setStatusCode("404");
						finalResponse.setErrorMessage("Password is incorrect!. Please click on the below link for OTP");
						return finalResponse;
					}
				} else {
					finalResponse.setErrorMessage("User doesn't exist with the this Mobile number");
				}
			} else {

				finalResponse.setStatus(false);
				finalResponse.setStatusCode("404");
				finalResponse.setErrorMessage("This Mobile is not registered");
				return finalResponse;
			}
		}
		return null;
	}
	
	
	@Override
	public FinalResponse generateOtpForUserMobile(String username, String mobileno) {
		FinalResponse finalResponse = new FinalResponse();
//		List<String> mobiles = repository.getAllMobileno();
		String user = null;
		String mobile = null;
		List<String> userList = repository.checkUsersName();
		for (int i = 0; i < userList.size(); i++) {
			if (username.equals(userList.get(i))) {
				user = userList.get(i);
			}
		}
		List<String> mobilesList = repository.getAllMobileno();
		for (int i = 0; i < mobilesList.size(); i++) {
			if (mobilesList.get(i).equals(mobileno)) {
				mobile = mobilesList.get(i);
			}
		}
		if (username.equals(user)) {
			if (mobileno.equals(mobile)) {
				int randomPin = (int) ((Math.random() * 900000) + 100000);
				String otpNum = String.valueOf(randomPin);
				String otpMessage = "OTP to verIfy the User Name is " + otpNum;
				Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
				Message.creator(new PhoneNumber("+919963190891"), new PhoneNumber("+12183221445"), otpMessage).create();
				repository.updateOtp(otpNum, new Date(), mobileno);
				finalResponse.setStatus(true);
				finalResponse.setStatusCode("200");
				finalResponse.setMessage("User verified and OTP forwarded to registered Mobile No");
				return finalResponse;
			} else {
				finalResponse.setStatus(false);
				finalResponse.setStatusCode("404");
				finalResponse.setErrorMessage("This Mobileno is not registered with the given User name");
				return finalResponse;
			}
		}else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("404");
			finalResponse.setErrorMessage("User does not exist!");
			return finalResponse;
		}
	}

	@Override
	public FinalResponse validatingToken(String token, Login login) {
		FinalResponse finalResponse = new FinalResponse();
		String userToken = token.substring(7, token.length());
		if (userToken != null) {
			try {
				boolean value = jwtTokenUtil.validateToken(userToken, login);
				System.out.println(userToken);
				if (value == true) {
					finalResponse.setStatus(true);
					finalResponse.setStatusCode("200");
					finalResponse.setMessage("Token is valid");
					return finalResponse;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		finalResponse.setStatus(false);
		finalResponse.setStatusCode("500");
		finalResponse.setMessage("Token is Invalid");
		return finalResponse;
	}

//	@Override
//	public Employees loadUserByUsername(String username) throws UsernameNotFoundException {
//		List<Object> userList = repository.checkUsersName();
//		Employees employees = null;
//		for (int i = 0; i < userList.size(); i++) {
//			if (userList.get(i).equals(username)) {
//				employees = new Employees();
//			} else {
//				throw new UsernameNotFoundException("User not found with username: " + username);
//			}
//		}
//		return employees;
//	}

	@Override
	public FinalResponse generateEmail(EmailSender emailSender) {
		FinalResponse finalResponse = new FinalResponse();
		String toEmail = emailSender.getToEmail();
		String emailSubject = emailSender.getSubject();
		String emailBody = emailSender.getBody();
		if (!(toEmail == null && emailSubject == null && emailBody == null)) {
			Boolean status = emailSenderService.sendEmail(toEmail, emailSubject, emailBody);
			if (status) {
				finalResponse.setStatus(true);
				finalResponse.setStatusCode("200");
				finalResponse.setMessage("Mail sent successfuly");
				return finalResponse;
			}
		}
		finalResponse.setStatus(false);
		finalResponse.setStatusCode("501");
		finalResponse.setErrorMessage("Unable to send mail");
		return finalResponse;
	}

	@Override
	public Employees loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FinalResponse verifyUserByOtp(int otp, String mobileNumber, String toEmailId) {
		FinalResponse finalResponse = new FinalResponse();
		int userOtp = repository.toCheckUserOtp(mobileNumber);
		if (userOtp != 0) {
			long currentTimeinMillis = System.currentTimeMillis();
			Date dbOtpTime = repository.toGetDBOtpTime(mobileNumber);
			long time = dbOtpTime.getTime();
			if (time + OTP_VALID_DURATION > currentTimeinMillis) {
				if (userOtp == otp) {
					SimpleMailMessage message = new SimpleMailMessage();
					message.setFrom("ashutosh.at46@gmail.com");
					message.setTo(toEmailId);
					message.setSubject("verfication");
					message.setText("OTP verified successfully");
					javaMailSender.send(message);
					repository.verficationOtp(otp, mobileNumber);
					finalResponse.setStatus(true);
					finalResponse.setStatusCode("200");
					finalResponse.setMessage(" OTP Verified Successfully");
					return finalResponse;
				} else {
					finalResponse.setStatus(false);
					finalResponse.setStatusCode("500");
					finalResponse.setMessage(" Otp not Matched!!!");
					return finalResponse;
				}
			} else {
				finalResponse.setStatus(false);
				finalResponse.setStatusCode("404");
				finalResponse.setMessage("OTP verify duration has Expired!");
			}

		} else {
			finalResponse.setStatus(false);
			finalResponse.setStatusCode("404");
			finalResponse.setMessage("user does not exits with this mobileNumber");
			return finalResponse;
		}
		return finalResponse;
	}
	



	
}