import java.util.HashMap;

/**
 * Reads in and parses a file, populates frequency table and map of words to their successors.
 * Successors are only considered if they are in the same sentence.
 * That is, if the previous two sentences occur consecutively in a file, 
 * 	"successors" is not considered a successor of "successors."
 * @author tierra
 *
 */
public interface IFileReader {
    
    /**
     * Gets the frequency table
     * @return map of words to their frequencies
     */
    public HashMap <String, Integer> getWords();
    
    /**
     * Gets the successor table
     * @return map of words to their successor and how frequently this pairing occurs
     */
    public HashMap <String, HashMap<String, Integer>> getSuccessors();
    
    /**
     * Creates mapping of word to its frequency 
     * @param word a word in the file
     */
    public void makeFrequencyTable (String word);
    
    /**
     * Creates mapping of word to its successor and how frequently this pairing occurs
     * @param word a word in the file
     * @param previous the word that precedes param word
     */
    public void makeSuccessorMap (String word, String previous);

}
