import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author Alexandra Rumyantseva
 *
 */
public class Autocomplete implements IAutocomplete {

	Trie[] tries ;
	HashMap<String, Integer> wordTable ;
	HashMap<String, PrefixPair[] > successorMap ;
	int nSuggestions = 5 ;
	
	public Autocomplete (HashMap<String, Integer> wTable, HashMap<String, HashMap<String, Integer>> successors) {
		if (wTable == null) 
			throw new IllegalArgumentException ("word table can not be null");
		if (successors == null) 
			throw new IllegalArgumentException ("successor table can not be null");

		
		wordTable = (HashMap<String, Integer>)wTable.clone();  
		buildTries(wTable) ;
		buildSuccessorMap(successors) ;

	}
	
	private void buildTries(HashMap<String, Integer> wTable) {
		
		tries = new Trie[26] ;
		
		for (String word : wTable.keySet()) {
			char firstLetter = word.charAt(0) ;
			int index = (int)firstLetter - 97 ;
			if ((index>=0) && (index<26)) {
				if (tries[index] == null) 
					tries[index] = new Trie(firstLetter) ;
				tries[index].insert(word) ;
			}
		}           
	}
	
//	// temp function for debbugging
//	public void printTries() {
//		for (int i=0; i< tries.length; i++) {
//			if (tries[i] == null)
//				System.out.println("tries " + i + " is null");
//			else {
//				//System.out.println("tries " + i + " has root " + tries[i].getRoot().getValue());
//			}
//		}
//	}
	
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
	
//	// temp function for debbugging
//	public void printSuccessors() {
//		for (String word : successorMap.keySet()) {
//			System.out.println("word is " + word + " Successors are  : ");
//			PrefixPair[] s = successorMap.get(word) ;
//			for (int i=0; i< s.length; i++) {
//				System.out.println(s[i].getName() + " : " + s[i].getFrequency());
//			}
//		}
//	}
	
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
		
		String spaced = input.replace(".", " . ").replace("!", " ! ").replace("?", " ? ").replace(",", " , ");
		
		if (!isWord(spaced))
			return new String[0] ;
		
		// tokenize input (watch out if length is 1)
		String[] tokens = input.split(" ");
		
		// get rid of some punctuation that we didn't check for
		String word = tokens[tokens.length - 1].trim().replaceAll("[^a-zA-Z0-9'.?!]", "") ;
		word = word.toLowerCase() ;
				
		// check if the word is a complete word that ends with space, or a prefix
		
		if (Character.isSpaceChar(input.charAt(input.length()-1))) {
			// user finished typing word and pressed space			
			
			// look for suggestions in successorMap
			PrefixPair[] succ = successorMap.get(word) ;
			// sort them in descending order of frequency
			String[] result = new String[0] ;
			if (succ != null)
				result = sortSuccessorsByFreq(succ, -1) ;
			return result ;
		}
		
		// last char is not space, so we have prefix
		
		// need to find if it has previous word
		String prev = null ;
		if (tokens.length > 1) {
			prev = tokens[tokens.length - 2].trim().replaceAll("[^a-zA-Z0-9'.?!]", "") ;
			prev = prev.toLowerCase() ;
		}
		
		int l = 0 ; // how many will be returned from prev successors
		String[] result1 = new String[0] ; // suggestions using prev successors
		if (isWord(prev)) {
						
			// get successor suggestions for prev
			PrefixPair[] prevMatches = successorMap.get(prev) ;
			
			// out of them get only those that start with prefix word
			// find first index of PP that  starts with prefix
			if (prevMatches != null) {
			int first = firstIndexOf(0, prevMatches.length-1, prevMatches, word) ;
			// find last index of PP that  starts with prefix
			int last = lastIndexOf(0, prevMatches.length-1, prevMatches, word) ;
			
			if ((first != -1) && (last != -1)) {
				PrefixPair[] sPrevMatches = Arrays.copyOfRange(prevMatches, first, last) ;

				result1 = sortSuccessorsByFreq(sPrevMatches, -1) ;
				l = result1.length ;
				// check if we already found enough
				if (l == nSuggestions)
					return result1 ;
			}
			}
		}	
		// if not found enough look in trie for prefix
		int remainder = nSuggestions - l ;
			
		String[] result2 = getMatchSuggestions(word, result1, remainder, prev) ;
			
