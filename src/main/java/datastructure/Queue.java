package datastructure;

import java.util.Arrays;
import java.util.Objects;

public class Queue<T> {
    private T[] elements;     // The elements in the queue
    private int pointer;     // Points at the next available position

    @SuppressWarnings("unchecked")  // Avoid this message: "Unchecked cast: 'java.lang.Object' to 'T[]'
    // O(1)
    public Queue(int arraySize) {
        if (arraySize <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        this.elements = (T[]) new Object[arraySize];
    }

    // Best case O(1): When there is no resizing
    // Worst case O(n): When the queue is full and need resizing
    public void insert(T newItem) {
        Objects.requireNonNull(newItem);

        if (pointer == elements.length) {
            resize();
        } // resize when queue is full
        elements[pointer] = newItem;
        pointer++;  // Increment pointer to point at the next available position

    }

    // O(n): Shifting elements to the left to fill in the empty front spot
    public T remove() {
        if (elements[0] == null) return null;

        T removedElement = elements[0];

        for (int i = 0; i < pointer - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[pointer - 1] = null;
        pointer--;

        return removedElement;
    }

    // O(1): Accessing array is constant
    public T peekFront() {
        return (elements[0] != null) ? elements[0] : null;
    }

    // O(1): Accessing array is constant
    public T peekRear() {
        return (elements[0] != null) ? elements[pointer - 1] : null;
    }


    @Override
    public String toString() {
        return "datastructure.Queue2{" + ", Elements=" + Arrays.toString(elements) + ", Pointer=" + pointer + '}';
    }

    public void display() {
        System.out.print(" Out ");
        for (T element : elements) {
            System.out.print(" <- | " + element + " | ");
        }
        System.out.print(" <---  Queue Here ");
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int size = elements.length;

        T[] newArray = (T[]) new Object[size * 2];
        System.arraycopy(elements, 0, newArray, 0, size);

        elements = newArray;

    }


}