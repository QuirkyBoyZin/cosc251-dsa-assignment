package datastructure.linkedlist.stack;


import datastructure.linkedlist.singlylink.LinkedList;

import java.util.Objects;

public class Stack<T> {
    private final LinkedList<T> stack;

    public Stack() {
        this.stack = new LinkedList<>();
    }

    // Time complexity: O(1)
    public void push(T newItem) {
        Objects.requireNonNull(newItem, "New item must not be null");

        stack.insertFirst(newItem);

    }

    // Time complexity: O(1)
    public T pop() {
        T removed = stack.peekFirst();
        if (removed == null) return null;
        stack.deleteFirst();
        return removed;
    }

    // Time complexity: O(1)
    public T peek() {
        return stack.peekFirst();
    }

    // Time complexity: O(n) where n is the number of elements in the stack
    @Override
    public String toString() {
        return "Stack[" + stack + "]";
    }

    // Time complexity: O(n) where n is the number of elements in the stack
    public void display() {
        System.out.print("Top [");
        System.out.print(stack);
        System.out.println("]");
    }
}
