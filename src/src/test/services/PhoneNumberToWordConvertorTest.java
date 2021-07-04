package src.test.services;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Set;

import org.junit.jupiter.api.Test;

import src.main.models.PhoneNumber;
import src.main.services.PhoneNumberToWordConvertor;
import src.utils.Constants;

class PhoneNumberToWordConvertorTest {
	private String _dictionaryFile;
	private PhoneNumberToWordConvertor _phoneNumberToWordConvertor;
	
	public PhoneNumberToWordConvertorTest() throws Exception
	{
		String projectPath = new File(".").getCanonicalPath();
				
		_dictionaryFile = projectPath+"/src/src/test/resources/dictionary.txt";
		_phoneNumberToWordConvertor = PhoneNumberToWordConvertor.getInstance();
	}
	
	@Test
	public void Test_setDictionaryWithValidFileName() throws Exception
	{
		assertDoesNotThrow(()->{_phoneNumberToWordConvertor.setDictionary(_dictionaryFile);});
	}
	
	@Test
	public void Test_setDictionaryWithInValidFileName()
	{
		assertThrows(Exception.class, ()->{_phoneNumberToWordConvertor.setDictionary(_dictionaryFile+"2.txt");}, Constants.MISSING_DICTIONARY_FILE);
	}
	
	@Test
	public void Test_getConvertedWordsWithValidNumberWithNoPunctiation() throws Exception
	{
		_phoneNumberToWordConvertor.setDictionary(_dictionaryFile);
		_phoneNumberToWordConvertor.setNumberToLetterMapper("PhonePad");
		
		Set<String> result= _phoneNumberToWordConvertor.getConvertedWords(new PhoneNumber("18005282"));
		
		assertEquals(1, result.size());
		assertTrue(result.contains("1800JAVA"));
	}
	
	@Test
	public void Test_getConvertedWordsWithValidNumberWithPunctuation() throws Exception
	{
		_phoneNumberToWordConvertor.setDictionary(_dictionaryFile);
		_phoneNumberToWordConvertor.setNumberToLetterMapper("PhonePad");
		
		Set<String> result= _phoneNumberToWordConvertor.getConvertedWords(new PhoneNumber("180-052,8 2"));
		
		assertEquals(1, result.size());
		assertTrue(result.contains("1800JAVA"));
	}
	
	@Test
	public void Test_getConvertedWordsWithValidNumberWithMultiWord() throws Exception
	{
		_phoneNumberToWordConvertor.setDictionary(_dictionaryFile);
		_phoneNumberToWordConvertor.setNumberToLetterMapper("PhonePad");
		
		Set<String> result= _phoneNumberToWordConvertor.getConvertedWords(new PhoneNumber("2255637666"));
		
		assertTrue(result.contains("CALLMESOON"));
	}
	
	@Test
	public void Test_getConvertedWordsWithValidNumberFullWord() throws Exception
	{
		_phoneNumberToWordConvertor.setDictionary(_dictionaryFile);
		_phoneNumberToWordConvertor.setNumberToLetterMapper("PhonePad");
		
		Set<String> result= _phoneNumberToWordConvertor.getConvertedWords(new PhoneNumber("5282"));
		
		assertTrue(result.contains("JAVA"));
	}
	
	@Test
	public void Test_getConvertedWordsWithValidNumberFullWordMultipleCombinations() throws Exception
	{
		_phoneNumberToWordConvertor.setDictionary(_dictionaryFile);
		_phoneNumberToWordConvertor.setNumberToLetterMapper("PhonePad");
		
		Set<String> result= _phoneNumberToWordConvertor.getConvertedWords(new PhoneNumber("228"));
		
		assertEquals(2, result.size());
		assertTrue(result.contains("BAT"));
		assertTrue(result.contains("CAT"));
	}
	
	@Test
	public void Test_getConvertedWordsWithValidNumberNoPut() throws Exception
	{
		_phoneNumberToWordConvertor.setDictionary(_dictionaryFile);
		_phoneNumberToWordConvertor.setNumberToLetterMapper("PhonePad");
		
		Set<String> result= _phoneNumberToWordConvertor.getConvertedWords(new PhoneNumber("12345"));
		
		assertEquals(0, result.size());
	}
	
	@Test
	public void Test_getConvertedWordsWithValidNumberMultiplecPartialCombinations() throws Exception
	{
		_phoneNumberToWordConvertor.setDictionary(_dictionaryFile);
		_phoneNumberToWordConvertor.setNumberToLetterMapper("PhonePad");
		
        Set<String> result= _phoneNumberToWordConvertor.getConvertedWords(new PhoneNumber("1-800-3569377"));
		assertEquals(3, result.size());
		assertTrue(result.contains("1800FLOW377"));
		assertTrue(result.contains("1800FLOWER7"));
		assertTrue(result.contains("1800FLOWERS"));
	}
	
	@Test
	public void Test_getConvertedWordsWithInavlidPhoneNumber() throws Exception
	{
		_phoneNumberToWordConvertor.setDictionary(_dictionaryFile);
		_phoneNumberToWordConvertor.setNumberToLetterMapper("PhonePad");
		
		assertThrows(Exception.class, ()->{_phoneNumberToWordConvertor.getConvertedWords(new PhoneNumber("11111BA"));}, Constants.INVALID_PHONE_NUMBER);
	}
}
