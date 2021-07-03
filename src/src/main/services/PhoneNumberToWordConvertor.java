package src.main.services;

import java.util.List;

import src.main.models.PhoneNumber;
import src.main.models.TrieNode;
import src.main.repository.WordDictionary;
import src.main.repository.WordDictionaryFromFile;

public class PhoneNumberToWordConvertor {
	
	private WordDictionary _dictionary;
	private NumberToLetterMapper _letterMap;
	private List<String> _wordsForPhoneNumber;
	private static PhoneNumberToWordConvertor _phoneNumberToWordConvertor = new PhoneNumberToWordConvertor();
	 
	private PhoneNumberToWordConvertor() {
	}
	
	public static PhoneNumberToWordConvertor getInstance()
	{
		return _phoneNumberToWordConvertor;
	}
	
	public void setDictionary(String dictionaryFileName) throws Exception
	{
		_dictionary = WordDictionaryFromFile.getInstance();
		_dictionary.load(dictionaryFileName);
	}
	
	public void setNumberToLetterMapper(String NumberToLetterMapperStrategy)
	{
		_letterMap = NumberToLetterMapperFactory.getNumberToLetterMapperFactory(NumberToLetterMapperStrategy);
	}
	
	public List<String> getConvertedWords(PhoneNumber phoneNumber) {
		
	    char[] phoneNumberDigits = phoneNumber.getDigitsArray();
	    int length = phoneNumberDigits.length;
	    
	    int currStartPos = 0;
	    while(currStartPos < length-1)
			for(int i=0; i<length; i++)
			{
				getMatchedStrings(i+currStartPos, 0, phoneNumberDigits, phoneNumberDigits, null);
			}
		
		return _wordsForPhoneNumber;
	}
	
	private void getMatchedStrings(int staringDigitIndex, int level, char[] phoneNumberDigits, char[] output, TrieNode parentNode) {
		
		TrieNode[] nodes = null;
		
		if (level == 0)
		{
			List<Character> matchedCharacters = _letterMap.getAllEncodedCharacters(phoneNumberDigits[staringDigitIndex+level]);
			int matchedCharactersLength = matchedCharacters.size();
			for(int i=0; i<matchedCharactersLength; i++)
			{
			  nodes = _dictionary.getStartingNode(matchedCharacters.get(i)).getChildNodes();
			}
		}
		else
			nodes = parentNode.getChildNodes();
		
		for(TrieNode trieNode : nodes)
		{
			output[staringDigitIndex+level] = trieNode.getValue();
			if(trieNode.getIsEnd()) {
				_wordsForPhoneNumber.add(output.toString());
				if((staringDigitIndex+1) < phoneNumberDigits.length)
					getMatchedStrings(staringDigitIndex+1, 0, phoneNumberDigits, output, null);
			}
			else 
			  getMatchedStrings(staringDigitIndex,level+1, phoneNumberDigits, output, trieNode);
		}			
	}
}
