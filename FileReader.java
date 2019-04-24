import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileReader {

    private String filename;
    private ArrayList<String> lines;
    private HashMap <String, Integer> words;
    private HashMap<String, HashMap<String, Integer>> successors;

    public FileReader (String fn) throws FileNotFoundException {
	filename = fn;
	lines = new ArrayList<>();
	words = new HashMap <String, Integer>();
	successors = new HashMap<String, HashMap<String, Integer>> ();
	readFile();
    }


    public HashMap <String, Integer> getWords(){
	return words;
    }

    public ArrayList<String> getLines(){
	return lines;
    }

    private void readFile() throws FileNotFoundException {

	Scanner sc = new Scanner(new File(filename));

	//	String w;

	while(sc.hasNextLine()) {
	    String line = sc.nextLine();
	    lines.add(line.toLowerCase());
	}
	sc.close();

	for(String line : lines) {
	    String[] tokens = line.split(" ");
	    for(String token : tokens){
		token = token.trim();
		token = token.replaceAll("[^a-zA-Z0-9']", "");
		if(token.equals("")) continue;
		if (words.containsKey(token)) {
		    words.put(token, words.get(token) + 1);
		} else {
		    words.put(token, 1);
		}
	    }
	}


	//	while (sc.hasNext()) {
	//	    w = sc.next();
	//	    w = w.toLowerCase();
	//
	//	    if (words.containsKey(w)) {
	//		words.put(w, words.get(w) + 1);
	//	    } else {
	//		words.put(w, 1);
	//	    }
	//	}
	//	
	//	sc.close();
	//
    }

    public void makeFrequencyTable (String word) {

	if (words.containsKey(word)) {
	    words.put(word, words.get(word) + 1);
	} else {
	    words.put(word, 1);
	}
    }

    public void makeSuccessorMap (String word, String previous) {

    }

}
