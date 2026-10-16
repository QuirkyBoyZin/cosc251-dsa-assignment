package datastructure.linkedlist.singlylink;

public class LinkedList<T> {
    private Link<T> head, tail;

    public LinkedList() {
    }

    public void insertFirst(T newItem) {

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
