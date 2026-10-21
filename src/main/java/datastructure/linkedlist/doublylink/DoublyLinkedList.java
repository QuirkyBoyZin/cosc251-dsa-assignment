package datastructure.linkedlist.doublylink;

import java.util.Objects;

public class DoublyLinkedList<T> {
    private int count;
    private DoublyLink<T> head;
    private DoublyLink<T> tail;

    // Time Complexity: O(1)
    public DoublyLinkedList() {
        this.count = 0;
        this.head = null;
        this.tail = null;
    }

    // Time Complexity: O(N) where N is the number of elements in the list
    private DoublyLink<T> traverseByIndex(int index) {
        DoublyLink<T> current;
        if (index < count / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = count - 1; i > index; i--) {
                current = current.previous;
            }
        }
        return current;
    }

    // Time Complexity: O(1)
    public T peekFirst() {
        if (head == null) return null;
        return head.data;
    }

    // Time Complexity: O(1)
    public T peekLast() {
        if (tail == null) return null;
        return tail.data;
    }

    // Time Complexity: O(1)
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    // Time Complexity: O(1)
    public int size() {
        return count;
    }

    // Time Complexity: O(1)
    public void insertFirst(T newItem) {
        Objects.requireNonNull(newItem, "Item must not be null");

        DoublyLink<T> newLink = new DoublyLink<>(newItem, head, null);

        if (head == null) {
            tail = newLink;
        } else {
            head.previous = newLink;
        }

        head = newLink;
        count++;
    }

    // Time Complexity: O(1)
    public void insertLast(T newItem) {
        Objects.requireNonNull(newItem, "Item must not be null");

        DoublyLink<T> newLink = new DoublyLink<>(newItem, null, tail);

        if (tail == null) {
            head = newLink;
        } else {
            tail.next = newLink;
        }

        tail = newLink;
        count++;
    }

    // Time Complexity: O(N) where N is the number of elements in the list
    public void insertAt(int index, T newItem) {
        Objects.requireNonNull(newItem, "Item must not be null");

        // Must account for the fact that we can insert at the end of the list (index == count)
        // Remember that count - 1 == index is the last element
        // So count == index is a valid position to insert

        // count == 5;
        // index == 5; // valid position to insert at the end of the list
        if (index < 0 || count < index) {
            throw new IndexOutOfBoundsException("Index out of bounds. Valid range: 0 to " + count);
        }

        if (index == 0) {
            insertFirst(newItem);
            return;
        }

        if (index == count) {
            insertLast(newItem);
            return;
        }

        DoublyLink<T> current = traverseByIndex(index);

        DoublyLink<T> newLink = new DoublyLink<>(newItem, current, current.previous);

        current.previous.next = newLink;
        current.previous = newLink;

        count++;
    }

    // Time Complexity: O(1)
    public boolean deleteFirst() {
        if (head == null) return false;

        DoublyLink<T> removedLink = head;
        head = head.next;

        if (head == null) {
            tail = null;
        } else {
            head.previous = null;
        }

        removedLink.next = null;
        count--;
        return true;
    }

    // Time Complexity: O(1)
    public boolean deleteLast() {
        if (head == null) return false;

        DoublyLink<T> removedLink = tail;
        tail = tail.previous;

        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }

        removedLink.previous = null;
        count--;
        return true;
    }

    // Time Complexity: O(N) where N is the number of elements in the list
    public boolean deleteAt(int index) {
        if (index < 0 || index >= count) {
            return false;
        }

        if (index == 0) return deleteFirst();
        if (index == count - 1) return deleteLast();

        DoublyLink<T> removedLink = traverseByIndex(index);

        removedLink.previous.next = removedLink.next;
        removedLink.next.previous = removedLink.previous;

        removedLink.next = null;
        removedLink.previous = null;

        count--;
        return true;
    }

    // Time Complexity: O(N) where N is the number of elements in the list
    public boolean deleteKey(T key) {
        Objects.requireNonNull(key, "Key must not be null");

        if (head == null) return false;

        DoublyLink<T> current = head;
        while (!current.data.equals(key)) {
            current = current.next;

            if (current == null) return false;
        }

        if (current == head) return deleteFirst();
        if (current == tail) return deleteLast();

        current.previous.next = current.next;
        current.next.previous = current.previous;

        current.next = null;
        current.previous = null;

        count--;
        return true;
    }

    // Time Complexity: O(N) where N is the number of elements in the list
    public int find(T key) {
        Objects.requireNonNull(key, "Key must not be null");

        DoublyLink<T> current = head;
        for (int i = 0; current != null; i++) {
            if (current.data.equals(key)) return i;
            current = current.next;
        }
        return -1;
    }

    // O(N) where N is the number of elements in the list
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DoublyLink<T> current = head;
        while (current != null) {
            if (current.next == null) {
                sb.append(current.data);
            } else {
                sb.append(current.data).append(" <-> ");
            }
            current = current.next;
        }
        return sb.toString();
    }

    // O(N) where N is the number of elements in the list
    public void displayForward() {
        DoublyLink<T> current = head;
        System.out.print("Displaying forward: Head [");
        while (current != null) {
            if (current.next != null) {
                System.out.print(current.data + " <-> ");
            } else {
                System.out.print(current.data);
            }
            current = current.next;
        }
        System.out.println("] Tail");
    }

    // O(N) where N is the number of elements in the list
    public void displayBackward() {
        DoublyLink<T> current = tail;
        System.out.print("Displaying backward: Tail [");
        while (current != null) {
            if (current.previous != null) {
                System.out.print(current.data + " <-> ");
            } else {
                System.out.print(current.data);
            }
            current = current.previous;
        }
        System.out.println("] Head");
    }
}