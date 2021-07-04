package src.test.services;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import src.main.services.CommandLineInterpretor;
import src.utils.Constants;

class CommandLineInterpretorTest {

	private CommandLineInterpretor _commandLineInterpretor;
	private String _dictionaryFile;
	private String _inputFile;
	
	public CommandLineInterpretorTest() throws IOException
	{
		_commandLineInterpretor = new CommandLineInterpretor();
		String projectPath = new File(".").getCanonicalPath();
		
		_inputFile = projectPath+"/src/test/resources/input.txt";
		_dictionaryFile = projectPath+"/src/test/resources/dictionary.txt";
	}
	
	@Test
	void setTestArguementWithAllOptionsTest() throws Exception {
		_commandLineInterpretor.setArguements(new String[] {"-d", _dictionaryFile, "-f", _inputFile } );
		
		assertNotNull(_commandLineInterpretor.getStringValue("-d"));
	}
	
	@Test
	void setTestArguementWithMissingOptionsTest() throws Exception {
		_commandLineInterpretor.setArguements(new String[] {"-d", _dictionaryFile, "-f", _inputFile } );
		
		assertNull(_commandLineInterpretor.getStringValue("-i"));
	}
	
	@Test
	void validateTestArguementWithExistingOptionsValue() throws Exception {
		_commandLineInterpretor.setArguements(new String[] {"-d", _dictionaryFile, "-f", _inputFile } );
		
		assertEquals(_dictionaryFile, _commandLineInterpretor.getStringValue("-d"));
	}
	
	@Test
	void setTestArguementWithNoOptions() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			_commandLineInterpretor.setArguements(new String[] {"-d", "dictionary.txt", _inputFile } );
		    });
		 
		 assertEquals(Constants.MISSING_CLI_OPTION, exception.getMessage());
		
		assertEquals("dictionary.txt", _commandLineInterpretor.getStringValue("-d"));
	}
}
