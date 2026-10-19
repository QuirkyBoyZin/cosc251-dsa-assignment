package datastructure.linkedlist.doublylink;

class DoublyLink<T> {
    T data;
    DoublyLink<T> next;
    DoublyLink<T> previous;

    DoublyLink(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }
}
