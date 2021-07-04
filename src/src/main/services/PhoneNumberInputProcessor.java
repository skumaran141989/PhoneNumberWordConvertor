package src.main.services;

import java.util.HashMap;
import java.util.Set;

public interface PhoneNumberInputProcessor {
     
	public HashMap<String, Set<String>> processPhoneNumbersFromFile(PhoneNumberToWordConvertor phoneNumberToWordConvertor) throws Exception;
}
