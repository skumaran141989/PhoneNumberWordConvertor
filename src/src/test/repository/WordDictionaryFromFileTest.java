package src.test.repository;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import src.main.repository.WordDictionary;
import src.main.repository.WordDictionaryFromFile;
import src.utils.Constants;

class WordDictionaryFromFileTest {

	private WordDictionary _wordDictionary;
	private String _inputFile;
	private String _dictionaryFile;
	
	public WordDictionaryFromFileTest() throws IOException
	{
		_wordDictionary = WordDictionaryFromFile.getInstance();
		
		String projectPath = new File(".").getCanonicalPath();
		
		_inputFile = projectPath+"/src/test/resources/input.txt";
		_dictionaryFile = projectPath+"/src/test/resources/dictionary.txt";
	}
	
	@Test
	void loadNotExistingDictionaryFile() {
		 Exception exception = assertThrows(NumberFormatException.class, () -> {
			 _wordDictionary.load(_dictionaryFile+"2");;
		    });
		 
		 assertEquals(Constants.MISSING_DICTIONARY_FILE, exception.getMessage());
	}
	
	@Test
	void loadExistingDictionaryFile() throws Exception {
	    _wordDictionary.load(_dictionaryFile);
	     Assert.assertNotNull(_wordDictionary.getStartingNode('C'));
	}
	
	@Test
    void getStartingNodeWithExistingChar() throws Exception {
		 _wordDictionary.load(_dictionaryFile);
		Assert.assertNotNull(_wordDictionary.getStartingNode('C'));
	}
	
	@Test
	void getStartingNodeWithNonExistingChar() throws Exception {
		 _wordDictionary.load(_dictionaryFile);
		Assert.assertNull(_wordDictionary.getStartingNode('N'));
	}
}
