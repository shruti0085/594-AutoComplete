import static org.junit.Assert.*;

import org.junit.Test;

import java.util.HashMap;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
		//System.out.println("app");
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp[i], resultApp[i]) ;
		} 
		
		resultApp = auto.allPredictions("apple ") ;
		String[] wantApp2 = {"juice", "pie", "please"} ;
		//System.out.println("apple ");
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp2[i], resultApp[i]) ;
		}
		
		resultApp = auto.allPredictions("apple p") ;
		String[] wantApp3 = {"pie", "please"} ;
		//System.out.println("apple p");
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp3[i], resultApp[i]) ;
		} 
		
		//What does this test for?
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
		
		// test empty input, this should fail
		resultApp = auto.allPredictions("") ;
		String[] wantApp6 = new String[0] ;
		for (int i = 0; i < resultApp.length; i++) {
			assertEquals(wantApp6[i], resultApp[i]) ;
		} 

	}
	
	@Test
	public void test_buildSuccessorMap_ifTo() {  
		
		HashMap<String, Integer> wTable = new HashMap<String, Integer>() ;
		wTable.put("to", 5) ;
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
		
		HashMap<String, HashMap<String, Integer>> successors = new HashMap<>() ;
		successors.put("to", appleSuccessors) ;
		successors.put("apply", applySuccessors) ;
		successors.put("juice", juiceSuccessors) ;
		successors.put("please", pleaseSuccessors) ;
		successors.put("pie", pieSuccessors) ;
		successors.put("is", isSuccessors) ;		   
		
		Autocomplete auto = new Autocomplete(wTable, successors) ; 
	}
	
	@Test
	public void test_allPredictions_Line113() {
		
		HashMap<String, Integer> wTable = new HashMap<String, Integer>() ;
		wTable.put("apple ", 5) ;
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
		isSuccessors.put("apple ", 60) ;
		isSuccessors.put("juice", 30) ;
		isSuccessors.put("pie", 10) ;
		
		HashMap<String, HashMap<String, Integer>> successors = new HashMap<>() ;
		successors.put("apple ", appleSuccessors) ;
		successors.put("apply", applySuccessors) ;
		successors.put("juice", juiceSuccessors) ;
		successors.put("please", pleaseSuccessors) ;
		successors.put("pie", pieSuccessors) ;
		successors.put("is", isSuccessors) ;
		
		//getSuggestions
		//allPredictions
		
		Autocomplete auto = new Autocomplete(wTable, successors) ;

	    // get all matching terms 
	    String[] allResults = auto.allPredictions("");    
	}
	
	@Test
	public void test_allPredictions_Line119() {
		
		HashMap<String, Integer> wTable = new HashMap<String, Integer>() ;
		wTable.put("apple ", 5) ;
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
		isSuccessors.put("apple ", 60) ;
		isSuccessors.put("juice", 30) ;
		isSuccessors.put("pie", 10) ;
		
		HashMap<String, HashMap<String, Integer>> successors = new HashMap<>() ;
		successors.put("apple ", appleSuccessors) ;
		successors.put("apply", applySuccessors) ;
		successors.put("juice", juiceSuccessors) ;
		successors.put("please", pleaseSuccessors) ;
		successors.put("pie", pieSuccessors) ;
		successors.put("is", isSuccessors) ;
		
		//getSuggestions
		//allPredictions
		
		Autocomplete auto = new Autocomplete(wTable, successors) ;

	    // get all matching terms 
	    String[] allResults = auto.allPredictions(" ");    
	} 
	
	@Test
	public void test_PrefixPair_Line138() {
		
		HashMap<String, Integer> wTable = new HashMap<String, Integer>() ;
		wTable.put("apple ", 5) ;
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
		isSuccessors.put("apple ", 60) ;
		isSuccessors.put("juice", 30) ;
		isSuccessors.put("pie", 10) ;
		
		HashMap<String, HashMap<String, Integer>> successors = new HashMap<>() ;
		successors.put("apple ", appleSuccessors) ;
		successors.put("apply", applySuccessors) ;
		successors.put("juice", juiceSuccessors) ;
		successors.put("please", pleaseSuccessors) ;
		successors.put("pie", pieSuccessors) ;
		successors.put("is", isSuccessors) ;
		 
		//getSuggestions
		//allPredictions
		
		Autocomplete auto = new Autocomplete(wTable, successors) ;
		PrefixPair[] succ = auto.getSuccessorMap().get("apple");

	    // get all matching terms 
	    String[] allResults = auto.allPredictions(" ");     
	}
	
	@Test
	public void test_nSuggestions_Line186() { 
		
		HashMap<String, Integer> wTable = new HashMap<String, Integer>() ;
		wTable.put("apple ", 5) ;
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

		
		HashMap<String, Integer> applySuccessors = new HashMap<String, Integer> () ;
		applySuccessors.put("please", 1) ;
		applySuccessors.put("all", 4) ;
		applySuccessors.put("to", 3) ;
		applySuccessors.put("for", 8) ;
		applySuccessors.put("on", 3) ;

		
		HashMap<String, Integer> juiceSuccessors = new HashMap<String, Integer> () ;
		juiceSuccessors.put("is", 4) ;
		juiceSuccessors.put("please", 4) ;
		juiceSuccessors.put("mix", 4) ;
		juiceSuccessors.put("for", 4) ;
		juiceSuccessors.put("from", 4) ;

		
		HashMap<String, Integer> pleaseSuccessors = new HashMap<String, Integer> () ;
		pleaseSuccessors.put("is", 4) ;
		pleaseSuccessors.put("please", 4) ;
		pleaseSuccessors.put("mix", 4) ;
		pleaseSuccessors.put("for", 4) ;
		pleaseSuccessors.put("from", 4) ;
		
		HashMap<String, Integer> pieSuccessors = new HashMap<String, Integer> () ;
		pieSuccessors.put("is", 4) ;
		pieSuccessors.put("please", 4) ;
		pieSuccessors.put("mix", 4) ;
		pieSuccessors.put("for", 4) ;
		pieSuccessors.put("from", 4) ; 
		
		HashMap<String, Integer> isSuccessors = new HashMap<String, Integer> () ;
		isSuccessors.put("apple ", 60) ;
		isSuccessors.put("juice", 30) ;
		isSuccessors.put("pie", 10) ;
		isSuccessors.put("happy", 10) ;
		isSuccessors.put("green", 10) ;
		isSuccessors.put("true", 10) ;
		
		HashMap<String, HashMap<String, Integer>> successors = new HashMap<>() ;
		successors.put("apple ", appleSuccessors) ;
		successors.put("apply", applySuccessors) ;
		successors.put("juice", juiceSuccessors) ;
		successors.put("please", pleaseSuccessors) ;
		successors.put("pie", pieSuccessors) ;
		successors.put("is", isSuccessors) ;  
		
		//getSuggestions
		//allPredictions 
		String[] result1 = null; 
		 
		Autocomplete auto = new Autocomplete(wTable, successors) ; 
		//PrefixPair[] succ = auto.getSuccessorMap().get("apple"); 
    
	}	  
	 
    /**
     * @throws IllegalStateException
     */
    @Test(expected = IllegalStateException.class) 
    public void test1of2_Autocomplete_IllegalStateException()
    {

		HashMap<String, Integer> h1 = new HashMap<String, Integer>();
		h1.put("apple", 5) ;
		h1.put("apply", 2) ;     
   
		
		HashMap<String, HashMap<String, Integer>> h2 = null; 
		
		Autocomplete b = new Autocomplete(h1, h2);     		        
    }   
    
    /**
     * @throws IllegalStateException
     */
    @Test(expected = IllegalStateException.class) 
    public void test2of2_Autocomplete_IllegalStateException()
    {

		HashMap<String, Integer> h1 = null;
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
		
		HashMap<String, HashMap<String, Integer>> successors = new HashMap<>() ;
		successors.put("apple", appleSuccessors) ;
		successors.put("apply", applySuccessors) ;
		successors.put("juice", juiceSuccessors) ;
		successors.put("please", pleaseSuccessors) ;
		successors.put("pie", pieSuccessors) ;
		successors.put("is", isSuccessors) ; 
		
		Autocomplete c = new Autocomplete(h1, successors);      
		      
    } 

}
