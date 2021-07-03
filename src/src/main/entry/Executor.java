package src.main.entry;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import src.main.services.CommandLineInterpretor;
import src.main.services.PhoneNumberProcessor;
import src.main.services.PhoneNumberProcessorFromFile;
import src.main.services.PhoneNumberToWordConvertor;
import src.utils.Constants;

public class Executor {
	
	private PhoneNumberToWordConvertor _phoneToWordConvertor;
	private PhoneNumberProcessor _phoneNumberProcessor;
	private String _projectPath;
	
	public static void main(String[] args) throws Exception
	{
		Executor executor = new Executor();
		executor._projectPath = new File(".").getCanonicalPath();
		System.out.println(executor._projectPath);
		
		CommandLineInterpretor cli = new CommandLineInterpretor();
		cli.setArguements(args);
		
		executor.executeCommand(cli);
	}
	
	public Executor()
	{
		_phoneToWordConvertor = PhoneNumberToWordConvertor.getInstance();
	}
	
	public HashMap<String, List<String>> executeCommand(CommandLineInterpretor cli) throws Exception
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
