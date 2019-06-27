public class OffByN implements CharacterComparator {
    int n;

    OffByN(int n) {
        this.n = n;
    }

    public boolean equalChars(char x, char y) {
        return x - y == n || y - n == n;
    }
}
