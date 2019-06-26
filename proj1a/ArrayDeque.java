public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    private int addHelper(int n) {
        return (n + 1) % items.length;
    }

    private int minusHelper(int n) {
        return (n + items.length - 1) % items.length;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusHelper(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = addHelper(nextLast);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            return;
        }
        int index = addHelper(nextFirst);
        for (int i = 0; i < size - 1; i++) {
            System.out.print(items[index] + " ");
            index = addHelper(index);
        }
        System.out.print(items[index]);
    }


    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int start = addHelper(nextFirst);
        T removeItem = items[start];
        items[start] = null;
        nextFirst = start;
        size -= 1;

        if (items.length >= 16 && size * 4 < items.length) {
            resize(items.length / 2);
        }

        return removeItem;

    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int end = minusHelper(nextLast);
        T removeItem = items[end];
        items[end] = null;
        nextLast = end;
        size -= 1;

        if (items.length >= 16 && size * 4 < items.length) {
            resize(items.length / 2);
        }

        return removeItem;
    }

    public T get(int index) {
        int target = addHelper(nextFirst);
        for (int i = 0; i < index; i++) {
            target = addHelper(target);
        }
        return items[target];
    }

    private void resize(int capacity) {
        int start = addHelper(nextFirst);
        int end = minusHelper(nextLast);
        T[] newArr = (T[]) new Object[capacity];
        if (start < end) {
            System.arraycopy(items, start, newArr, capacity / 4, size);
            nextFirst = capacity / 4 - 1;
            nextLast = capacity / 4 + size;
            items = newArr;
            return;
        }
        if (start > end) {
            System.arraycopy(items, start, newArr, capacity / 4, size - start);
            System.arraycopy(items, 0, newArr, capacity / 4 + size - start, end + 1);
            nextFirst = capacity / 4 - 1;
            nextLast = capacity / 4 + size;
            items = newArr;
        }
    }
}
