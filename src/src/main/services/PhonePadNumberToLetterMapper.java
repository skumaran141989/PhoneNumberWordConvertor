package src.main.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PhonePadNumberToLetterMapper implements NumberToLetterMapper {

	private HashMap<Character, List<Character>> phonePadLetterMap;
	
	public PhonePadNumberToLetterMapper() {
		
		phonePadLetterMap = new HashMap<Character, List<Character>>();
		
		List<Character> letterList= new ArrayList<Character>();
		
		letterList.add('A');
		letterList.add('B');
		letterList.add('C');
		phonePadLetterMap.put('2', letterList);
		
		letterList = new ArrayList<Character>();
		letterList.add('D');
		letterList.add('E');
		letterList.add('F');
		phonePadLetterMap.put('3', letterList);
		
		letterList= new ArrayList<Character>();
		letterList.add('G');
		letterList.add('H');
		letterList.add('I');
		phonePadLetterMap.put('4', letterList);
		
		letterList= new ArrayList<Character>();
		letterList.add('J');
		letterList.add('K');
		letterList.add('L');
		phonePadLetterMap.put('5', letterList);
		
		letterList= new ArrayList<Character>();
		letterList.add('M');
		letterList.add('N');
		letterList.add('O');
		phonePadLetterMap.put('6', letterList);
		
		letterList= new ArrayList<Character>();
		letterList.add('P');
		letterList.add('Q');
		letterList.add('R');
		letterList.add('S');
		phonePadLetterMap.put('7', letterList);
		
		letterList= new ArrayList<Character>();
		letterList.add('T');
		letterList.add('U');
		letterList.add('V');
		phonePadLetterMap.put('8', letterList);
		
		letterList = new ArrayList<Character>();
		letterList.add('W');
		letterList.add('X');
		letterList.add('Y');
		letterList.add('Z');
		phonePadLetterMap.put('9', letterList);
	}
	
	@Override
	 public List<Character> getAllEncodedCharacters(Character number){
		return phonePadLetterMap.get(number);
	 }
}
