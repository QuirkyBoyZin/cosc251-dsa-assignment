package datastructure;

import java.util.Arrays;

public class Queue<T> {
    private T[] elements;     // The elements in the queue
    private int pointer;    // Points at the next available element

    @SuppressWarnings("unchecked")  // Avoid this message: "Unchecked cast: 'java.lang.Object' to 'T[]'
    public Queue(int arraySize) {
        if (arraySize <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        this.elements = (T[]) new Object[arraySize];
    }

    public void insert(T newItem) {
        elements[pointer] = newItem;
        pointer++;  // Increment pointer to point at the next available position
        if (pointer == elements.length) {
            resize();
        } // resize when queue is full

    }

    public T remove() {
        if (elements[0] == null) return null;

        T removedElement = elements[0];
        elements[0] = null;

        for (int i = 0; i < pointer; i++) {
            elements[i] = elements[i + 1];
            elements[i + 1] = null;
        }

        pointer--;

        return removedElement;
    }

    public T peekFront() {
        return (elements[0] != null) ? elements[0] : null;
    }

    public T peekRear() {
        if (pointer - 1 < 0) return null; // To avoid Index -1 out of bounds for length 1

        return (elements[pointer - 1] != null) ? elements[pointer - 1] : null;
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
