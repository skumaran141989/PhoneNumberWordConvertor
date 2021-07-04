package src.main.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import src.utils.Constants;

public class PhoneNumberInputProcessorFromFile extends PhoneNumberInputProcessor{
	
	@Override
	public void processPhoneNumbersFromSource(Object source) throws Exception
	{
		String fileName = (String) source;
		try (Scanner scanner = new Scanner(new File(fileName)))
		   {
			   while (scanner.hasNextLine()) {
				  String phoneNumber = scanner.nextLine();
				  addNumber(phoneNumber);
			   }
		   } 
		catch(FileNotFoundException exception)
		{
			throw new Exception(Constants.MISSING_INPUT_FILE);
		}
	}
}
