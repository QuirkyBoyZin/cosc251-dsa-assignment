package datastructure.linkedlist.doublylink;

public class DoublyLink<T> {
    private T data;
    DoublyLinkedList<T> next;
    DoublyLinkedList<T> previous;

    public DoublyLink(T data, DoublyLinkedList<T> next, DoublyLinkedList<T> previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
}
