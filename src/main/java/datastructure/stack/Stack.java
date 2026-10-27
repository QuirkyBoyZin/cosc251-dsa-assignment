package datastructure.stack;

import java.util.Arrays;
import java.util.Objects;

public class Stack <T>{
    private int top = -1;
    private T[] elements;

    @SuppressWarnings("unchecked")
    public Stack(int arraySize) {
        if (arraySize <= 0) throw new IllegalArgumentException("arraySize must not be less than or equal to 0! ");
        this.elements = (T[]) new Object[arraySize];
    }

    // O(n): Worst case if the stack is full needs to resize
    // O(1): Best case if the stack is not full
    public void push(T newItem) {
        Objects.requireNonNull(newItem);

        if (top + 1 == elements.length) { resize(); }

        top++;
        elements[top] = newItem;
    }

    // O(1): Accessing array is instant because we have a top which can directly get us to the top of the stack
    public T peek() {
        if (top < 0) return null;
        return elements[top];
    }

    // O(1): Accessing array is instant because we have a top which can directly get us to the top of the stack
    // and setting an element to null is also constant
    public T pop() {
        if (top == -1) return null;

        T value = elements[top];
        elements[top] = null;

        top--;
        return value;
    }


    // O(n): Iterating over the entire stack then displaying in a stack manner
    public void display() {
        for (int i = top; i >= 0 ; i--) System.out.println("| " + elements[i] + " |");
    }

    // O(n): While top, type arraySize is constant time but Arrays.toString() needs to iterate
    // over the array to convert every element into a String
    @Override
    public String toString() {
        return "datastructure.stack.Stack{" +
                "top=" + top +
                ", elements=" + Arrays.toString(elements) +
                '}';
    }


    @SuppressWarnings("unchecked")
    private void resize() {
        int size = elements.length;
        T[] newArray = (T[]) new Object[size* 2];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;


    }
}
