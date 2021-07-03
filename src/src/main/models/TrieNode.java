package src.main.models;


public class TrieNode {

	private char value;
	private TrieNode[] nextNodes;
	private boolean isEnd;
	
	public TrieNode()
	{
	  nextNodes = new TrieNode[26];
	  isEnd = false;
      for (int i = 0; i < 26; i++)
    	  nextNodes[i] = null;
	}
	
	public char getValue()
	{
		return this.value;
	}
	
	public void setValue(char value)
	{
		this.value = value;
	}
	
	public TrieNode[] getChildNodes()
	{
		return this.nextNodes;
	}
	
	public TrieNode getChildNode(int index)
	{
		return this.nextNodes[index];
	}
	
	public void setChildNode(int index, TrieNode node)
	{
		this.nextNodes[index]= node;
	}
	
	public boolean getIsEnd()
	{
		return this.isEnd;
	}
	
	public void setIsEnd(boolean value)
	{
		this.isEnd=value;
	}
}
