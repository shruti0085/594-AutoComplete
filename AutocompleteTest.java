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

		String[] result1 = null; 
		 
		Autocomplete auto = new Autocomplete(wTable, successors) ; 
		result1 = auto.allPredictions("blah blah apple p");  
    
	}
	
	@Test
	public void test_isWord_Line244() { 
		
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

		String[] result1 = null; 
		 
		Autocomplete auto = new Autocomplete(wTable, successors) ; 
		result1 = auto.allPredictions("a");   
    
	} 
	
	@Test
	public void test1of2_isWord_elseStatements() { 
		
		HashMap<String, Integer> wTable = new HashMap<String, Integer>() ;
		wTable.put("bike", 5) ;
		wTable.put("apply", 2) ;
		wTable.put("juice", 8) ;
		wTable.put("pie", 7) ;
		wTable.put("please", 10) ;
		wTable.put("is", 100) ;   
		
		HashMap<String, Integer> bikesuccessors = new HashMap<String, Integer> () ;
		bikesuccessors.put("chain", 1) ;
		bikesuccessors.put("derraileur", 2) ;
		bikesuccessors.put("handlebars", 3) ;
		bikesuccessors.put("spokes", 4) ;
		bikesuccessors.put("stem", 5) ;  
		
		HashMap<String, HashMap<String, Integer>> successors = new HashMap<>() ;
		successors.put("bike", bikesuccessors) ;

		String[] res1 = null;
		String[] res2 = null; 
		 
		Autocomplete auto = new Autocomplete(wTable, successors) ;
		//res2 = auto.allPredictions("bike cha");  
		res1 = auto.allPredictions("bike .");     
    
	}
//	
//	@Test
//	public void test2of2_isWord_elseStatements_line258() { //I can't figure this one out.
//		
//		HashMap<String, Integer> wTable = new HashMap<String, Integer>() ;
//		wTable.put("bike", 5) ;
//		wTable.put("apply", 2) ;
//		wTable.put("juice", 8) ;
//		wTable.put("pie", 7) ;
//		wTable.put("please", 10) ;
//		wTable.put("is", 100) ;    
//		
//		HashMap<String, Integer> bikesuccessors = new HashMap<String, Integer> () ;
//		bikesuccessors.put("chain", 1) ;
//		bikesuccessors.put("derraileur", 2) ;
//		bikesuccessors.put("handlebars", 3) ;
//		bikesuccessors.put("spokes", 4) ;
//		bikesuccessors.put("stem", 5) ;  
//		
//		HashMap<String, HashMap<String, Integer>> successors = new HashMap<>() ;
//		successors.put("bike", bikesuccessors) ;
//
//		String[] res1 = null;
//		 
//		Autocomplete auto = new Autocomplete(wTable, successors) ;
//		//res2 = auto.allPredictions("bike cha");  
//		res1 = auto.allPredictions(" ");       
//     
//	}
	
	@Test
	public void test_sortSuccesorsByFreq() { 
		
		HashMap<String, Integer> wTable = new HashMap<String, Integer>() ;
		wTable.put("xray", 1) ; 
 
		
		HashMap<String, Integer> xraysuccessors = new HashMap<String, Integer> () ;
		xraysuccessors.put("vision", 1) ;

		
		HashMap<String, Integer> visionsuccessors = new HashMap<String, Integer> () ;
		visionsuccessors.put("anomaly", 3) ; 
		visionsuccessors.put("airplane", 3) ;
		visionsuccessors.put("after", 3) ;		
		visionsuccessors.put("through", 3) ;		
		visionsuccessors.put("inspite", 3) ;	
		visionsuccessors.put("figure", 3) ;		 
		
		HashMap<String, HashMap<String, Integer>> successors = new HashMap<>() ;
		successors.put("xray", xraysuccessors) ;
		successors.put("vision", visionsuccessors) ;

		String[] res1 = null;    
		  
		Autocomplete auto = new Autocomplete(wTable, successors) ;
		//res2 = auto.allPredictions("bike cha");  
		res1 = auto.allPredictions("vision ");                       
      
	}  
	
	@Test
	public void getMatchSuggestions() {  
		 
		HashMap<String, Integer> wTable = new HashMap<String, Integer>() ;
		wTable.put("8approach", 1) ;    
		wTable.put("ask", 1) ;       
		
		HashMap<String, Integer> approachsuccessors = new HashMap<String, Integer> () ;
		approachsuccessors.put("firmly", 5) ;
		approachsuccessors.put("aggressively", 2) ; 

		
		HashMap<String, Integer> asksuccessors = new HashMap<String, Integer> () ;
		asksuccessors.put("for", 3) ; 
		asksuccessors.put("him", 2) ;  
		 
		
		HashMap<String, HashMap<String, Integer>> successors = new HashMap<>() ;
		successors.put("8approach", approachsuccessors) ;
		successors.put("ask", asksuccessors) ;

		String[] res1 = null;    
		    
		Autocomplete auto = new Autocomplete(wTable, successors) ;
		res1 = auto.allPredictions("8approach");                            
      
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
		
		//String[] result1 = null;
		
		Autocomplete b = new Autocomplete(h1, h2);  
		//result1 = b.allPredictions("blah blah apple p"); 
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
		
		//String[] res = null;
		Autocomplete c = new Autocomplete(h1, successors);  
		//res = c.allPredictions("juice ");
		      
    } 

}
