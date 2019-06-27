import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String input1 = "gaohan";
        String input2 = "caoac";
        String input3 = "baab";

        assertFalse(palindrome.isPalindrome(input1));
        assertTrue(palindrome.isPalindrome(input2));
        assertTrue(palindrome.isPalindrome(input3));

    }

    @Test
    public void testIsPalindromeRecursive() {
        String input1 = "gaohan";
        String input2 = "caoac";
        String input3 = "baab";

        assertFalse(palindrome.isPalindromeRecursive(input1));
        assertTrue(palindrome.isPalindromeRecursive(input2));
        assertTrue(palindrome.isPalindromeRecursive(input3));

    }

    @Test
    public void testNewIsPalindrome() {
        CharacterComparator cc = new OffByOne();
        String input1 = "gasirbh";
        String input2 = "gasirbh";
        String input3 = "baab";

        assertFalse(palindrome.isPalindrome(input3, cc));
        assertTrue(palindrome.isPalindrome(input1, cc));
        assertTrue(palindrome.isPalindrome(input2, cc));

    }
}
