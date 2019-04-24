package newAuto;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author add the whole team later
 *
 */
public class Autocomplete implements IAutocomplete {

	Trie[] tries ;
	HashMap<String, Integer> wordTable ;
	HashMap<String, PrefixPair[] > successorMap ;
	
	public Autocomplete (HashMap<String, Integer> wTable, HashMap<String, HashMap<String, Integer>> successors) {
		wordTable = wTable ;
		buildTries(wTable) ;
		buildSuccessorMap(successors) ;
	}
	
	private void buildTries(HashMap<String, Integer> wTable) {
		// TODO 
	}
	
	private void buildSuccessorMap(HashMap<String, HashMap<String, Integer>> before) {
		// TODO 
	
	}
	
	private PrefixPair[] convertToPrefixPair(HashMap<String, Integer> map) {
		// TODO
		return null ;
	}
	
	@Override
	public String[] allPredictions(String input) {
		// TODO Auto-generated method stub
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
