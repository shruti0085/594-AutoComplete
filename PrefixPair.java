
public class PrefixPair implements Comparable<PrefixPair> { 
    //it's really a private class, will be nested in AutoComplete

    private String name;
    private int frequency;

    public PrefixPair (String n, int f) {
	name = n;
	frequency = f;
    }


    public Comparable<PrefixPair> byFreq() {
	// TODO
	return null;

    }

    @Override
    public int compareTo(PrefixPair o) {
	return frequency - o.frequency;
    }
}
