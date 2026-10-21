package datastructure.linkedlist.singlylink;

class Link<T> {
    T data;
    Link<T> next;

    // Time Complexity: O(1)
    Link(T data) {
        this(data, null);
    }

    // Time Complexity: O(1)
    Link(T data, Link<T> next) {
        this.data = data;
        this.next = next;
    }
}