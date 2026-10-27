package datastructure.linkedlist.singlylink;

import java.util.Objects;

public class LinkedList<T> {
    private Link<T> head;
    private Link<T> tail;
    private int count;

    // Time Complexity: O(1)
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    // Time Complexity: O(1)
    public T peekFirst() {
        return head == null ? null : head.data;
    }

    // Time Complexity: O(1)
    public T peekLast() {
        return tail == null ? null : tail.data;
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

        head = new Link<>(newItem, head);

        if (tail == null) {
            tail = head;
        }
        count++;
    }

    // Time Complexity: O(1)
    public void insertLast(T newItem) {
        Objects.requireNonNull(newItem, "Item must not be null");

        Link<T> newLink = new Link<>(newItem);

        if (tail == null) {
            head = newLink;
        } else {
            tail.next = newLink;
        }
        tail = newLink;
        count++;
    }

    // Time Complexity: O(N)
    public void insertAt(int index, T newItem) {
        Objects.requireNonNull(newItem, "Item must not be null");

        // Must account for the fact that we can insert at the end of the list (index == count)
        // Remember that count - 1 == index is the last element
        // So count == index is a valid position to insert
        if (index < 0 || index > count)
            throw new IndexOutOfBoundsException("Index out of bounds. Valid range: 0 to " + count);

        if (index == 0) {
            insertFirst(newItem);
            return;
        }

        if (index == count) {
            insertLast(newItem);
            return;
        }

        Link<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        current.next = new Link<>(newItem, current.next);
        count++;
    }

    // Time Complexity: O(1)
    public boolean deleteFirst() {
        if (head == null) return false;

        Link<T> removedLink = head;
        head = head.next;

        removedLink.next = null;

        if (head == null) {
            tail = null;
        }
        count--;

        return true;
    }

    // Time Complexity: O(N)
    public boolean deleteLast() {
        if (head == null) return false;

        count--;

        if (head == tail) {
            head = null;
            tail = null;
            return true;
        }

        Link<T> current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        tail = current;
        tail.next = null;

        return true;
    }

    // Time Complexity: O(N)
    public boolean deleteAt(int index) {
        if (index < 0 || index >= count) {
            return false;
        }

        if (index == 0) return deleteFirst();
        if (index == count - 1) return deleteLast();

        Link<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        Link<T> removedLink = current.next;
        current.next = removedLink.next;
        removedLink.next = null;

        count--;
        return true;
    }

    // Time Complexity: O(N)
    public boolean deleteKey(T key) {
        Objects.requireNonNull(key, "Key must not be null");

        if (head == null) return false;

        Link<T> previous = null;
        Link<T> current = head;

        while (!current.data.equals(key)) {
            previous = current;
            current = current.next;

            if (current == null) return false;
        }

        if (current == head) return deleteFirst();
        if (current == tail) return deleteLast();

        previous.next = current.next;

        current.next = null;

        count--;
        return true;
    }

    // Time Complexity: O(N)
    public int find(T key) {
        Objects.requireNonNull(key, "Key must not be null");

        Link<T> current = head;
        for (int i = 0; current != null; i++) {
            if (current.data.equals(key)) return i;
            current = current.next;
        }
        return -1;
    }

    // O(N) where N is the number of elements in the list
    @Override
    public String toString() {
        if (head == null) return "";

        StringBuilder sb = new StringBuilder();
        Link<T> current = head;

        while (current.next != null) {
            sb.append(current.data).append(" -> ");
            current = current.next;
        }

        return sb.append(current.data).toString();
    }

    // O(N) where N is the number of elements in the list
    public void display() {
        if (head == null) {
            System.out.println("LinkedList: Head [] Tail");
            return;
        }

        // Using StringBuilder here to avoid repeated access to IO device
        StringBuilder sb = new StringBuilder("LinkedList: Head [");
        Link<T> current = head;

        while (current.next != null) {
            sb.append(current.data).append(" -> ");
            current = current.next;
        }

        sb.append(current.data).append("] Tail");
        System.out.println(sb);
    }
}