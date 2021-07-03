package src.main.repository;

import java.util.HashMap;

import src.main.models.TrieNode;

public abstract class WordDictionary {
	
	private HashMap<Character, TrieNode> rootNodes;
	
	abstract public void load(Object input) throws Exception;
	
	public TrieNode getStartingNode(char startingChar)
	{
	  return rootNodes.get(startingChar);
	}
	
	public void clearDictionary()
	{
		if(rootNodes!=null)
	      rootNodes.clear();
	}
	
	protected void insert(String input)
	{
		if(rootNodes!=null)
	      rootNodes = new HashMap<Character, TrieNode>();
		
		  char startingChar = input.charAt(0);
		  TrieNode trieNode = new TrieNode();
		  
		  if (rootNodes.containsKey(startingChar))
			  trieNode = rootNodes.get(startingChar);
		  else
			  trieNode.setValue(startingChar);
		 
			  int length = input.length();
			  for (int level = 1; level < length; level++)
		        {
		            int index = input.charAt(level) - 'A';
		            
		            if (trieNode.getChildNode(index) == null)
		            	trieNode.setChildNode(index, new TrieNode());
		      
		            trieNode = trieNode.getChildNode(index);
		        }
			  
			  trieNode.setIsEnd(true);
			  rootNodes.put(startingChar, trieNode);
	}
}
	
	
