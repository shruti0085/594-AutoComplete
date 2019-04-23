import java.util.ArrayList;
/**
 *  Implements a trie. We store the input list of words in tries so
 * that we can efficiently find words with a given prefix. 
 **/
public class Trie implements ITrie{
	
	private TrieNode root;
    
	public Trie() {
		root = new TrieNode(' ');
	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TrieNode search(String prefix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> listOfPredictions(TrieNode node) {
		// TODO Auto-generated method stub
		return null;
	}

}
