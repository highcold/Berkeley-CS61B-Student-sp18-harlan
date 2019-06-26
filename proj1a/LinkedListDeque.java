public class LinkedListDeque<T> {

    private class Node<T> {
        private T item;
        private Node prev;
        private Node next;

        public Node(T item) {
            this.item = item;
        }

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        public Node getRecursiveHelper(int index) {

            if (index == 0) {
                return this.next;
            }
            return this.next.getRecursiveHelper(index - 1);
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T item) {
        Node temp = sentinel.next;
        Node newNode = new Node(sentinel, item, temp);
        sentinel.next = newNode;
        temp.prev = newNode;
        size += 1;
    }

    public void addLast(T item) {
        Node temp = sentinel.prev;
        Node newNode = new Node(temp, item, sentinel);
        sentinel.prev = newNode;
        temp.next = newNode;
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
        Node n = sentinel.next;
        for (int i = 0; i < size - 1; i++) {
            System.out.print(n.item + " ");
            n = n.next;
        }
        System.out.println(n.item);

    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node returnNode = sentinel.next;
        Node temp = sentinel.next.next;
        sentinel.next = temp;
        temp.prev = sentinel;
        returnNode.prev = null;
        returnNode.next = null;
        size -= 1;
        return (T) returnNode.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node returnNode = sentinel.prev;
        Node temp = sentinel.prev.prev;
        sentinel.prev = temp;
        temp.next = sentinel;
        returnNode.prev = null;
        returnNode.next = null;
        size -= 1;
        return (T) returnNode.item;
    }

    public T get(int index) {
        if (size == 0) {
            return null;
        }
        Node indexNode = sentinel.next;
        for (int i = 0; i < index; i++) {
            indexNode = indexNode.next;
        }
        return (T) indexNode.item;
    }

    public T getRecursive(int index) {
        if (size == 0) {
            return null;
        }
        Node returnNode = sentinel.getRecursiveHelper(index);

        return (T) returnNode.item;
    }

}
