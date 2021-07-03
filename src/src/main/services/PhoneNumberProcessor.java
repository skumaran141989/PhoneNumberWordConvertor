package src.main.services;

import java.util.HashMap;
import java.util.List;

public interface PhoneNumberProcessor {
     
	public HashMap<String, List<String>> processPhoneNumbersFromFile(PhoneNumberToWordConvertor phoneNumberToWordConvertor) throws Exception;
}
