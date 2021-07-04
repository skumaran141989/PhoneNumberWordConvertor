package src.main.entry;

import java.util.HashMap;
import java.util.Set;

import src.main.services.CommandLineInterpretor;
import src.main.services.PhoneNumberInputProcessor;
import src.main.services.PhoneNumberProcessorFromFile;
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
		
		executor.executeCommand(cli);
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
		
		_phoneNumberProcessor = new PhoneNumberProcessorFromFile(cli.getStringValue("-i"));
		
		return _phoneNumberProcessor.processPhoneNumbersFromFile(_phoneToWordConvertor);
	}
}
