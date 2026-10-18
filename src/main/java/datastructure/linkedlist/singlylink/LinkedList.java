package datastructure.linkedlist.singlylink;

public class LinkedList<T> {
    private Link<T> head, tail;

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

    }

    public void insertAt(int index, T newItem) {

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

    public void display() {}

    @Override
    public String toString() {
        return "LinkedList{}";
    }
}
