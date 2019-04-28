import java.util.ArrayList;
import java.util.HashMap;
/**
 *  Implements a trie. We store the input list of words in tries so
 * that we can efficiently find words with a given prefix. 
 **/
public class Trie implements ITrie{
	
	private TrieNode root;
    
	Trie(char c) {
		root = new TrieNode(c);
	}
   
	/** Insert a word into the trie.
	 *  if string is null return IllegalArgumentException
	 *  if string is not present in the Trie, insert it 
	 *  @param word
	 */
     
	@Override
	public void insert(String word) {
		if(word==null || word.equals("")) {
			throw new IllegalArgumentException();
		}
		word =  word.toLowerCase();
		//if trie already has the word
		if(search(word)!=null) {
			TrieNode tr = search(word);
			tr.setLastNode(true);
			return;
		}
		TrieNode current = root;
		HashMap<Character, TrieNode> children = current.getChildren();	
		for(int i=1;i< word.length();i++) {
			char ch = word.charAt(i);
			TrieNode pre;
			if(children.containsKey(ch)) {
				pre = children.get(ch);
			}else {
				pre = new TrieNode(ch);
				children.put(ch,pre);
			}
			children = pre.getChildren();
			if(i == word.length()-1) {
				pre.setLastNode(true);
			}
			
		}
		// TODO Auto-generated method stub
		
	}
	/**
	 * Search for the given string prefix in the Trie.
	 * If present, return last TrieNode containing the prefix,
	 * if not present or string is null or empty, return null
	 * @param prefix
	 * @return TrieNode containing the prefix
	 */
	@Override
	public TrieNode search(String prefix) {
		// TODO Auto-generated method stub
		if(prefix==null || prefix.equals("")) {
			return null;
		}
		HashMap<Character, TrieNode> children = root.getChildren();
		TrieNode pre = root;
		for(int i =1;i< prefix.length();i++) {
			char ch = prefix.charAt(i);
			if(children.containsKey(ch)) {
				pre = children.get(ch);
				children = pre.getChildren();
			}else {
				return null;
			}
		}
		return pre;
	}
	/** 
	 * Returns list of predictions for a given prefix by finding the TrieNode using search function
	 * and traversing from the given TrieNode to get to all children 
	 * which are lastNodes, thus returning all words present in the trie for given prefix in an ArrayList<String>
	 * If there are no children from the given node, return null
	 * if the prefix string is empty or null, return null
	 * if the search function returns null, return null
	 * @param- prefix : string for which all predictions to be found
	 * @return : predict: arraylist of all predictions
	 * */
	@Override
	public ArrayList<String> listOfPredictions(String prefix) {
		
		if(prefix==null || prefix.equals("")) {
			return null;
		}
		
		TrieNode node = search(prefix);
		if(node == null) {
			return null;
		}
		String str = "";
		str = str + prefix.substring(0,prefix.length()-1);
		ArrayList<String> predict = listPredictionsHelper(node, str);
		if(predict.size() ==0) {
			return null;
		}
		return predict;
		// TODO Auto-generated method stub
		
	}
	/**
	 * Helper function for listOfPredictions function to get all predicted words
	 * given TrieNode for a prefix
	 * @param node- TrieNode whose leaves would be returned
	 * @param s- string to make words using characters from each node
	 * @return ArrayList<String> predicted words
	 */
	private ArrayList<String> listPredictionsHelper(TrieNode node, String s){
		
		TrieNode root = node;
		ArrayList<String> predictions = new ArrayList<>();
		s = s+ root.getValue();
		if(node.isLastNode()) {
			
			predictions.add(s);
		}
		
		HashMap<Character, TrieNode> children = node.getChildren();
		for(Character ch: children.keySet()) {
			root = children.get(ch);
			
			predictions.addAll(listPredictionsHelper(root, s));
		}
		
		return predictions;
		}
	}


