package src.main.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import src.main.models.PhoneNumber;
import src.main.models.TrieNode;
import src.main.repository.WordDictionary;
import src.main.repository.WordDictionaryFromFile;

public class PhoneNumberToWordConvertor {
	
	private WordDictionary _dictionary;
	private NumberToLetterMapper _letterMap;
	
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
	
	public HashMap<String, Set<String>> processNumbersFromInputProcessor(PhoneNumberInputProcessor phoneNumberInputProcessor)
	{
		HashMap<String, Set<String>> wordsForPhoneNumbers = new HashMap<String, Set<String>>();
		List<PhoneNumber> phoneNumbers  = phoneNumberInputProcessor.getPhonenNumbers();
		
		for(PhoneNumber phoneNumber:phoneNumbers)
		{
			wordsForPhoneNumbers.put(phoneNumber.getActualPhoneNumber(), getConvertedWords(phoneNumber));
		}
		
		return wordsForPhoneNumbers;
	}
	
	public Set<String> getConvertedWords(PhoneNumber phoneNumber) {
		Set<String> _wordsForPhoneNumber = new HashSet<String>();
	    char[] phoneNumberDigits = phoneNumber.getDigitsArray();
	    int length = phoneNumberDigits.length;
	    
	    int i=0;

	    for(i=0; i<length; i++)
        {
	    	getMatchedStrings(i, 0, false, phoneNumberDigits, phoneNumberDigits.clone(), null, _wordsForPhoneNumber);
		}
		
		return _wordsForPhoneNumber;
	}
	
	private void getMatchedStrings(int staringDigitIndex, int level, boolean continueWord, char[] phoneNumberDigits, 
			char[] output, TrieNode parentNode, Set<String> _wordsForPhoneNumber) {
		if (level == 0)
		{
			List<Character> matchedCharacters = _letterMap.getAllEncodedCharacters(phoneNumberDigits[staringDigitIndex+level]);
			int matchedCharactersLength = matchedCharacters.size();
			for(int i=0; i<matchedCharactersLength; i++)
			{
				parentNode = _dictionary.getStartingNode(matchedCharacters.get(i));
				getMatchedStringFromParent(staringDigitIndex, level, continueWord, phoneNumberDigits, output, parentNode, _wordsForPhoneNumber);
			}
		 }
		
		getMatchedStringFromParent(staringDigitIndex, level, continueWord, phoneNumberDigits, output, parentNode, _wordsForPhoneNumber);
	}
	
	private void getMatchedStringFromParent(int staringDigitIndex, int level, boolean continueWord,
			char[] phoneNumberDigits, char[] output, TrieNode parentNode, Set<String> _wordsForPhoneNumber)
	{
		TrieNode[] childNodes = null;
		if(parentNode != null)
		{
			if(continueWord)
			{
				String word = new String(output);
				word = word.substring(0,staringDigitIndex+level+(output.length-phoneNumberDigits.length))+"-"+word.substring(staringDigitIndex+level+(output.length-phoneNumberDigits.length), word.length());
				output = word.toCharArray();
			}
			
			output[staringDigitIndex+level+(output.length-phoneNumberDigits.length)] = parentNode.getValue();
			
			if(parentNode.getIsEnd()) {
				_wordsForPhoneNumber.add(new String(output));
				if((staringDigitIndex+level+1) < phoneNumberDigits.length)
					getMatchedStrings(staringDigitIndex+level+1, 0, true, phoneNumberDigits, output.clone(), null, _wordsForPhoneNumber);		
			}
			if ((staringDigitIndex+level+1) < phoneNumberDigits.length )
			{
				childNodes = parentNode.getChildNodes();
				List<Character> matchedNextCharacters = _letterMap.getAllEncodedCharacters(phoneNumberDigits[staringDigitIndex+level+1]);
				if(childNodes != null)
					for(TrieNode trieNode : childNodes)
					{
						if(trieNode!=null && matchedNextCharacters.contains(trieNode.getValue()))
							getMatchedStrings(staringDigitIndex,level+1, false, phoneNumberDigits, output.clone(), trieNode, _wordsForPhoneNumber);
					}
			}
		}
	}
}
