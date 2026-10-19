package datastructure.linkedlist.singlylink;

class Link<T> {
     final T data;
     Link<T> next;

    public Link(T data, Link<T> next) {
        this.data = data;
        this.next = next;
    }

}
