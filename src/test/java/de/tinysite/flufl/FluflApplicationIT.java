package de.tinysite.flufl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

class FluflApplicationTest {
	@Value("${testprocess-file}")
	String testfile = "";

	@Test
	void contextLoads() {
	}
	@Test
	public void main() {
		FluflApplication.main(new String[] {testfile});
	}

}
