import java.util.HashMap;

/**
 * TrieNode in a general trie, each representing a character. Each node will keep
 * track of its children in a hashmap and 
 * additional valid state if it is the last character of a word.
 * 
 * @author Shruti Sinha
 *
 */
public class TrieNode {
	char value;
	HashMap<Character, TrieNode> children;
	boolean lastNode;
	
	/**
	 * Constructor to create a new TrieNode given a character to store in it 
	 * @param value -character to be stored
	 */
	public TrieNode(char value){
		this.value = value;
		children = new HashMap<>();
		lastNode = false;
	}
	
	public TrieNode() {
		children = new HashMap<>();
		lastNode = false;
	}
	/**
	 * Return the value associated with a given TrieNode
	 * @return value
	 */
	public char getValue() {
		return value;
	}
	/**
	 * Sets the value associated for a TrieNode
	 * @param value
	 */
	public void setValue(char value) {
		this.value = value;
	}
	/**
	 * Returns the children for the TrieNode
	 * @return children
	 */
	public HashMap<Character, TrieNode> getChildren() {
		return children;
	}
	/**
	 * Sets the children for given TrieNode
	 * @param children
	 */
	public void setChildren(HashMap<Character, TrieNode> children) {
		this.children = children;
	}
	/**
	 * Returns whether or not the given TrieNode ends a word in the Trie
	 * @return lastNode
	 */
	public boolean isLastNode() {
		return lastNode;
	}
	/**
	 * Sets whether or not the TrieNode ends a word in the Trie
	 * @param lastNode
	 */
	public void setLastNode(boolean lastNode) {
		this.lastNode = lastNode;
	}
	
	
}
