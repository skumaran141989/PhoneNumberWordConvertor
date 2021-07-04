package src.test.services;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import src.main.services.PhoneNumberInputProcessor;
import src.main.services.PhoneNumberInputProcessorFromFile;
import src.utils.Constants;

class PhoneNumberInputProcessorTest {

	private PhoneNumberInputProcessor _phoneNumberInputProcessor;
	private String _inputFile;
	
	public PhoneNumberInputProcessorTest() throws IOException
	{
		_phoneNumberInputProcessor = new PhoneNumberInputProcessorFromFile();
		
		String projectPath = new File(".").getCanonicalPath();
		
		_inputFile = projectPath+"/src/src/test/resources/Input.txt";
	}
	
	@Test
	void loadNotExistingInputFile() {
		 Exception exception = assertThrows(Exception.class, () -> {
			 _phoneNumberInputProcessor.processPhoneNumbersFromSource(_inputFile+"2");
		    });
		 
		 assertEquals(Constants.MISSING_INPUT_FILE, exception.getMessage());
	}
	
	@Test
	void loadExistingInputFile() throws Exception {
		assertDoesNotThrow(()->{_phoneNumberInputProcessor.processPhoneNumbersFromSource(_inputFile);});
	}
	
	@Test
    void ValidateWithValues() throws Exception {
		_phoneNumberInputProcessor.processPhoneNumbersFromSource(_inputFile);
		Assert.assertEquals(3, _phoneNumberInputProcessor.getPhonenNumbers().size());
	}

}
