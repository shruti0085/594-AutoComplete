import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Alexandra Rumyantseva
 *
 */
public class Autocomplete implements IAutocomplete {

	Trie[] tries ;
	HashMap<String, Integer> wordTable ;
	HashMap<String, PrefixPair[] > successorMap ;
	
	public Autocomplete (HashMap<String, Integer> wTable, HashMap<String, HashMap<String, Integer>> successors) {
		wordTable = wTable ; // need to call copy constructor ?
		buildTries(wTable) ;
		buildSuccessorMap(successors) ;
	}
	
	private void buildTries(HashMap<String, Integer> wTable) {
		// corner
		if (wTable == null)
			return ;
		
		tries = new Trie[26] ;
		
		for (String word : wTable.keySet()) {
			char firstLetter = word.charAt(0) ;
			int index = (int)firstLetter - 97 ;
			if (tries[index] == null) 
				tries[index] = new Trie(firstLetter) ;
			tries[index].insert(word) ;
		}           
	}
	
	// temp function for debbugging
	public void printTries() {
		for (int i=0; i< tries.length; i++) {
			if (tries[i] == null)
				System.out.println("tries " + i + " is null");
			else {
				System.out.println("tries " + i + " has root " + tries[i].getRoot().getValue());
			}
		}
	}
	
	private void buildSuccessorMap(HashMap<String, HashMap<String, Integer>> before) {

		if (before == null)
			return ; // throw something?
		
		successorMap = new HashMap<String, PrefixPair[]>() ;
		for (String word : before.keySet()) {
			HashMap<String, Integer> hashScsrs = before.get(word) ;
			PrefixPair[] arrScsrs = convertToPrefixPair(hashScsrs) ;
			// sort lexicographically
			Arrays.sort(arrScsrs); 
			successorMap.put(word, arrScsrs) ;
		}	
	}
	
	// temp function for debbugging
	public void printSuccessors() {
		for (String word : successorMap.keySet()) {
			System.out.println("word is " + word + " Successors are  : ");
			PrefixPair[] s = successorMap.get(word) ;
			for (int i=0; i< s.length; i++) {
				System.out.println(s[i].getName() + " : " + s[i].getFrequency());
			}
		}
	}
	
	private PrefixPair[] convertToPrefixPair(HashMap<String, Integer> hashScsrs) {
		PrefixPair[] arrScsrs = new PrefixPair[hashScsrs.size()] ;
		int i = 0 ;
		for (String s : hashScsrs.keySet()) {
			arrScsrs[i] = new PrefixPair(s, hashScsrs.get(s)) ;
			i++ ;
		}
		return arrScsrs ;
	}
	
	@Override
	public String[] allPredictions(String input) {
		// if input is empty, return empty array
		if (input.equals("")) 
			return new String[0] ;
		
		
		
		return null;
	}
	
	private boolean isWord(String input) {
		// TODO
		return false ;
	}
	
	private PrefixPair[] getSuccessorSuggestions(String word) {
		// TODO
		return null ;
	}
	
	private PrefixPair[] binarySearch(String prev, PrefixPair[] prevMatches) {
		// TODO
		return null ;
	}
	
	private String[] sortSuccessorsByFreq(PrefixPair[] wordWeight) {
		// TODO
		return null ;
	}
	
	private String[] getMatchSuggestions(String input, String[] prevMatch, int howMany) {
		// TODO
		return null ;

	}
	
	private String[] sortMatchesByFreq(ArrayList<String> unsorted) {
		// TODO
		return null ;
	}

}
