import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class PrefixPairTest {
    
    @Test //to test sorting by frequency	
    public void testByFreq() {
	PrefixPair a = new PrefixPair ("apple", 19);
	PrefixPair b = new PrefixPair ("banana", 13);
	PrefixPair c = new PrefixPair ("cookie", 29);
	PrefixPair d = new PrefixPair ("donut", 39);
	PrefixPair e = new PrefixPair ("elefante", 4);
	PrefixPair[] pairs = { a , b , c , d , e };
	Arrays.sort(pairs, PrefixPair.byFreq());
	//the array after sorting by frequency is:
	assertEquals("elefante, 4", pairs[0].toString());
	assertEquals("banana, 13", pairs[1].toString());
	assertEquals("apple, 19", pairs[2].toString());
	assertEquals("cookie, 29", pairs[3].toString());
	assertEquals("donut, 39", pairs[4].toString());
    }

    
    @Test //to test lexicographic sorting
    public void anotherTest() {
	PrefixPair a = new PrefixPair ("donut", 19);
	PrefixPair b = new PrefixPair ("elefante", 13);
	PrefixPair c = new PrefixPair ("cookie", 29);
	PrefixPair d = new PrefixPair ("apple", 39);
	PrefixPair e = new PrefixPair ("banana", 4);
	PrefixPair[] pairs = { a , b , c , d , e };
	Arrays.sort(pairs);
	//the array after sorting lexicographically is:
	assertEquals("apple", pairs[0].getName());
	assertEquals("banana", pairs[1].getName());
	assertEquals("cookie", pairs[2].getName());
	assertEquals("donut", pairs[3].getName());
	assertEquals("elefante", pairs[4].getName());
    }
 
}
