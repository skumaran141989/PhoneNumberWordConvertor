package src.test.execution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import src.main.entry.Executor;
import src.main.services.CommandLineInterpretor;
import src.utils.Constants;

class ExecutorTest {
	
	private CommandLineInterpretor _cli;
	private String _dictionaryFile;
	private String _inputFile;
	private Executor _executor = new Executor();
	
	public ExecutorTest() throws Exception
	{
		_cli = new CommandLineInterpretor();
		
		String projectPath = new File(".").getCanonicalPath();
				
		_inputFile = projectPath+"/src/test/resources/input.txt";
		_dictionaryFile = projectPath+"/src/test/resources/dictionary.txt";
		_executor = new Executor();
	}

	@Test
	void executorTestWithAllArguements() throws Exception {

		
		_cli.setArguements(new String[] {"", "-d", _dictionaryFile, "-i", _inputFile, "-m", "PhonePad"});
		
		Exception exception = assertThrows(Exception.class, () -> {
			_executor.executeCommand(_cli);
		    });
		 
		 assertNull(exception);
	}
	
	@Test
	void executorTestWithMissingDictionaryFileArguement() throws Exception {
		_cli.setArguements(new String[] {"", "-i", _inputFile, "-m", "PhonePad"});
		
		Exception exception = assertThrows(Exception.class, () -> {
			_executor.executeCommand(_cli);
		    });
		 
		 assertEquals(Constants.MISSING_DICTIONARY_OPTION, exception.getMessage());
	}
	
	@Test
	void executorTestWithMissingInputFileArguements() throws Exception {
		_cli.setArguements(new String[] {"", "-d", _dictionaryFile, "-m", "PhonePad"});
		
		Exception exception = assertThrows(Exception.class, () -> {
			_executor.executeCommand(_cli);
		    });
		 
		 assertEquals(Constants.MISSING_INPUT_OPTION, exception.getMessage());
	}
	
	@Test
	void executorTestWithMissingNumberToLetterStrategyArguements() throws Exception {
		
		_cli.setArguements(new String[] {"", "-d", _dictionaryFile, "-i", _inputFile});
		
		Exception exception = assertThrows(Exception.class, () -> {
			_executor.executeCommand(_cli);
		    });
		 
		 assertEquals(Constants.MISSING_NUMBER_TO_LETTER_STRATEGY_OPTION, exception.getMessage());
	}
	
	@Test
	void validateExecutorTestWithAllArguements() throws Exception {
		_cli.setArguements(new String[] {"", "-d", _dictionaryFile, "-i", _inputFile, "-m", "PhonePad"});
		
		HashMap<String, List<String>> result= _executor.executeCommand(_cli);
		
		assertEquals(result.get("1-800-3569377").get(0), "1800FLOWERS");
		assertEquals(result.get("225563").get(0), "CALLME");
	}

}
