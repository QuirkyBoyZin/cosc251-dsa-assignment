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
        } else {
            tail.next = newLink;
        }
        tail = newLink;
        count++;

    }

    public void insertAt(int index, T newItem) {
        Objects.requireNonNull(newItem);
        // Inserting at the beginning or at the end
        if (index == 0) {
            insertFirst(newItem);
            return;
        }

        // Inserting at the end
        if (index == count) {
            insertLast(newItem);
            return;
        }

        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Index must be in range of [ 0 to " + count + " ] ");
        }

        // Inserting at the middle
        Link<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next; // Get the link before the actual link at the index
        }

        current.next = new Link<>(newItem, current.next);

        count++;
    }

    public boolean deleteFirst() {
        if (head == null) return false;
        head = head.next;           // Get the next link of head then refer head to it

        if (head == null) {
            tail = null;
        }
        count--;

        return true;
    }

    public boolean deleteLast() {
        if (head == null) return false;

        if (tail == head) {
            head = tail = null;

            count--;
            return true;
        }

        Link<T> current = head;  // Traversing head to get the second last link from tail
        while (current.next != tail) {
            current = current.next;
        }
        current.next = null;
        tail = current;

        count--;
        return true;
    }

    public boolean deleteAt(int index) {
        // Deleting at the beginning
        if (index == 0) return deleteFirst();

        // Deleting at the end
        if (index == count)  return deleteLast();

        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Index must be in range of [ 0 to " + count + " ] ");
        }


        // Deleting at the middle
        Link<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next; // Get the link before the removed link
        }

        Link<T> removedLink = current.next;
        current.next = removedLink.next;
        removedLink.next = null;

        count--;
        return true;
    }

    public boolean deleteKey(T key) {
        Objects.requireNonNull(key);
        if (head == null) return false;

        Link<T> previous = null;
        Link<T> current = head;

        for (int i = 0; current != null; i++) {

            if (current.data.equals(key)) {
                if (i == 0) return deleteFirst();
                if (i == count - 1) return deleteLast();

                deleteAt(i);
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public int find(T key) {
        Objects.requireNonNull(key, "Key must be null");

        Link<T> current = head;
        for (int i = 0; current != null; i++) {
            if (current.data.equals(key)) return i;

            current = current.next;
        }

        return -1;
    }

    public boolean isEmpty() {
        return head == null && tail == null; // if head is null then there's no link, hence the linkedlist is empty
    }

    public void display() {
        Link<T> current = head;          // Start traversing from the head

        // if link is null then it has reached the end of the linkedlist
        System.out.print("LinkedList: Head [ ");
        while (current != null) {
            if (current.next != null) {
                System.out.print(current.data + " -> ");
            } else {
                System.out.print(current.data);
            }
            current = current.next;
        }
        System.out.println(" ] Tail");

    }

    @Override
    public String toString() {
        Link<T> current = head;          // Start traversing from the head
        StringBuilder sb = new StringBuilder();

        while (current != null) {
            if (current.next != null) {
                System.out.print(current.data + " -> ");
            } else {
                sb.append(current.data);
            }
            current = current.next;
        }
        if (head != null && tail != null) {
            sb.append("\nHead: ").append(head.data.toString()).append("\nTail: ").append(tail.data.toString());
        } else {
            sb.append("\nHead: null").append("\nTail: null ");

        }
        return sb.toString();
    }


}
