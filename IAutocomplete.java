/**
 * @author Alexandra Rumyantseva
 *
 * Description:  a data type that provides autocomplete functionality using
 * 				 given text as training. Includes {@code Trie}, 
 * 				 {@code PrefixPair}, and {@code FileRider} data structures.
 */
public interface IAutocomplete {
	/**
	 * Predict the words that follow the input
	 * @param input is the word or prefix that allows the function to complete it
	 * @return array of possibly following words sorted in descending order of possibility
	 */
	public String[] allPredictions(String input) ;
}