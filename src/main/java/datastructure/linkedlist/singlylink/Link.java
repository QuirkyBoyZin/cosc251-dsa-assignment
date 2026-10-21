package datastructure.linkedlist.singlylink;

class Link<T> {
    T data;
    Link<T> next;

    Link(T data) {
        this(data, null);
    }

    Link(T data, Link<T> next) {
        this.data = data;
        this.next = next;
    }
}