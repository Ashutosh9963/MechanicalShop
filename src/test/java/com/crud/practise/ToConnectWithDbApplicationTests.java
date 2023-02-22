package com.crud.practise;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ToConnectWithDbApplicationTests {
	
	@Mock
	ToConnectWithDbApplication toConnectWithDbApplication;
	
	@Test
	void contextLoads() {
		Assertions.assertThat(toConnectWithDbApplication).isNotNull();
	}

}
