package deques;

public class LinkedDeque<T> extends AbstractDeque<T> {
    private int size;
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    Node<T> front;
    Node<T> back;
    Node<T> sentinalFront;
    Node<T> sentinalBack;
    // Feel free to add any additional fields you may need, though.

    public LinkedDeque() {
        size = 0;
        sentinalFront = new Node<T>(null);
        sentinalBack = new Node<T>(null);
        sentinalFront.next = sentinalBack;
        sentinalBack.prev = sentinalFront;
        front = sentinalFront;
        back = sentinalBack;
    }

    public void addFirst(T item) {
        size += 1;
        if (sentinalFront.next == sentinalBack) {
            sentinalFront.next = new Node<T>(item, sentinalFront, sentinalBack);
            sentinalBack.prev = sentinalFront.next;
        } else {
            Node<T> curr = sentinalFront.next;
            sentinalFront.next = new Node<T>(item, sentinalFront, sentinalFront.next);
            curr.prev = sentinalFront.next;
        }
    }

    public void addLast(T item) {
        size += 1;
        if (sentinalFront.next == sentinalBack) {
            sentinalBack.prev = new Node<T>(item, sentinalFront, sentinalBack);
            sentinalFront.next = sentinalBack.prev;
        } else {
            Node<T> curr = sentinalBack.prev;
            sentinalBack.prev = new Node<T>(item, sentinalBack.prev, sentinalBack);
            curr.next = sentinalBack.prev;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T result = sentinalFront.next.value;
        if (sentinalFront.next == sentinalBack.prev) {
            sentinalFront.next = sentinalBack;
            sentinalBack.prev = sentinalFront;
        } else {
            Node<T> curr = sentinalFront.next.next;
            sentinalFront.next = sentinalFront.next.next;
            curr.prev = sentinalFront;
        }
        return result;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T result = sentinalBack.prev.value;
        if (sentinalFront.next == sentinalBack.prev) {
            sentinalBack.prev = sentinalFront;
            sentinalFront.next = sentinalBack;
        } else {
            Node<T> curr = sentinalBack.prev.prev;
            sentinalBack.prev = sentinalBack.prev.prev;
            curr.next = sentinalBack;
        }
        return result;
    }

    public T get(int index) {
        if ((index >= size) || (index < 0)) {
            return null;
        }
        int j = size - 1;
        Node<T> curr = sentinalFront.next;
        Node<T> before = sentinalBack.prev;
        // first case -> even list, second -> odd list
        for (int i = 0; i < size + 1 / 2; i++) {
            if (i == index) {
                return curr.value;
            } else if (j == index) {
                return before.value;
            }
            j--;
            curr = curr.next;
            before = before.prev;
        }
        return null;
    }

    public int size() {
        return size;
    }
}
