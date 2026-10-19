package datastructure.linkedlist.singlylink;


import java.util.Objects;

public class LinkedList<T> {
    private Link<T> head;
    private Link<T> tail;
    private int count;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public T peekFirst() {
        if (head == null) return null;
        return head.data;
    }

    public T peekLast() {
        if (tail == null) return null;
        return tail.data;
    }

    // Time Complexity: O(1)
    public boolean isEmpty() {
        return head == null || tail == null;
    }

    // Time Complexity: O(1)
    public void insertFirst(T newItem) {
        Objects.requireNonNull(newItem, "Item must not be null");

        Link<T> newLink = new Link<>(newItem);

        if (head == null) {
            tail = newLink;
        } else {
            newLink.next = head;
        }
        head = newLink;
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

    // Time Complexity: O(N) where N is the number of elements in the list
    public int find(T key) {
        Objects.requireNonNull(key, "Key must not be null");

        Link<T> current = head;
        for (int i = 0; current != null; i++) {
            if (current.data.equals(key)) return i;

            current = current.next;
        }
        return -1;
    }

    // Time Complexity: O(N) where N is the number of elements in the list
    public void insertAt(int index, T newItem) {
        Objects.requireNonNull(newItem, "Item must not be null");

        // Must account for the fact that we can insert at the end of the list (index == count)
        // Remember that count - 1 == index is the last element
        // So count == index is a valid position to insert

        // count == 5;
        // index == 5; // valid position to insert at the end of the list
        if (index < 0 || count < index)
            throw new IndexOutOfBoundsException("Index out of bounds. Valid range: 0 to " + count);

        // If index is 0, it means we want to insert at the beginning of the list
        if (index == 0) {
            insertFirst(newItem);
            return;
        }

        // If index is equal to count, it means we want to insert at the end of the list
        if (index == count) {
            insertLast(newItem);
            return;
        }

        Link<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        Link<T> newLink = new Link<>(newItem);
        newLink.next = current.next;
        current.next = newLink;

        count++;

    }

    // Time Complexity: O(1)
    public boolean deleteFirst() {
        if (head == null) return false;
        head = head.next;

        if (head == null) {
            tail = null;
        }
        count--;

        return true;
    }

    // Time Complexity: O(N) where N is the number of elements in the list
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

        current.next = null;
        tail = current;


        return true;
    }

    // Time Complexity: O(N) where N is the number of elements in the list
    public boolean deleteAt(int index) {
        // Must account for the fact that we can delete at the end of the list (index == count - 1)
        if (index < 0 || index >= count) {
            return false;
        }

        if (head == null) return false;

        if (index == 0) {
            return deleteFirst();
        }

        if (index == count - 1) {
            return deleteLast();
        }

        Link<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        current.next = current.next.next;
        count--;

        return true;
    }

    // Time Complexity: O(N) where N is the number of elements in the list
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

        count--;

        return true;
    }

    // Time Complexity: O(N) where N is the number of elements in the list
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Link<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.toString();
    }

    // Time Complexity: O(N) where N is the number of elements in the list
    public void display() {
        Link<T> current = head;
        System.out.print("LinkedList: Head [");
        while (current != null) {
            if (current.next == null) {
                System.out.print(current.data);
            } else {
                System.out.print(current.data + " -> ");
            }
            current = current.next;
        }

        System.out.println("] Tail");
    }
}