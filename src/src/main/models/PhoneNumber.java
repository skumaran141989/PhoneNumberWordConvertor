package src.main.models;

import java.util.ArrayList;
import java.util.List;

import src.utils.Constants;

public class PhoneNumber {
	
	private List<Character> _phoneNumber;
	
	public PhoneNumber(String phoneNumber) throws Exception
	{
		_phoneNumber = new ArrayList<Character>();
		
		for (int i=0;i< phoneNumber.length(); i++)
		{
			int currentChar = phoneNumber.charAt(i);
			if(currentChar>=48 && currentChar<=57)
			{
				_phoneNumber.add(phoneNumber.charAt(i));
			}
			else if ((currentChar>=97 && currentChar<=122) || (currentChar>=65 && currentChar<=90))
			  throw new Exception(Constants.INVALID_PHONE_NUMBER);
		}
	}
	
	public char[] getDigitsArray()
	{
		char[] phoneNumber = new char [_phoneNumber.size()];
		for (int i=0;i< _phoneNumber.size(); i++)
			phoneNumber[i] = _phoneNumber.get(i);
		
	    return phoneNumber;
	}
}
