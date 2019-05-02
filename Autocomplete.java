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
	HashMap<String, PrefixPair[]> successorMap ; 
	int nSuggestions = 5 ;     
	
	Autocomplete (HashMap<String, Integer> wTable, HashMap<String, HashMap<String, Integer>> successors) {
		//System.out.println("This is passing in null: " + successors); 
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

		/**
		 * I took this validation out to get code coverage becausebuildSuccessorMap()
		 * called within Auto constructor, so nullpointer and illegalState are taken
		 * care of there.  No need to test inside buildSuccessMapAgain.
		 * if (before == null)
		 * return ;
		 */    
		
		successorMap = new HashMap<String, PrefixPair[]>() ;
		for (String word : before.keySet()) {
			HashMap<String, Integer> hashScsrs = before.get(word) ;
			PrefixPair[] arrScsrs = convertToPrefixPair(hashScsrs) ;
			// sort lexicographically
			Arrays.sort(arrScsrs);
			if (word.equals("to")) { 
				//System.out.println("successors for to ") ;
				for (int i = 0; i < arrScsrs.length; i++) {
					//System.out.println(arrScsrs[i].getName()) ;
				} 
			}
				
			successorMap.put(word, arrScsrs) ;  
		}
		
//		PrefixPair[] getsrs = successorMap.get("to") ;
//		System.out.println("GET  from map successors for to ") ;
//		for (int i = 0; i < getsrs.length; i++)
//			System.out.println(getsrs[i].getName()) ;
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
		
		//System.out.println("This is line 117 spaced: " + spaced);
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
			
			if (succ != null) {    
				// make copy of succ
				PrefixPair[] succCopy = Arrays.copyOf( succ, succ.length);
				result = sortSuccessorsByFreq(succCopy, -1) ;
			}
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
		String[] result1 = null ; // suggestions using prev successors
		if (isWord(prev)) {
						
			// get successor suggestions for prev
			PrefixPair[] prevMatches = successorMap.get(prev) ;
			
//			for ( PrefixPair p: prevMatches) {
//				System.out.println("This is prefix pair: " + p.getName());
//			} 
			 
			// out of them get only those that start with prefix word  
			// find first index of PP that  starts with prefix
			if (prevMatches != null) {
				//System.out.println("successors of prev word (before sorting by freq)  "+prev);
				for (int i = 0; i < prevMatches.length; i++) {
					//System.out.println(prevMatches[i].getName());
				}
				int first = firstIndexOf(0, prevMatches.length-1, prevMatches, word) ;
				// find last index of PP that  starts with prefix
				int last = lastIndexOf(0, prevMatches.length-1, prevMatches, word) ;
				//System.out.println("first index is  "+first+" last index is "+last);
			
				if ((first != -1) && (last != -1)) {
					PrefixPair[] sPrevMatches = Arrays.copyOfRange(prevMatches, first, last+1) ;
					//System.out.println("sPrevMatches has size "+sPrevMatches.length);
					result1 = sortSuccessorsByFreq(sPrevMatches, -1) ;
					for (int y = 0; y < result1.length; y++) {
						System.out.println(result1[y]); 
					}
					//System.out.println("result1 got assigned and has size "+result1.length); 
					l = result1.length ; 
					// check if we already found enough

					if (l == nSuggestions)
						return result1 ;                  
				}
			}
		}	
		// if not found enough look in trie for prefix
		int remainder = nSuggestions - l ;
			
		if ((result1 != null)&&(result1.length != 0)) {
			//System.out.println("successors of prev word (after sorting by freq "+prev);
			for (int i = 0; i < result1.length; i++) { 
				//System.out.println(result1[i]);
			}
		}
		String[] result2 = getMatchSuggestions(word, result1, remainder, prev) ;
		
		if (result2.length != 0) {
			//System.out.println("results from trie for word  "+word);
			for (int i = 0; i < result2.length; i++) {
				//System.out.println(result2[i]);
			}
		}
		
		// combine results 1 and 2 
		int newLen = 0 ;
		if (result1 != null)
			newLen = result1.length + result2.length ;
		else
			newLen = result2.length ;
		String[] result = new String[newLen] ; 
		int i = 0 ;
		if (result1 != null) {
			for (i = 0; i < result1.length; i++) {
				result[i] = result1[i] ;
			}
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
		} else {
			
			if (Character.isLetter(input.charAt(len-1))) {
				return true ;
			} else if (Character.isSpaceChar(input.charAt(len-1))) {
				// check if the one before is a letter
				if (Character.isLetter(input.charAt(len-2)))
					return true ;
				else
					return false ;    
			} else {
				return false ;         
			}
		}
	}
	
	private int  firstIndexOf(int low, int high, PrefixPair[] arr, String prefix) {
		// arr is empty
		if (high == -1)
			return -1 ; 
		//System.out.println("This is Low and High" + low + high);
		if (low == high) {
			if (((prefix.length() <= arr[low].getName().length())) && (arr[low].getName().substring(0, prefix.length()).equals(prefix))) { 
				//System.out.println((prefix.length() <= arr[low].getName().length()));
				//System.out.println((arr[low].getName().substring(0, prefix.length()).equals(prefix)));
				return low; 
			} else {
				return -1;         
			}
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
//		System.out.println("How Many: " + howMany);
//		for (PrefixPair p : succ) {
//			System.out.println(p.getName());
//		}
		Comparator<PrefixPair> comparator = PrefixPair.byFreq();
		Arrays.sort(succ, comparator);
		
		// make a String array of necessary size and return it
		int l ;
		//System.out.println("Line 332: " + succ.length + " " + nSuggestions);
		if (howMany == -1) { 
			if (succ.length > nSuggestions) 
				l = nSuggestions ;         
			else  
				l = succ.length ; 
		} 
		else
			l = Math.min(howMany, succ.length) ;
		//System.out.println("in sortSuccessorsByFreq() l = " + l);
		String[] result = new String[l] ;
		for (int i = 0; i < l; i++) {
			result[i] = succ[i].getName() ;
		}
		return result ;
	}
	
	// looking for them in corresponding trie
	private String[] getMatchSuggestions(String prefix, String[] prevMatch, int howMany, String prev) {
		//System.out.println("in getMatchSuggestions()");
		
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
			//System.out.println("prevMatch size is "+ prevMatch.length );
			for (int i = 0 ; i < prevMatch.length; i++) {
				//System.out.println("checking if word "+ prevMatch[i] + " is in the list");
				if (list.contains(prevMatch[i])) {
					list.remove(prevMatch[i]) ;
					//System.out.println("removed from trie result word "+ prevMatch[i]);
				}
			}
		}
		else {
			//System.out.println("prevMatch[] is null");
		}
		
		// make PP array for easy sort
		PrefixPair[] pArr = new PrefixPair[list.size()] ;
		boolean successfulTrieResult = true ;
		int i = 0 ;  
		for (String w : list) {  
			System.out.println("This is String inside the Prefix Pair:" + w); 
			
			if (wordTable.get(w) != null) {
				int fr =  wordTable.get(w);
				pArr[i] = new PrefixPair(w, fr) ; 
				i ++ ;
			} else {  
				System.out.println("couldn't find word " + w + " return from trie search");
				successfulTrieResult = false ;              
			}  
		}
		
		if (successfulTrieResult) {
			return sortSuccessorsByFreq (pArr, howMany) ;
		} else {
			return new String[0] ;     
		}
 
	}

	public HashMap<String, PrefixPair[]> getSuccessorMap() {
		return successorMap;
	}

	
	

}
