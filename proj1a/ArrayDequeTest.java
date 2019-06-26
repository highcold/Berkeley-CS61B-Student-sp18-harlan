import org.junit.Test;

public class ArrayDequeTest {
    @Test
    public void resizeTest() {
        ArrayDeque<Integer> input = new ArrayDeque();
        input.addLast(1);
        input.addLast(2);
        for (int i = 0; i < 8; i++) {
            input.addLast(i);
        }
        System.out.println();
    }

    @Test
    public void printTest() {
        ArrayDeque<Integer> input = new ArrayDeque();
        input.addLast(11);
        input.addLast(22);
        for (int i = 0; i < 8; i++) {
            input.addLast(i);
        }
        input.printDeque();
    }

    @Test
    public void removeTest() {
        ArrayDeque<Integer> input = new ArrayDeque();
        for (int i = 0; i < 16; i++) {
            input.addLast(i);
        }
        input.printDeque();
        for (int i = 0; i < 13; i++) {
            input.removeFirst();
        }
        System.out.println();
    }

    @Test
    public void get() {
        ArrayDeque<Integer> input = new ArrayDeque();
        for (int i = 0; i < 16; i++) {
            input.addLast(i);
        }
        System.out.println(input.get(0));
    }

}
