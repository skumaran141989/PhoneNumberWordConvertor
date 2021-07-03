package src.main.models;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumber {
	
	private List<Character> _phoneNumber;
	
	public PhoneNumber(String phoneNumber)
	{
		_phoneNumber = new ArrayList<Character>();
		
		for (int i=0;i< phoneNumber.length(); i++)
		{
			int currentChar = phoneNumber.charAt(i);
			if((48-currentChar) >= 0 && 48-currentChar <=9)
			{
				_phoneNumber.add(phoneNumber.charAt(i));
			}
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
