package datastructure.linkedlist.singlylink;
import java.util.Objects;

public class LinkedList<T> {
    private Link<T> head, tail;
    private int count;

    public LinkedList() {
    }

    public void insertFirst(T newItem) {
        Objects.requireNonNull(newItem);

        head = new Link<>(newItem, head);   // head points to new Link, New link point to head's previous link
        if (tail == null) {
            tail = head;  // tail only change here initially
        }

        count++;

    }

    public void insertLast(T newItem) {
        Objects.requireNonNull(newItem);
        Link<T> newLink = new Link<>(newItem, null);

        if (head == null) {
            head = newLink;
            count++;
            return;
        }

        tail.next = newLink;
        tail = newLink;
        count++;

    }

    public void insertAt(int index, T newItem) {
        Objects.requireNonNull(newItem);
        int maxIndex = count - 1;

        // Inserting at the beginning or at the end
        if (index == 0) {
            insertFirst(newItem);
            count++;
            return;
        }

        // Inserting at the end
        if (index == maxIndex) {
            insertLast(newItem);
            count++;
            return;
        }

        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException("Index must be in range of 0 to " + count);
        }

        // Inserting at the middle
        Link<T> current = head;
        for (int i = 1; i < index; i++) {
            current = current.next; // Get the link before the actual link at the index
        }

        current.next = new Link<>(newItem, current.next);
        count++;

    }

    public boolean deleteFirst() {
        if (isEmpty()) return false;

        if (head == tail) {
            head = tail = null;
            return true;
        }

        Link<T> removedLink = head;
        head = head.next;           // Get the next link of head then refer head to it
        removedLink.next = null;

        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) return false;

        if (tail == head) {
            head = tail = null;
            return true;
        }

        Link<T> link = head;  // Traversing head to get the second last link from tail
        while (link.next != tail) {
            link = link.next;
        }

        link.next = null;
        tail = link;

        return true;
    }

    public boolean deleteAt(int index) {
        if (isEmpty()) return false;

        if (isEmpty()) {
            throw new RuntimeException("Please insert an element first");
        }

        int size = size();
        int maxIndex = size - 1;
        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException("Index must be in range of 0 to " + size);
        }

        // Deleting at the beginning or at the end
        if (index == 0) {
            deleteFirst();
            return true;
        }

        // Deleting at the end
        if (index == maxIndex) {
            deleteLast();
            return true;
        }

        // Deleting at the middle
        Link<T> link = head;
        for (int i = 1; i < index; i++) {
            link = link.next; // Get the link before the removed link
        }

        Link<T> removedLink = link.next;
        link.next = removedLink.next;
        removedLink.next = null;

        return true;
    }

    public boolean deleteKey(T key) {
        Objects.requireNonNull(key);
        if (isEmpty()) return false;

        int index = find(key);

        return index != -1 && deleteAt(index);
    }

    public int find(T key) {
        Objects.requireNonNull(key);

        if (isEmpty()) return -1;

        Link<T> link = head;
        int index = 0;
        while (link != null) {
            if (link.data.equals(key)) return index;
            index++;

            link = link.next;
        }


        return -1;
    }

    public boolean isEmpty() {
        return head == null; // if head is null then there's no link, hence the linkedlist is empty
    }

    public void display() {
        Link<T> link = head;          // Start traversing from the head

        // if link is null then it has reached the end of the linkedlist
        System.out.print("Head");
        while (link != null) {
            T data = link.data;      // get data from each link
            System.out.print(" -> " + data);

            link = link.next;      // updating the link to get new data
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
            link = link.next;
        }

        return size;
    }

}
