import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PrefixPairTest {
    
    @Test	
    public void testByFreq() {
	
    }
    
    @Test
    public void testCompareTo() {
	PrefixPair a = new PrefixPair ("apple", 19);
	PrefixPair b = new PrefixPair ("banana", 13);
	assertEquals (6, a.compareTo(b));
	
	PrefixPair c = new PrefixPair ("cookie", 29);
	PrefixPair d = new PrefixPair ("donut", 39);
	assertEquals (-10, c.compareTo(d));
	
	PrefixPair e = new PrefixPair ("elefante", 4);
	PrefixPair f = new PrefixPair ("files", 4);
	assertEquals (0, e.compareTo(f));
    }
    
}
