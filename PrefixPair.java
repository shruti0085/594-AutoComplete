import java.util.Comparator;

/**
 * 
 * Represents a pairing of a prefix and how frequently it occurs
 * @author tierra, alexandra
 *
 */
public class PrefixPair implements Comparable<PrefixPair> { 

    private String name;
    private int frequency;

    /**
     * Constructs an instance of a PrefixPair, initializing a given prefix and it frequency
     * @param n the prefix
     * @param f the frequency of the prefix
     */
    public PrefixPair (String n, int f) {
	name = n;
	frequency = f;
    }
    
    /**
     * Gets the prefix.
     * @return the prefix
     */
    public String getName() {
	return name;
    }
    
    /**
     * Gets how frequently the prefix occurs.
     * @return the frequency of the prefix
     */
    public int getFrequency() {
	return frequency;
    }

    /**
     * Returns a comparator for comparing prefixes in descending order by frequency.
     * @return comparator for comparing prefixes in descending order by frequency
     */
    public static Comparator<PrefixPair> byFreq() {
	return new ByFreq();

    }
    
    private static class ByFreq implements Comparator<PrefixPair>{

	/**
	 * Compares the frequencies of two PrefixPairs for order
	 * @param o1 the first pair to be compared.
	 * @param o2 the second pair to be compared.
	 * @return a negative integer, zero, or a positive integer as the 
	 * 	first argument is less than, equal to, or greater than the second.
	 */
	@Override
	public int compare(PrefixPair o1, PrefixPair o2) {
	    return -(o1.frequency - o2.frequency);
	}	
    }


    /**
     * Compares two pairs by prefix for sorting lexicographically
     * @param o the PrefixPair to be compared
     * @return a negative integer, zero, or a positive integer as this 
     * 		object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(PrefixPair o) {
	return (name.compareTo(o.name));
    }
    
    /**
     * Returns a string representation of the pair.
     * @return a string representation of the pair.
     */
    @Override
    public String toString() {
        return name + ", " + frequency;
    }
}
