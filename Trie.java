import java.util.ArrayList;
import java.util.HashMap;
/**
 *  Implements a trie. We store the input list of words in tries so
 * that we can efficiently find words with a given prefix. 
 **/
public class Trie implements ITrie{
	
	private TrieNode root;
    
	Trie() {
		root = new TrieNode();
	}

	@Override
	public void insert(String word) {
		word =  word.toLowerCase();
		if(search(word)!=null) {
			return;
		}
		TrieNode current = root;
		HashMap<Character, TrieNode> children = current.getChildren();	
		for(int i=0;i< word.length();i++) {
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

	@Override
	public TrieNode search(String prefix) {
		// TODO Auto-generated method stub
		HashMap<Character, TrieNode> children = root.getChildren();
		TrieNode pre = null;
		for(int i =0;i< prefix.length();i++) {
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

	@Override
	public ArrayList<String> listOfPredictions(TrieNode node) {
		String str = "";
		ArrayList<String> predict = listPredictionsHelper(node, str);
		return predict;
		// TODO Auto-generated method stub
		
	}
	
	private ArrayList<String> listPredictionsHelper(TrieNode node, String s){
		
		TrieNode root = node;
		ArrayList<String> predictions = new ArrayList<>();
//		if(root==null) {
//			return predictions;
//		}
		if(node.isLastNode()) {
			s = s +node.getValue();
			predictions.add(s);
		}
		
		HashMap<Character, TrieNode> children = node.getChildren();
		for(Character ch: children.keySet()) {
			root = children.get(ch);
			predictions.addAll(listPredictionsHelper(root, s));
		}
		
		return predictions;
		};
	}


