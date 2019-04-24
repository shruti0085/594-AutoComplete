package newAuto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class AutocompleteTest {

	@Test
	void testAllPredictions() {
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
		appleSuccessors.put("please", 1) ;
		
		HashMap<String, Integer> juiceSuccessors = new HashMap<String, Integer> () ;
		juiceSuccessors.put("is", 3) ;
		juiceSuccessors.put("please", 4) ;
		
		HashMap<String, Integer> pleaseSuccessors = new HashMap<String, Integer> () ;
		
		HashMap<String, Integer> pieSuccessors = new HashMap<String, Integer> () ;
		
		HashMap<String, Integer> isSuccessors = new HashMap<String, Integer> () ;
		isSuccessors.put("apple", 60) ;
		isSuccessors.put("juice", 30) ;
		isSuccessors.put("pie", 10) ;
		
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
		assertEquals(wantApp, resultApp) ;
		
		resultApp = auto.allPredictions("apple ") ;
		String[] wantApp2 = {"juice", "pie", "please"} ;
		assertEquals(wantApp2, resultApp) ;
		
		resultApp = auto.allPredictions("apple p") ;
		String[] wantApp3 = {"pie", "please"} ;
		assertEquals(wantApp3, resultApp) ;
		
		resultApp = auto.allPredictions("apple pie") ;
		String[] wantApp4 = {} ;
		assertEquals(wantApp4, resultApp) ;
		
		// TO DO: count for cases "pie pie" - should not be there
		resultApp = auto.allPredictions("apple pie p") ;
		String[] wantApp5 = {"please"} ;
		assertEquals(wantApp5, resultApp) ;
	}

}
