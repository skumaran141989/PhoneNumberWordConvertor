package src.main.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import src.utils.Constants;

public class WordDictionaryFromFile extends WordDictionary {

  private String _fileName;
  
  private static WordDictionaryFromFile _wordDictionaryFromFile = new WordDictionaryFromFile();
  
  private WordDictionaryFromFile() {
  }
  
  public static WordDictionaryFromFile getInstance()
  {
	  return _wordDictionaryFromFile;
  }
  
  @Override
   public void load(Object input) throws Exception
   {
	  _fileName = (String)input;
	  clearDictionary();
	  
	   try (Scanner scanner = new Scanner(new File(_fileName)))
	   {
		   while (scanner.hasNextLine()) {
			  String word = scanner.nextLine();
			    insert(word.toUpperCase());
			  }
	   } catch (FileNotFoundException e) {
		 throw new Exception(Constants.MISSING_DICTIONARY_FILE);
	   }
   }
}