		// combine results 1 and 2 
		int newLen = result1.length + result2.length ;
		String[] result = new String[newLen] ; 
		int i = 0 ;
		for (i = 0; i < result1.length; i++) {
			result[i] = result1[i] ;
		}
		for (int j = 0; j < result2.length; j++) {
			result[i] = result2[j] ;
			i++ ;
		}
		return result ;

	}
	
	// is last input word or punctuation?
	private boolean isWord(String input) {
		if ((input == null) || (input.equals(""))) {
			return false ;
		}
		// if last char is space, check if the one before is letter or punctuation
		// check for boundaries
		
		int len = input.length() ;
		if (len == 1) {
			if (Character.isLetter(input.charAt(0))) 
				return true ;
			else 
				return false ;
		}
		
		else {
			if (Character.isLetter(input.charAt(len-1)))
				return true ;
			else if (Character.isSpaceChar(input.charAt(len-1))) {
				// check if the one before is a letter
				if (Character.isLetter(input.charAt(len-2)))
					return true ;
				else
					return false ;
			}
			else
				return false ;
		}

	}
	
	private int  firstIndexOf(int low, int high, PrefixPair[] arr, String prefix) {
		// arr is empty
		if (high == -1)
			return -1 ;
		
		if (low == high) {
			if (((prefix.length() <= arr[low].getName().length())) && (arr[low].getName().substring(0, prefix.length()).equals(prefix))) 
				return low;
			else return -1;
        }
		
		else {
            int middle = (low + high) / 2;
            
            if (arr[middle].getName().substring(0, Math.min(prefix.length(), arr[middle].getName().length())).compareTo(prefix) >= 0)
                return firstIndexOf(low, middle, arr, prefix);
            else
                return firstIndexOf(middle+1, high, arr, prefix);
        }

	}
	
private int  lastIndexOf(int low, int high, PrefixPair[] arr, String prefix) {
	// arr is empty
		if (high == -1)
			return -1 ;
		
		if (low == high) {
            //if (arr[low].getName().substring(0, prefix.length()).equals(prefix)) 
			if (((prefix.length() <= arr[low].getName().length())) && (arr[low].getName().substring(0, prefix.length()).equals(prefix)))
            	return low;
            else return -1;
        }
		
		if ((high-low) == 1) {
			
            if  ((prefix.length() <= arr[high].getName().length()) 
            		&& (arr[high].getName().substring(0, prefix.length()).equals(prefix))) 
                return high;
            if ((prefix.length() <= arr[low].getName().length()) 
            		&& (arr[low].getName().substring(0, prefix.length()).equals(prefix)))
            	return low;
            else return -1;
        }
        else {
            int middle = (low + high) / 2;
            
            if  (arr[middle].getName().substring(0, Math.min(prefix.length(), arr[middle].getName().length())).compareTo(prefix) <= 0)
                return lastIndexOf(middle, high, arr, prefix);
            else
                return lastIndexOf(low, middle-1, arr, prefix);
        }

	}
	
	private String[] sortSuccessorsByFreq(PrefixPair[] succ, int howMany) {
		Comparator<PrefixPair> comparator = PrefixPair.byFreq();
		Arrays.sort(succ, comparator);
		// make a String array of necessary size and return it
		int l ;
		if (howMany == -1) {
			if (succ.length > nSuggestions)
				l = nSuggestions ;
			else 
				l = succ.length ;
		}
		else
			l = Math.min(howMany, succ.length) ;
		
		String[] result = new String[l] ;
		for (int i = 0; i < l; i++) {
			result[i] = succ[i].getName() ;
		}
		return result ;
	}
	
	// looking for them in corresponding trie
	private String[] getMatchSuggestions(String prefix, String[] prevMatch, int howMany, String prev) {
		// TO DO: prevMatch can be null !!!!!!
		
		// find corresponding index in tries
		char firstLetter = prefix.charAt(0) ;
		int index = (int)firstLetter - 97 ;
		
		if ((index < 0) || (index > 25)) // if first char is not letter
			return new String[0] ;       // we can't help
	
		ArrayList<String> list = tries[index].listOfPredictions(prefix) ;
		if (list == null) return new String[0] ; 
		
		// if list has word "prev", remove it
		if ((prev != null) && (list.contains(prev)))
			list.remove(prev) ;
		
	
		
		// remove overlaps with prevMatch
		if (prevMatch != null) {
			for (int i = 0 ; i < prevMatch.length; i++) {
				if (list.contains(prevMatch[i]))
					list.remove(prevMatch[i]) ;
				}
		}
		
		// make PP array for easy sort
		PrefixPair[] pArr = new PrefixPair[list.size()] ;
		boolean successfulTrieResult = true ;
		int i = 0 ;
		for (String w : list) {
			
			if (wordTable.get(w) != null) {
				int fr =  wordTable.get(w);
				pArr[i] = new PrefixPair(w, fr) ;
				i ++ ;
			}
			else {
				System.out.println("couldn't find word " + w + " return from trie search");
				successfulTrieResult = false ;
			}
		}
		
		if (successfulTrieResult) return sortSuccessorsByFreq (pArr, howMany) ;
		else return new String[0] ;

	}

}
