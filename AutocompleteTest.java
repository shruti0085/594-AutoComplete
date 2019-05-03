import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class AutocompleteTest { 

	@Test
	public void testAllPredictions() {
		HashMap<String, Integer> wTable = new HashMap<String, Integer>() ;
		wTable.put("apple", 5) ;
		wTable.put("apply", 2) ;
		wTable.put("juice", 8) ;
		wTable.put("pie", 7) ;
		wTable.put("please", 10) ;
		wTable.put("is", 100) ;
		
		HashMap<String, Integer> appleSuccessors = new HashMap<String, Integer> () ;
		appleSuccessors.put("juice", 2) ;
		appleSuccessors.put("pie", 2) ;
		appleSuccessors.put("please", 1) ;
		
		HashMap<String, Integer> applySuccessors = new HashMap<String, Integer> () ;
		applySuccessors.put("please", 1) ;
		
		HashMap<String, Integer> juiceSuccessors = new HashMap<String, Integer> () ;
		juiceSuccessors.put("is", 3) ;
		juiceSuccessors.put("please", 4) ;
		
		HashMap<String, Integer> pleaseSuccessors = new HashMap<String, Integer> () ;
		
		HashMap<String, Integer> pieSuccessors = new HashMap<String, Integer> () ;
		
		HashMap<String, Integer> isSuccessors = new HashMap<String, Integer> () ;
		isSuccessors.put("apple", 60) ;
		isSuccessors.put("juice", 30) ;
		isSuccessors.put("pie", 10) ;
		isSuccessors.put("good", 5) ;
		isSuccessors.put("bad", 6) ;
		isSuccessors.put("nice", 3) ;
		
		HashMap<String, HashMap<String, Integer>> successors = new HashMap<>() ;
		successors.put("apple", appleSuccessors) ;
		successors.put("apply", applySuccessors) ;
		successors.put("juice", juiceSuccessors) ;
		successors.put("please", pleaseSuccessors) ;
		successors.put("pie", pieSuccessors) ;
		successors.put("is", isSuccessors) ;		
		
		Autocomplete auto = new Autocomplete(wTable, successors) ;
		
		String[] resultApp = auto.allPredictions("app") ;
		String[] wantApp = {"apple", "apply"} ;
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp[i], resultApp[i]) ;
		}
		
		resultApp = auto.allPredictions("apple ") ;
		String[] wantApp2 = {"juice", "pie", "please"} ;
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp2[i], resultApp[i]) ;
		}
		
		resultApp = auto.allPredictions("apple p") ;
		String[] wantApp3 = {"pie", "please"} ;
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp3[i], resultApp[i]) ;
		} 
		
		resultApp = auto.allPredictions("apple pie ") ;
		String[] wantApp4 = {} ;
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp4[i], resultApp[i]) ;
		} 
		
		resultApp = auto.allPredictions("apple pie p") ;
		String[] wantApp5 = {"please"} ;
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp5[i], resultApp[i]) ;
		}
		
		// test empty input
		resultApp = auto.allPredictions("") ;
		String[] wantApp6 = new String[0] ;
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp6[i], resultApp[i]) ;
		} 
		
		// test a single space input
		resultApp = auto.allPredictions(" ") ;
		String[] wantApp7 = new String[0] ;
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp7[i], resultApp[i]) ;
		} 
		
		// test unknown word
		resultApp = auto.allPredictions("blah ") ;
		String[] wantApp8 = new String[0] ;
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp8[i], resultApp[i]) ;
		} 
		
		// test word + unknown prefix for this word 
		resultApp = auto.allPredictions("apple blah") ;
		String[] wantApp9 = new String[0] ;
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp9[i], resultApp[i]) ;
		}
		
		// test unknown word + prefix for this word 
		resultApp = auto.allPredictions("blah app") ;
		String[] wantApp10 = {"apple", "apply"} ;
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp10[i], resultApp[i]) ;
		}
		
		// test a single letter input
		resultApp = auto.allPredictions("z") ;
		String[] wantApp11 = new String[0] ;
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp11[i], resultApp[i]) ;
		}
		
		// test a last char is unknown punctuation
		resultApp = auto.allPredictions("foo /") ;
		String[] wantApp12 = new String[0] ;
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp12[i], resultApp[i]) ;
		}
		
		// test a last char is known punctuation
		resultApp = auto.allPredictions("foo !") ;
		String[] wantApp13 = new String[0] ;
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp13[i], resultApp[i]) ;
		}
		
		// if found matches are more than desirable size
		resultApp = auto.allPredictions("is ") ;
		String[] wantApp14 = {"apple", "juice", "pie", "bad", "good"} ;
		for (int i = 0; i < resultApp.length; i++) {			
			assertEquals(wantApp14[i], resultApp[i]) ;
		}
		
		// if prefix starts with non-letter symbol
		resultApp = auto.allPredictions("is -0p") ;
		String[] wantApp15 = new String[0] ;
		for (int i = 0; i < resultApp.length; i++) {		
			assertEquals(wantApp15[i], resultApp[i]) ;
		}
		
		// corner case 1 for last index search
		resultApp = auto.allPredictions("apply p") ;
		String[] wantApp16 = {"please", "pie"} ;
		for (int i = 0; i < resultApp.length; i++) {			
			assertEquals(wantApp16[i], resultApp[i]) ;
		}
		
		// corner case 2 for last index search
		resultApp = auto.allPredictions("juice i") ;
		String[] wantApp17 = {"is"} ;
		for (int i = 0; i < resultApp.length; i++) {			
			assertEquals(wantApp17[i], resultApp[i]) ;
		}
		
	}
	
	@Test
	public void testResult1SameSizeAs_nSuggestions() { 
		
		HashMap<String, Integer> wTable = new HashMap<String, Integer>() ;
		wTable.put("apple", 5) ;
		wTable.put("apply", 2) ;
		wTable.put("juice", 8) ;
		wTable.put("pie", 7) ;
		wTable.put("please", 10) ;
		wTable.put("is", 100) ;   
		
		HashMap<String, Integer> appleSuccessors = new HashMap<String, Integer> () ;
		appleSuccessors.put("pear", 1) ;
		appleSuccessors.put("pie", 2) ;
		appleSuccessors.put("please", 3) ;
		appleSuccessors.put("pop", 4) ;
		appleSuccessors.put("pay", 5) ; 
		
		HashMap<String, HashMap<String, Integer>> successors = new HashMap<>() ;
		successors.put("apple", appleSuccessors) ;

		Autocomplete auto = new Autocomplete(wTable, successors) ; 
		String[] result1 = auto.allPredictions("blah blah apple p");  
		String[] wantApp7 = {"pay", "pop", "please", "pie", "pear"} ;
		for (int i = 0; i < result1.length; i++) {
			assertEquals(wantApp7[i], result1[i]) ;
		} 
    
	}
	
	@Test
	public void testNullArgsToConstructor() { 
		
		HashMap<String, Integer> wTable = null ; 
		
		HashMap<String, Integer> appleSuccessors = new HashMap<String, Integer> () ;
		appleSuccessors.put("pear", 1) ;
		appleSuccessors.put("pie", 2) ;
		appleSuccessors.put("please", 3) ;
		appleSuccessors.put("pop", 4) ;
		appleSuccessors.put("pay", 5) ; 
		
		HashMap<String, HashMap<String, Integer>> successors = new HashMap<>() ;
		successors.put("apple", appleSuccessors) ;
		 
		try {
			Autocomplete auto = new Autocomplete(wTable, successors) ; 
			fail("Expected an IllegalArgumentException to be thrown");
		}
		catch (IllegalArgumentException e) {}
		
		successors = null ;
		wTable = new HashMap<String, Integer>() ;
		try {
			Autocomplete auto = new Autocomplete(wTable, successors) ; 
			fail("Expected an IllegalArgumentException to be thrown");
		}
		catch (IllegalArgumentException e) {}
	}

}
