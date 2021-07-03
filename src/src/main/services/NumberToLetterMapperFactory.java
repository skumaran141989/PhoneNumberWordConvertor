package src.main.services;

public class NumberToLetterMapperFactory {


	public static NumberToLetterMapper getNumberToLetterMapperFactory(String option)
	{
		 switch(option)
		 {
		    case "PhonePad":
		      return new PhonePadNumberToLetterMapper();
		      
		    default:
		    	return new PhonePadNumberToLetterMapper();
		 }
	}
}
