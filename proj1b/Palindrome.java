public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }
        Deque<Character> deque = new ArrayDeque<>();
        for (Character c : word.toCharArray()) {
            deque.addLast(c);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        int size = word.length();
        if (word == null || size == 0 || size == 1) {
            return true;
        }
        Deque<Character> deque = wordToDeque(word);
        for (int i = 0; i < size / 2; i++) {
            if (deque.get(i) != deque.get(size - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return true;
        }
        Deque deque = wordToDeque(word);
        return isPalindromeHelper(deque, cc);
    }

    public boolean isPalindromeHelper(Deque deque, CharacterComparator cc) {
        if (deque == null || deque.size() <= 1) {
            return true;
        }
        return cc.equalChars((char)deque.removeFirst(), (char)deque.removeLast()) && isPalindromeHelper(deque, cc);
    }


    public boolean isPalindromeRecursive(String word) {
        if (word == null) {
            return true;
        }
        Deque deque = wordToDeque(word);
        return isPalindromeHelper(deque);
    }

    public boolean isPalindromeHelper(Deque deque) {
        if (deque == null || deque.size() <= 1 ) {
            return true;
        }
        return deque.removeFirst() == deque.removeLast() && isPalindromeHelper(deque);
    }


}
