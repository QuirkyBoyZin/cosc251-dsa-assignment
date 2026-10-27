package datastructure.queue;

import java.util.Arrays;
import java.util.Objects;

// Inner class to keep track of the given item with the given priority
record Item<T>(T item, int priorityValue){}

public class PriorityQueue<T> {
    private Item<T>[] items;     // The elements in the queue
    private int pointer;        // Points at the next available position

    @SuppressWarnings("unchecked")
    // O(1)
    public PriorityQueue(int arraySize) {
        if (arraySize <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        this.pointer = 0;
        this.items = new Item[arraySize];

    }

    // O(n): Finding the correct insertion spot to maintain priority order
    public void insert(T newItem, int priorityValue) {
        Objects.requireNonNull(newItem);
        if (pointer == items.length) resizeArray();

        Item<T> item = new Item<>(newItem, priorityValue);

        int index = findInsertionPosition(item);

        // index == -1 means the item is the lowest priority
        if (index == -1) {
            items[pointer] = item;
            pointer++;
            return;
        }

        if (items[index] != null) {
            for (int i = pointer; i > index; i--) items[i] = items[i - 1];
        }
        items[index] = item;
        pointer++;
    }

    // O(n): Shifting elements to the left to fill in the empty front spot
    public T remove() {
        if (items[0] == null) return null;

        T removedElement = items[0].item();

        for (int i = 0; i < pointer - 1; i++) {
            items[i] = items[i + 1];
        }

        items[pointer - 1] = null;
        pointer--;

        return removedElement;

    }

    // O(1): Accessing array is constant
    public T peekFront() {
        return (items[0] != null) ? items[0].item() : null;
    }

    // O(1): Accessing array is constant
    public T peekRear() {
        return (items[0] != null) ? items[pointer - 1].item() : null;
    }

    public void display() {
        System.out.print(" Out ");
        for (int i = 0; i < items.length; i++) {
            System.out.print(" <- | " + items[i] + ": " + ((items[i] == null) ? null : items[i].priorityValue() + " | "));

        }
        System.out.println(" ");


    }

    @Override
    public String toString() {
        return "PriorityQueue{" + ", elements=" + Arrays.toString(items) + ", pointer= " + pointer + ", items.length=" + items.length + '}';
    }

    @SuppressWarnings("unchecked")
    private void resizeArray() {
        Item<T>[] newArray = new Item[items.length * 2];

        System.arraycopy(items, 0, newArray, 0, items.length);
        items = newArray;

    }

    private int findInsertionPosition(Item<T> item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && item.priorityValue() <= items[i].priorityValue()) {
                return i; // insert at the left of the lower priorityValue
            }
        }
        return -1;
    }

}
