// Christopher Ferrari

package classwork.c3;

public class IntList {

    // Fields
    private int[] items;
    private int size;
    private static final int START_SIZE = 8;


    // Constructor
    IntList() {
        items = new int[START_SIZE];
        size = 0;
    }

    public int getAt(int index) {
        indexValidityCheck(index);
        return items[index];
    }

    public void append(int toAdd) {
        checkAndGrow();
        items[size] = toAdd;
        size++;
    }

    public void prepend(int toAdd) {
        insertAt(toAdd, 0);
    }

    public void insertAt(int toAdd, int index) {
        checkAndGrow();

        // Different from indexValidityCheck, > instead of >=
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = size; i > index; i--) {
            items[i] = items[i - 1];
        }
        items[index] = toAdd;
        size++;
    }

    public void removeAll(int toRemove) {
        for (int i = 0; i < size; i++) {
            if (items[i] == toRemove) {
                removeAt(i);
                i--;
            }
        }
    }

    public void removeAt(int index) {
        indexValidityCheck(index);
        shiftLeft(index);
        size--;
    }

    private void indexValidityCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /*
     * Expands the size of the list whenever it is at
     * capacity
     */
    private void checkAndGrow() {
        // Case: big enough to fit another item, so no
        // need to grow
        if (size < items.length) {
            return;
        }

        // Case: we're at capacity and need to grow
        // Step 1: create new, bigger array; we'll
        // double the size of the old one
        int[] newItems = new int[items.length * 2];

        // Step 2: copy the items from the old array
        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }

        // Step 3: update IntList reference
        items = newItems;
    }

    /*
     * Shifts all elements to the right of the given
     * index one left
     */
    private void shiftLeft(int index) {
        for (int i = index; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
    }

}
