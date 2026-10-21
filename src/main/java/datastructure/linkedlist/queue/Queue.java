package datastructure.linkedlist.queue;

import datastructure.linkedlist.singlylink.LinkedList;

import java.util.Objects;

public class Queue<T> {
    private final LinkedList<T> queue;

    // Time Complexity: O(1)
    public Queue() {
        queue = new LinkedList<>();
    }

    // Time Complexity: O(1)
    public void insert(T newItem) {
        Objects.requireNonNull(newItem, "New item to be enqueued to the queue must not be null");

        queue.insertLast(newItem);
    }

    // Time Complexity: O(1)
    public T remove() {
        T removed = queue.peekFirst();
        if (removed == null) return null;
        queue.deleteFirst();
        return removed;
    }

    // Time Complexity: O(1)
    public T peekFront() {
        return queue.peekFirst();
    }

    // Time Complexity: O(1)
    public T peekRear() {
        return queue.peekLast();
    }

    // Time Complexity: O(n) where n is the number of elements in the queue
    @Override
    public String toString() {
        return "Queue[" + queue + "]";
    }

    // Time Complexity: O(N) where N is the number of elements in the queue
    public void display() {
        System.out.print("Front [");
        System.out.print(queue);
        System.out.print("] Rear");
    }
}
