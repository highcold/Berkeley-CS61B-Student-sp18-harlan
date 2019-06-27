import org.junit.Test;
import static org.junit.Assert.*;


public class testOffByN {

    static CharacterComparator offByN = new OffByN(3);

    @Test
    public void testEqualChars() {
        assertTrue(offByN.equalChars('s', 'v'));
        assertTrue(offByN.equalChars('d', 'a'));
        assertFalse(offByN.equalChars('a', 'c'));
    }
}
