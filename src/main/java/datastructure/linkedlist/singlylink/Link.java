package datastructure.linkedlist.singlylink;

public class Link<T> {
    private final T data;
    private  Link<T> next;

    public Link(T data, Link<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public Link<T> getNext() {
        return next;
    }

    public void setNext(Link<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Link{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}
