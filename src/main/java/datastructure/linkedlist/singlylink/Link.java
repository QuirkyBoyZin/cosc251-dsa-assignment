package datastructure.linkedlist.singlylink;

class Link<T> {
    T data;
    Link<T> next;

    Link(T data) {
        this.data = data;
        this.next = null;
    }
}