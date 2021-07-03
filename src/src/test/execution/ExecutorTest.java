package src.test.execution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.Test;

import src.main.entry.Executor;
import src.main.services.CommandLineInterpretor;
import src.utils.Constants;

class ExecutorTest {
	
	private CommandLineInterpretor _cli;
	private String _dictionaryFile;
	private String _inputFile;
	
	public ExecutorTest() throws Exception
	{
		_cli = new CommandLineInterpretor();
		
		String projectPath = new File(".").getCanonicalPath();
				
		_inputFile = projectPath+"/src/test/resources/input.txt";
		_dictionaryFile = projectPath+"/src/test/resources/dictionary.txt";
	}

	@Test
	void executorTestWithAllArguements() throws Exception {
		Executor executor = new Executor();
		
		_cli.setArguements(new String[] {"", "-d", _dictionaryFile, "-i", _inputFile, "-m", "PhonePad"});
		
		Exception exception = assertThrows(Exception.class, () -> {
			executor.executeCommand(_cli);
		    });
		 
		 assertNull(exception);
	}
	
	@Test
	void executorTestWithMissingDictionaryFileArguement() throws Exception {
		Executor executor = new Executor();
		_cli.setArguements(new String[] {"", "-i", _inputFile, "-m", "PhonePad"});
		
		Exception exception = assertThrows(Exception.class, () -> {
			executor.executeCommand(_cli);
		    });
		 
		 assertEquals(Constants.MISSING_DICTIONARY_OPTION, exception.getMessage());
	}
	
	@Test
	void executorTestWithMissingInputFileArguements() throws Exception {
		Executor executor = new Executor();
		_cli.setArguements(new String[] {"", "-d", _dictionaryFile, "-m", "PhonePad"});
		
		Exception exception = assertThrows(Exception.class, () -> {
			executor.executeCommand(_cli);
		    });
		 
		 assertEquals(Constants.MISSING_INPUT_OPTION, exception.getMessage());
	}
	
	@Test
	void executorTestWithMissingNumberToLetterStrategyArguements() throws Exception {
		
		Executor executor = new Executor();
		_cli.setArguements(new String[] {"", "-d", _dictionaryFile, "-i", _inputFile});
		
		Exception exception = assertThrows(Exception.class, () -> {
			executor.executeCommand(_cli);
		    });
		 
		 assertEquals(Constants.MISSING_NUMBER_TO_LETTER_STRATEGY_OPTION, exception.getMessage());
	}

}
