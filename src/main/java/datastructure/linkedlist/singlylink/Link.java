package datastructure.linkedlist.singlylink;

public class Link<T> {
    private T data;
    private Link<T> next;

    public Link(T data, Link<T> next) {
        this.data = data;
        this.next = next;
    }
}
