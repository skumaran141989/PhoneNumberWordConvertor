package src.main.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import src.main.models.PhoneNumber;
import src.utils.Constants;

public class PhoneNumberProcessorFromFile implements PhoneNumberInputProcessor{

	private String _fileName;
	
	public PhoneNumberProcessorFromFile(String inputFileName)
	{
		_fileName = inputFileName;
		
	}
	
	@Override
	public HashMap<String, Set<String>> processPhoneNumbersFromFile(PhoneNumberToWordConvertor phoneNumberToWordConvertor) throws Exception
	{
		HashMap<String, Set<String>> wordsForPhoneNumbers = new HashMap<String, Set<String>>();
		try (Scanner scanner = new Scanner(new File(_fileName)))
		   {
			   while (scanner.hasNextLine()) {
				  String phoneNumber = scanner.nextLine();
				  wordsForPhoneNumbers.put(phoneNumber, phoneNumberToWordConvertor.getConvertedWords(new PhoneNumber(phoneNumber)));
			   }
		   } 
		catch(FileNotFoundException exception)
		{
			throw new Exception(Constants.MISSING_INPUT_FILE);
		}
		
		return wordsForPhoneNumbers;
	}
}
