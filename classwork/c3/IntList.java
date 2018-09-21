// Christopher Ferrari

public class IntList {

    // Fields
    private int[] items;
    private int   size;
    private static final int START_SIZE = 8;
    


    // Constructor
    IntList () {
        items = new int[START_SIZE];
        size  = 0;
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
    
    public void prepend (int toAdd) {
        checkAndGrow();
        for (int i = size; i > 0; i--) {
            items[i] = items[i-1];
        }
        items[0] = toAdd;
        size++;
    }

    public void insertAt (int toAdd, int index) {
        checkAndGrow();
        indexValidityCheck(index);

        size++;
    }
    
    public void removeAll (int toRemove) {

    }

    public void removeAt(int index) {
        indexValidityCheck(index);
        shiftLeft(index);
        size--;
    }
    
    private void indexValidityCheck (int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
    
    /*
     * Expands the size of the list whenever it is at
     * capacity
     */
    private void checkAndGrow () {
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
    private void shiftLeft (int index) {
        for (int i = index; i < size-1; i++) {
            items[i] = items[i+1];
        }
    }

    public static void main( String args[] ) {
        IntList test = new IntList();
        test.append(2039);
        test.append(283);
        test.append(2038);
        test.append(2037);
        test.append(2036);
        test.append(2035);
        test.append(2034);
        test.append(2033);
        test.prepend(2032);
        for (int i = 0; i < test.size; i++) {
            System.out.println(test.getAt(i));
        }

    }


}
