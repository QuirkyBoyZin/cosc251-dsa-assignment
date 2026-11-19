/**
This is optimized version of UnorderedArray used to implement HashTable.
 As stated by the requirement, we need to use an unordered array.

The main difference between this and the previous UnorderedArray are:
 - No resizing. Resizing should be done in the HashTable class.
 - The 'count' variable is renamed to 'size', while 'capacity' is used to denote the total capacity of the array.
 - All methods are designed to work with indices instead of values. This is because the HashTable will handle the mapping of keys to indices, and the array will only store values at those indices.
 Hence, unneeded methods are removed.
 */


package datastructure.arrays;

import java.util.Arrays;
import java.util.Objects;

public class UnorderedArrayOptimized<T> {
    private T[] arr;
    private int size;

    @SuppressWarnings("unchecked")
    public UnorderedArrayOptimized(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + capacity);
        }
        // Allocate raw array with the given capacity
        this.arr = (T[]) new Object[capacity];
        this.size = 0;
    }

    public T get(int index) {
        checkBounds(index);
        return arr[index];
    }

    public void set(int index, T value) {
        Objects.requireNonNull(value, "Value cannot be null");
        checkBounds(index);

        if (arr[index] == null) {
            size++;
        }

        arr[index] = value;
    }

    public void delete(int index) {
        checkBounds(index);

        if (arr[index] == null) return;

        arr[index] = null;
        size--;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= arr.length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index + ", Capacity: " + arr.length);
        }
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return arr.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

    public static void main(String[] args) {

    }
}