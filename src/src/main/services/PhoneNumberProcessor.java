package src.main.services;

import java.util.HashMap;
import java.util.Set;

public interface PhoneNumberProcessor {
     
	public HashMap<String, Set<String>> processPhoneNumbersFromFile(PhoneNumberToWordConvertor phoneNumberToWordConvertor) throws Exception;
}
