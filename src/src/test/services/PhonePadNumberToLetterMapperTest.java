package src.test.services;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import src.main.services.NumberToLetterMapper;
import src.main.services.PhonePadNumberToLetterMapper;

@RunWith(Parameterized.class)
public class PhonePadNumberToLetterMapperTest {

	private NumberToLetterMapper _numberToLetterMapper;
	private Character inputCharacter;
	private Integer expectedNumberOfCharacters;
	
	@Before
	public void initialize()
	{
		_numberToLetterMapper = new PhonePadNumberToLetterMapper();
	}
	
	public PhonePadNumberToLetterMapperTest(Character inputCharacter, Integer expectedNumberOfCharacters )
	{
		this.inputCharacter = inputCharacter;
		this.expectedNumberOfCharacters = expectedNumberOfCharacters;
	}
	
	@Parameterized.Parameters
	   public static Collection<Object[]> getEncodedInputs() {
	      return Arrays.asList(new Object[][] {
	         { '2', 3 },
	         { '3', 3 },
	         { '4', 3 },
	         { '5', 3 },
	         { '6', 3 },
	         { '7', 4 },
	         { '8', 3 },
	         { '9', 4 },
	      });
	   }
	
	@Test
	public void getAllEncodedCharactersForExistingCharacter() {
       Assert.assertEquals(expectedNumberOfCharacters.intValue(), _numberToLetterMapper.getAllEncodedCharacters(inputCharacter).size());
	}
	
	@Test
	public void getEmptyListForEncodedCharactersForNonExistingCharacter() {
       Assert.assertEquals(0, _numberToLetterMapper.getAllEncodedCharacters('0').size());
	}
}
