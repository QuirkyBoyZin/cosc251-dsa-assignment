package datastructure.linkedlist.doublylink;

class DoublyLink<T> {
    final T data;
    DoublyLink<T> next;
    DoublyLink<T> previous;

    public DoublyLink(T data, DoublyLink<T> next, DoublyLink<T> previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
}
