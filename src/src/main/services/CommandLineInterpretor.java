package src.main.services;

import java.util.HashMap;

import src.utils.Constants;

public class CommandLineInterpretor {

	private HashMap<String, String> optionValueMap;

	public CommandLineInterpretor()
	{
		optionValueMap = new HashMap<String, String>();
	}
	
	public void setArguements(String[] args) throws Exception
	{
		int i = 0;
		
		while(i < args.length)
		{
		 if(args[i].startsWith("-"))
			 optionValueMap.put(args[i], args[++i]);
		 else
			 throw new Exception(Constants.MISSING_CLI_OPTION);
		 i++;
		}
			 
	}
	
	public String getStringValue(String option)
	{
		return optionValueMap.get(option);
	}
	
	public int getIntegerValue(String option)
	{
		return Integer.parseInt(optionValueMap.get(option));
	}
}
