package com.crud.practise.repositoryImpl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import com.crud.practise.model.Employees;
import com.crud.practise.model.Login;
import com.crud.practise.repository.EmployeesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class EmployeeRepositoryImpl implements EmployeesRepository {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Object[]> getAllEmployeesDetails() {
		Query query = entityManager.createNativeQuery("SELECT * FROM EMPLOYEES");
		List<Object[]> employeeList = null;
		try {
			employeeList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public Object[] getEmployeeDetailsById(int empId) {
		Query query = entityManager.createNativeQuery("SELECT * FROM EMPLOYEES WHERE EMPID = ? ");
		Object[]  employee=null;
		query.setParameter(1, empId);
		try {
			  employee = (Object[]) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	//-------------- Method to Save the Employee Details---------------- 
	@Override
	@Transactional
	public Employees insertEmployeeDetail(Employees employees) {
		Query query = entityManager.createNativeQuery("INSERT INTO EMPLOYEES (EMPLOYEENAME, MOBILE, DESIGNATION, USERNAME, PASSWORD, HIREDATE, CREATED_DATE, PROFILE_IMAGE) VALUES (?,?,?,?,?,?,?,?)");
		query.setParameter(1, employees.getEmployeeName());
		query.setParameter(2, employees.getMobile());
		query.setParameter(3, employees.getDesignation());
		query.setParameter(4, employees.getUsername());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = employees.getPassword();
		String encryptPass = passwordEncoder.encode(password);
		query.setParameter(5, encryptPass);
		Date date = new Date();
		query.setParameter(6, date);
		query.setParameter(7, date);
		try {
			byte[] byteImage = employees.getProfileImage().getBytes();
			try {
				Blob blobImage = new SerialBlob(byteImage);
				query.setParameter(8, blobImage);
			} catch (SerialException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		int recordInserted = 0;
		try {
			recordInserted = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (recordInserted == 1) {
			return employees;
		}
		else return null;
 	}
	

	@Override
	@Transactional
	public Employees updateEmployeeDetail(int empId, Employees employee) {
		Query query = entityManager.createNativeQuery("update employees set employeename=?, mobile=?, designation=?, username=?, password=?, updated_date=? where empid = ?");
		query.setParameter(1, employee.getEmployeeName());
		query.setParameter(2, employee.getMobile());
		query.setParameter(3, employee.getDesignation());
		query.setParameter(4, employee.getUsername());
		query.setParameter(5, employee.getPassword());
		Date date = new Date();
		query.setParameter(6, date);
		query.setParameter(7, empId);
		int recordUpdated = 0;
		try {
			recordUpdated =  query.executeUpdate();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		if (recordUpdated != 0) {
			return employee;
		} else 
			return null;
	}

	@Override
	@Transactional
	public Employees deleteEmployeeById(int empId) throws Exception {
		Query query = entityManager.createNativeQuery("DELETE FROM EMPLOYEES WHERE EMPID = ? ");
		query.setParameter(1, empId);
		int deleteRecord = 0; 
		try {
			deleteRecord = query.executeUpdate();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		if (deleteRecord > 0) {
			return null;
		}
		else
			throw new Exception("Employee not found the given ID");
	}

	@Override
	public Object loginEmployee(String employeeName, String password) {
		Query query = entityManager.createNativeQuery("SELECT * FROM EMPLOYEES WHERE USERNAME = ? AND PASSWORD = ? ");
		query.setParameter(1, employeeName);
		query.setParameter(2, password);
		Object loginData = null;
		try {
			loginData =  query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return loginData;
	}

	@Override
	public List<String> checkUsersName() {
		Query query = entityManager.createNativeQuery("SELECT USERNAME FROM EMPLOYEES");
		List<String> userList = null;
		try {
			userList = query.getResultList();
		} catch (NoResultException e) {
			e.getMessage();
		}
		return userList;
	}

	@Override
	public List<String> checkUsersPassword() {
		Query query = entityManager.createNativeQuery("SELECT PASSWORD FROM EMPLOYEES");
		List<String> passwordList = null;
		try {
			passwordList = query.getResultList();
		} catch (NoResultException e) {
			e.getMessage();
		}
		return passwordList;
	}
	
	@Override
	public List<String> getAllMobileno() {
		Query query = entityManager.createNativeQuery("SELECT MOBILE FROM EMPLOYEES");
		List<String> mobileNos= null;
		try {
			mobileNos = query.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return mobileNos;
	}

	@Override
	@Transactional
	public String updateOtp(String otp, Date otpDate,  String mobileNo) {
		Query query = entityManager.createNativeQuery("UPDATE EMPLOYEES SET RECOVERY_OTP = ?, OTP_REQUESTED_TIME = ? WHERE MOBILE = ?");
		query.setParameter(1, otp);
		query.setParameter(2, otpDate);
		query.setParameter(3, mobileNo);
		int result = 0;
		try {
			result =  query.executeUpdate();
		} catch (NoResultException e) {
			e.getMessage();
		}
		if (result == 1) {
			return "Otp updated Successfully";
		}
		return "not updated";
	}

	@Override
	public String toCheckUserByMobileno(String mobileNo) {
		Query query = entityManager.createNativeQuery("SELECT PASSWORD FROM EMPLOYEES WHERE MOBILE = ?");
		query.setParameter(1, mobileNo);
		String password = null;
		try {
			password = (String) query.getSingleResult();
			} catch (NoResultException e) {
				e.printStackTrace();
			}
		return password;
	}

	@Override
	public Object loginEmployeeByMobile(String mobileno, String password) {
		Query query = entityManager.createNativeQuery("SELECT * FROM EMPLOYEES WHERE MOBILE = ? AND PASSWORD = ?");
		query.setParameter(1, mobileno);
		query.setParameter(2, password);
		Object employee = null;
		try {
			employee = query.getSingleResult();
		} catch (NoResultException e) {
			e.getMessage();
		}
		return employee;
	}

	@Override
	public int toCheckUserOtp(String mobileNo) {
		Query query = entityManager.createNativeQuery("SELECT RECOVERY_OTP FROM EMPLOYEES WHERE MOBILE = ?");
		query.setParameter(1, mobileNo);
		int otp = 0;
		try {
			otp = (int) query.getSingleResult();
		} catch (InputMismatchException e) {
			e.getMessage();
		}
		return otp;
	}
	
	@Override
	public Date toGetDBOtpTime(String mobileNo) {
		Query query = entityManager.createNativeQuery("SELECT OTP_REQUESTED_TIME FROM EMPLOYEES WHERE MOBILE = ?");
		query.setParameter(1, mobileNo);
		Date otp = null;
		try {
			otp = (Date) query.getSingleResult();
		} catch (InputMismatchException e) {
			e.getMessage();
		}
		return otp;
	}
	
	 public String verficationOtp(int otp, String mobileNumber) {
	        Query query = entityManager.createNativeQuery("select * from employees where otp=? and mobile=?");
	        query.setParameter(1, otp);
	        query.setParameter(2, mobileNumber);
	        try {
	            query.getSingleResult();
	        } catch (Exception ex) {
	            System.out.println(ex);
	        }
	        return "verification is done";
	    }

	

	

	
}
