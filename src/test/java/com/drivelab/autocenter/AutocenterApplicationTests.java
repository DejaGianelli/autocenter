package com.drivelab.autocenter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class AutocenterApplicationTests {

	@Test
	void contextLoads() {
	}

}
