import java.util.Comparator;

public class PrefixPair implements Comparable<PrefixPair> { 

    private String name;
    private int frequency;

    public PrefixPair (String n, int f) {
	name = n;
	frequency = f;
    }
    
    public String getName() {
	return name;
    }
    
    public int getFrequency() {
	return frequency;
    }


    public static Comparator<PrefixPair> byFreq() {
	return new ByFreq();

    }
    
    private static class ByFreq implements Comparator<PrefixPair>{

	//for sorting by frequency
	@Override
	public int compare(PrefixPair o1, PrefixPair o2) {
	    return -(o1.frequency - o2.frequency);
	}
	
    }


    //for sorting lexicographically
    @Override
    public int compareTo(PrefixPair o) {
	return (name.compareTo(o.name));
    }
    
    @Override
    public String toString() {
        return name + ", " + frequency;
    }
}
