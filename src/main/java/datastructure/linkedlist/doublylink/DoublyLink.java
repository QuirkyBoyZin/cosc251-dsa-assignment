package datastructure.linkedlist.doublylink;

class DoublyLink<T> {
    T data;
    DoublyLink<T> next;
    DoublyLink<T> previous;

    // Time Complexity: O(1)
    DoublyLink(T data) {
        this(data, null, null);
    }

    // Time Complexity: O(1)
    DoublyLink(T data, DoublyLink<T> next, DoublyLink<T> previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
}