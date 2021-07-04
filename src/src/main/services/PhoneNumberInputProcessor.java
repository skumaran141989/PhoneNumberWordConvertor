package src.main.services;

import java.util.ArrayList;
import java.util.List;

import src.main.models.PhoneNumber;

public abstract class PhoneNumberInputProcessor {
     
	private List<PhoneNumber> _phoneNumbers;
	
	public abstract void processPhoneNumbersFromSource(Object source) throws Exception;
	
	protected void addNumber(String phoneNumber) throws Exception
	{
		if (_phoneNumbers==null)
			_phoneNumbers = new ArrayList<PhoneNumber>();
		
		_phoneNumbers.add(new PhoneNumber(phoneNumber));
	}
	
	public List<PhoneNumber> getPhonenNumbers()
	{
		return _phoneNumbers!=null? _phoneNumbers: new ArrayList<PhoneNumber>();
	}
}
