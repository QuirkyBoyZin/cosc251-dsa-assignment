package datastructure.linkedlist.singlylink;
import java.util.Objects;

public class LinkedList<T> {
    private Link<T> head, tail;

    public Link<T> getHead() {
        return head;
    }

    public Link<T> getTail() {
        return tail;
    }

    public LinkedList() {
    }

    public void insertFirst(T newItem) {
        Objects.requireNonNull(newItem);

        if (isEmpty()) {
            head = new Link<>(newItem, null);
            tail = head;  // tail only change here initially
            return;
        }

        // Not empty
        head = new Link<>(newItem, head);   // head points to new Link, New link point to head's previous link

    }

    public void insertLast(T newItem) {
        Objects.requireNonNull(newItem);

        if (isEmpty()) {
            head = new Link<>(newItem, null);
            tail = head;  // tail only change here initially
            return;
        }

        // Not empty
        Link<T> link = new Link<>(newItem, null);
        tail.setNext(link);
        tail = link;

    }

    public void insertAt(int index, T newItem) {
        if (isEmpty()) {
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
        Link<T> link = head;
        for (int i = 1; i < index ; i++) {
            link = link.getNext(); // Get the link before the actual link at the index
        }

        link.setNext(new Link<>(newItem, link.getNext()));


    }


    public boolean deleteFirst() {
        if (isEmpty()) return false;


        return false;
    }

    public boolean deleteLast() {
        if (isEmpty()) return false;

        return false;

    }

    public boolean deleteAt(int index) {
        if (isEmpty()) return false;

        return false;
    }

    public boolean deleteKey(T key) {
        if (isEmpty()) return false;

        return false;
    }

    public int find(T key) {
        if (isEmpty()) {
            return -1;
        }


        return -1;
    }

    public boolean isEmpty() {
        return head == null; // if head is null then there's no link, hence the linkedlist is empty
    }

    public void display() {
        Link<T> link = head;          // Start traversing from the head

        // if link is null then it has reached the end of the linkedlist
        System.out.print("Head ");
        while (link != null) {
            T data = link.getData();      // get data from each link
            System.out.print(" -> " + data);

            link = link.getNext();      // updating the link to get new data
        }
        System.out.println("");


    }

    @Override
    public String toString() {
        return "LinkedList{" + "head=" + head + ", tail=" + tail + '}';
    }

    private int size() {
        Link<T> link = head;          // Start traversing from the head
        int size = 0;

        while (link != null) {
            size++;
            link = link.getNext();
        }

        return size;
    }

}
