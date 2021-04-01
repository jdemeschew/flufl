package de.tinysite.flufl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FluflApplicationIT {
	private final String TEST_FILE = "src/test/resources/flufl-it.txt";
	private final String TEST_IMAGE_FILE="flufl.jpg";
	@BeforeEach
	public  void prepare(){
		deleteTestImage();

	}@AfterEach
	public void cleanUp(){
deleteTestImage();
	}
	@Test
	public void testFluflApplication() {
		FluflApplication.main(new String[] {TEST_FILE});
		assertTrue(new File(TEST_IMAGE_FILE).exists());



	}

	private void deleteTestImage(){
		File testImage =new File(TEST_IMAGE_FILE);
		testImage.delete();
	}

}
