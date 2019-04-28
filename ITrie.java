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
	 *  if string is not present in the Trie, insert it,else return 
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
	 * Returns list of predictions for a given prefix by finding the TrieNode using search function
	 * and traversing from the given TrieNode to get to all children 
	 * which are lastNodes, thus returning all words present in the Trie for given prefix in an ArrayList<String>
	 * If there are no children from the given node, return null
	 * if the prefix string is empty or null, return null
	 * if the search function returns null, return null
	 * @param prefix : string for which all predictions to be found
	 * @return predict : arraylist of all predictions
	 * */
	public ArrayList<String> listOfPredictions(String prefix);
}
