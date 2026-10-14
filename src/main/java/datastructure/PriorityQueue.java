package datastructure;
import java.util.Arrays;
import java.util.Objects;

// Inner class to keep track of the given item with the given priority
record Item<T>(T item, int priorityValue) {
    @Override
    public String toString() {
        return (String) item;
    }

}

public class PriorityQueue  <T> {

    private Item<T>[] items;     // The elements in the queue
    private int pointer;        // Points at the next available position
    private int arraySize;       // The length of the queue

    @SuppressWarnings("unchecked")
    public PriorityQueue(int arraySize) {
        if (arraySize <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        this.pointer   = 0;
        this.arraySize = arraySize;
        this.items     = new Item[arraySize];

    }

    public void insert(T newItem, int priorityValue) {
       Objects.requireNonNull(newItem);
       insertToArray(newItem, priorityValue);
   }

    public T remove() {
        if (items[0] == null) return null;

        T removedElement = items[0].item();

        for (int i = 0; i < pointer - 1; i++) {items[i] = items[i + 1]; }

        items[pointer - 1] = null;
        pointer--;

        return removedElement;

    }

    public T peekFront() { return (items[0] != null) ? items[0].item() : null; }

    public T peekRear() { return (items[0] != null) ? items[pointer - 1].item(): null; }

    public void display() {
        System.out.print(" Out ");
        for (int i = 0; i < arraySize; i++) {
            System.out.print(" <- | " + items[i] + ": " + ((items[i] == null)? null:items[i].priorityValue() + " | "));

        }
        System.out.println(" ");


    }

    @Override
    public String toString() {
        return "PriorityQueue{" +
                ", elements=" + Arrays.toString(items) +
                ", pointer= " + pointer +
                ", arraySize=" + arraySize +
                '}';
    }

    @SuppressWarnings("unchecked")
    private void resizeArray() {
        Item<T>[] newArray =  new Item[arraySize * 2];

        if (arraySize >= 0) System.arraycopy(items, 0, newArray, 0, arraySize);

        items = newArray;
        arraySize *=2;

    }

    private void insertToArray(T newItem, int priorityValue) {
        if (pointer == arraySize) resizeArray();

        Item<T> item = new Item<>(newItem, priorityValue);

        int index = findInsertionPosition(item);

        // index == -1 means the item is the lowest priority
        if (index == -1 ) {
            items[pointer] = item;
            pointer++;
            return;
        }

        if (items[index] != null) { shiftArray(index); }
        items[index] = item;
        pointer++;

    }

    private int findInsertionPosition(Item<T> item) {
        for (int i = 0; i < arraySize; i++) {
            if (items[i] != null && item.priorityValue() <= items[i].priorityValue() ) {
                return i; // insert at the left of the lower priorityValue
            }
        }
        return -1;
    }

    private void shiftArray(int index) {
        for (int i = pointer + 1; i > index; i--) items[i] = items[i-1];
        pointer++;
    }
}
