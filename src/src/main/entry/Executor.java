package src.main.entry;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import src.main.services.CommandLineInterpretor;
import src.main.services.PhoneNumberInputProcessor;
import src.main.services.PhoneNumberInputProcessorFromFile;
import src.main.services.PhoneNumberToWordConvertor;
import src.utils.Constants;

public class Executor {
	
	private PhoneNumberToWordConvertor _phoneToWordConvertor;
	private PhoneNumberInputProcessor _phoneNumberProcessor;
	
	public static void main(String[] args) throws Exception
	{
		Executor executor = new Executor();
		
		CommandLineInterpretor cli = new CommandLineInterpretor();
		cli.setArguements(args);
		
		HashMap<String, Set<String>> result = executor.executeCommand(cli);
		
		Set<Entry<String, Set<String>>> resultEntries = result.entrySet();
		
		
		for(Entry<String, Set<String>> resultEntry : resultEntries)
		{
			System.out.println("Possible alphanumberic combinations for "+resultEntry.getKey()+" are:");
			
			Set<String> wordNumbers = resultEntry.getValue();
			for(String wordNumber : wordNumbers)
				System.out.println("\t"+wordNumber);
			
			System.out.println();
		}
	}
	
	public Executor()
	{
		_phoneToWordConvertor = PhoneNumberToWordConvertor.getInstance();
	}
	
	public HashMap<String, Set<String>> executeCommand(CommandLineInterpretor cli) throws Exception
	{
		if (cli.getStringValue("-d")==null)
		{
			throw new Exception(Constants.MISSING_DICTIONARY_OPTION);
		}
		if (cli.getStringValue("-i")==null)
		{
			throw new Exception(Constants.MISSING_INPUT_OPTION);
		}
		if (cli.getStringValue("-m")==null)
		{
			throw new Exception(Constants.MISSING_NUMBER_TO_LETTER_STRATEGY_OPTION);
		}
		
		_phoneToWordConvertor.setDictionary(cli.getStringValue("-d"));
		_phoneToWordConvertor.setNumberToLetterMapper(cli.getStringValue("-m"));
		
		_phoneNumberProcessor = new PhoneNumberInputProcessorFromFile();
		
	    _phoneNumberProcessor.processPhoneNumbersFromSource(cli.getStringValue("-i"));
	    
	    return _phoneToWordConvertor.processNumbersFromInputProcessor(_phoneNumberProcessor);
	}
}
