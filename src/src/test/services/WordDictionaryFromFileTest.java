package src.test.services;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import src.main.repository.WordDictionary;
import src.main.repository.WordDictionaryFromFile;
import src.utils.Constants;

class WordDictionaryFromFileTest {

	private WordDictionary _wordDictionary;
	private String _dictionaryFile;
	
	public WordDictionaryFromFileTest() throws IOException
	{
		_wordDictionary = WordDictionaryFromFile.getInstance();
		
		String projectPath = new File(".").getCanonicalPath();
		
		_dictionaryFile = projectPath+"/src/src/test/resources/dictionary.txt";
	}
	
	@Test
	void loadNotExistingDictionaryFile() {
		 Exception exception = assertThrows(Exception.class, () -> {
			 _wordDictionary.load(_dictionaryFile+"2");
		    });
		 
		 assertEquals(Constants.MISSING_DICTIONARY_FILE, exception.getMessage());
	}
	
	@Test
	void loadExistingDictionaryFile() throws Exception {
		assertDoesNotThrow(()->{_wordDictionary.load(_dictionaryFile);});
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
