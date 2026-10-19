package datastructure.linkedlist.doublylink;
import java.util.Objects;

public class DoublyLinkedList<T> {
    DoublyLink<T> head, tail;

    public DoublyLinkedList() {
    }

    public void insertFirst(T newItem) {
        Objects.requireNonNull(newItem);

        if (head == null) {
            head = new DoublyLink<>(newItem, null, null);
            tail = head;  // tail only change here initially
            return;
        }

        // Not empty
        DoublyLink<T> newLink = new DoublyLink<>(newItem, head, null);

        head.previous = newLink;
        head = newLink;


    }

    public void insertLast(T newItem) {
        Objects.requireNonNull(newItem);

        if (head == null) {
            head = new DoublyLink<>(newItem, null, null);
            tail = head;  // tail only change here initially
            return;
        }

        // Not empty
        DoublyLink<T> newLink = new DoublyLink<>(newItem, null, tail);

        tail.next = newLink;
        tail = newLink;

    }

    public void insertAt(int index, T newItem) {
        if (head == null) {
            throw new RuntimeException("Please insert an element first");
        }

        Objects.requireNonNull(newItem);

        int size = size();
        int maxIndex = size - 1;
        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException("Index must be in range of 0 to " + size);
        }

        // Inserting at the beginning or at the end
        if (index == 0) {
            insertFirst(newItem);
            return;
        }

        // Inserting at the end
        if (index == maxIndex) {
            insertLast(newItem);
            return;
        }

        // Inserting at the middle
        DoublyLink<T> link = head;
        for (int i = 1; i <= index; i++) {
            link = link.next;  // Get the link at the given index
        }

        DoublyLink<T> newLink = new DoublyLink<>(newItem, link, link.previous);

        link.previous.next = newLink;
        link.previous = newLink;


    }

    public boolean deleteFirst() {
        return false;
    }

    public boolean deleteLast() {
        return false;

    }

    public boolean deleteAt(int index) {
        return false;
    }

    public boolean deleteKey(T key) {
        return false;
    }

    public int find(T key) {
        return -1;
    }

    public boolean isEmpty() {
        return false;
    }

    public void displayForward() {

    }

    public void displayBackward() {

    }


    private int size() {
        DoublyLink<T> link = head;          // Start traversing from the head
        int size = 0;

        while (link != null) {
            size++;
            link = link.next;
        }

        return size;
    }

    @Override
    public String toString() {
        return (head != null) ? "DoublyLinkedList{" + "head=" + head.data + ", tail=" + tail.data + '}' : "Empty";
    }
}
