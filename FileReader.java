import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


/**
 * Reads in and parses a file, populates frequency table and map of words to their successors.
 * Successors are only considered if they are in the same sentence.
 * That is, if the previous two sentences occur consecutively in a file, 
 * 	"successors" is not considered a successor of "successors."
 * @author tierra
 *
 */
public class FileReader implements IFileReader {

    private String filename;
    private ArrayList<String> lines;
    private HashMap <String, Integer> words;
    private HashMap<String, HashMap<String, Integer>> successors;
    private HashMap<String, Integer> hm; //this is the map that is within successors

    /**
     * Opens a file for reading and populating frequency and successor maps
     * @param fn the name of the file
     * @throws FileNotFoundException if the file is not found
     */
    public FileReader (String fn) throws FileNotFoundException {
	filename = fn;
	lines = new ArrayList<>();
	words = new HashMap <String, Integer>();
	successors = new HashMap<String, HashMap<String, Integer>> ();
	readFile();
    }

    /**
     * Gets the frequency table
     * @return words the words in the table and their frequencies
     */
    public HashMap <String, Integer> getWords(){
	return words;
    }

    /**
     * Gets the successor map
     * @return successors the pairs of words in the table and their frequencies
     */
    public HashMap <String, HashMap<String, Integer>> getSuccessors(){
	return successors;
    }


    private void readFile() throws FileNotFoundException {

	Scanner sc = new Scanner(new File(filename));

	while(sc.hasNext()) {
	    String line = sc.next().replace(".", " .").replace("!", " !").replace("?", " ?");
	    //above, we add space around the punctuation, so the punc'n can be considered a "word"
	    String[] tokens = line.split(" ");
	    for(String token : tokens){
		token = token.trim().replaceAll("[^a-zA-Z0-9'.?!]", "");
		lines.add(token.toLowerCase());
	    }
	}
	sc.close();

	for(String line : lines) {
	    String[] tokens = line.split(" ");
	    for(String token : tokens){
		if (isPunctuation(token)) continue;
		makeFrequencyTable(token);
	    }
	}

	for (int i = 0 ; i < lines.size()-1 ; i++) {
	    if (isPunctuation(lines.get(i+1)) || isPunctuation(lines.get(i))) continue;
	    //if "previous" or "word" is punctuation, we won't call makeSuccMap() on it
	    makeSuccessorMap (lines.get(i+1), lines.get(i));
	}
    }

    //helper so we don't have to keep saying if x.equals . || ! || ?
    private boolean isPunctuation (String word) {
	if (word.equals(".") || word.equals("!") || word.equals("?")) return true;
	return false;
    }

    /**
     * Creates mapping of word to its frequency 
     * @param word a word in the file
     */
    public void makeFrequencyTable (String word) {

	if (words.containsKey(word)) {
	    words.put(word, words.get(word) + 1);
	} else {
	    words.put(word, 1);
	}
    }

    /**
     * Creates mapping of word to its successor and how frequently this pairing occurs
     * @param word a word in the file
     * @param previous the word that precedes {@link word}
     */
    public void makeSuccessorMap (String word, String previous) {

	if (successors.containsKey(previous)) {
	    hm = successors.get(previous);
	    if (hm.containsKey(word)) {
		hm.put(word, hm.get(word)+1);
	    } else {
		hm.put(word, 1);
	    }
	} else {
	    hm = new HashMap<String, Integer>();
	    hm.put(word, 1);
	    successors.put(previous, hm);
	}
    }
}

