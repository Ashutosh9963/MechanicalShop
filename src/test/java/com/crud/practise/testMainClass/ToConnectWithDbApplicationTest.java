package com.crud.practise.testMainClass;

import org.junit.jupiter.api.Test; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crud.practise.ToConnectWithDbApplication;

@SpringBootTest
public class ToConnectWithDbApplicationTest {

	@Autowired
	ToConnectWithDbApplication toConnectWithDbApplication;
	
	@Test
	public void main() {
		ToConnectWithDbApplication.main(new String[] {});
	}
	
	@Test
	public void contextLoads() {
		
	}

}
