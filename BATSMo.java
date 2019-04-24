	import java.io.InputStream;
	import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
	import java.io.File;
	

	
/**
 * @author Rebecca Iglesias-Flores 
 * 	               The interface for the Becca Alex Tierra Shruti - Model 
 *                 that can be attached to Trie Tree.  the work done in Trie 
 *                 will be via a class that implements this interface. The interface 
 *                 may need to be extended depending on the design of the program.
 *         
 * @param <N>
 *            - the type of nodes maintained by the Trie
 *
 */	
public interface BATSMo  
{
	
	//private Trie[] tr;
	//private HashMap freqT;

    /**
     * Accept a Trie Object in order to build a Trie Tree of mapped prefix suggestions
     * 
     * 
     * @param HashMap of words with frequencies
     * @param HashMap of mapped word to it's top 5 successor predictions
     * 
     */
	public void autocomplete(HashMap<String, Integer> wTable, HashMap<String, HashMap<String, Integer>> predicitons);
	
	 /**
	 * Accept a String that represents the prefix to start searching trie tree
	 * returns the list of the top 5 suggestions created by autocomplete function above()
	 * a list of top five suggestions is returned
	 * 
	 * @param String
	 * @return String[] - List of 5 subsequent prefixes
	 */
	public <T> T[] allPredictions(String prefix);

	
//    /**
//     * Accept a String that represents the prefix to start searching trie tree
//     * returns the list of the top 5 suggestions created by autocomplete function above()
//     * a list of top five suggestions is returned
//     * 
//     * @param String
//     * @return List of 5 subsequent prefixes
//     */
//	public <T> T[] getMatchSuggestions(String prefix);
//
//
//    /**
//     * Accept a string representation of a whole word and creates a HashMap of Frequencies
//     * returns the list of the top 5 suggestions created by autocomplete function above()
//     * a list of top five suggestions is returned
//     * 
//     * @param String
//     * @return List of 5 subsequent prefixes
//     */
//	public void createHashMap(String word);
//
//
//    /**
//     * Sorts the top 5 suggestion from createHashMap()
//     * 
//     * @param Array of Strings populated from getMatchSuggestions
//     * @return A sorted list of the top 5 word frequencies
//     */
//	public <T> T[] sortByFrequency(T[] top5);
}


