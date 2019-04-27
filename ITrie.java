import java.util.ArrayList;

/**
 * Defines the interface for a prefix tree, an ordered tree data structure
 * We store the input list of words in tries so
 * that we can efficiently find words with a given prefix. 
 * @author shruti sinha
 *
 */
public interface ITrie {
	
	/** Insert a word into the trie.
	 *  if string is null or empty return IllegalArgumentException
	 *  if string is not present in the Trie, insert it 
	 *  @param word
	 */
	public void insert(String word);
	/**
	 * Search for the given string prefix in the Trie.
	 * If present, return last TrieNode containing the prefix,
	 * if not present or prefix is null or empty, return null
	 * @param prefix
	 * @return TrieNode containing the prefix
	 */
	public TrieNode search(String prefix);
	/** 
	 * Returns list of predictions traversing from the given TrieNode to get to all children 
	 * which are lastNodes, thus returning all words present in the trie in an ArrayList<String>
	 * If there are no children from the given node or the node is null, return null
	 * 
	 * */
	public ArrayList<String> listOfPredictions(TrieNode node);
}
