//Christopher Ferrari
package homework.h1;

public class Forneymonegerie implements ForneymonegerieInterface {

    // Fields
    // ----------------------------------------------------------
    private ForneymonType[] collection;
    private int size;
    private int typeSize;
    private static final int START_SIZE = 16;
    private int arrLength;


    // Constructor
    // ----------------------------------------------------------
    Forneymonegerie() {
        collection = new ForneymonType[START_SIZE];
        arrLength = collection.length;
        size = 0;
        typeSize = 0;
    }


    // Methods
    // ----------------------------------------------------------
    public boolean empty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    public int typeSize() {
        return typeSize;
    }

    public boolean collect(String toAdd) {
        checkAndGrow();
        if (searchIndex(toAdd) < 0) {
            collection[typeSize] = new ForneymonType(toAdd, 1);
            typeSize++;
            size++;
            return true;
        }

        collection[searchIndex(toAdd)].count++;
        size++;
        return false;
    }

    public boolean release(String toRemove) {
        int index = searchIndex(toRemove);

        if (index < 0) {
            return false;
        }

        if (collection[index].count == 1) {
            releaseType(toRemove);
            return true;
        }

        collection[index].count--;
        size--;
        return true;
    }

    public void releaseType(String toNuke) {
        int index = searchIndex(toNuke);

        if (index < 0) {
            return;
        }

        size = size - collection[index].count;
        for (int i = index; i < typeSize; i++) {
            collection[i] = collection[i + 1];
        }
        typeSize--;

    }

    public int countType(String toCount) {
        if (searchIndex(toCount) < 0) {
            return 0;
        }

        return collection[searchIndex(toCount)].count;

    }

    public boolean contains(String toCheck) {
        if (searchIndex(toCheck) < 0) {
            return false;
        }

        return true;
    }

    public String nth(int n) {
        throw new UnsupportedOperationException();
    }

    public String rarestType() {
        throw new UnsupportedOperationException();
    }

    public Forneymonegerie clone() {
        throw new UnsupportedOperationException();
    }

    public void trade(Forneymonegerie other) {
        throw new UnsupportedOperationException();
    }


    // Static methods
    // ----------------------------------------------------------
    public static Forneymonegerie diffMon(Forneymonegerie y1, Forneymonegerie y2) {
        throw new UnsupportedOperationException();
    }

    public static boolean sameCollection(Forneymonegerie y1, Forneymonegerie y2) {
        throw new UnsupportedOperationException();
    }


    // TODO: Update without "EMPTY"
    public String toString() {
        String types = "[ ";

        for (int i = 0; i < collection.length; i++) {
            if (collection[i] != null) {
                types = types + collection[i].toString() + ", ";
            } else {
                types = types + "EMPTY, ";
            }

        }

        types += " ]";

        return types;
    }


    // Private helper methods
    // ----------------------------------------------------------

    private void checkAndGrow() {
        if (typeSize < collection.length) {
            return;
        }

        ForneymonType[] newCollection = new ForneymonType[typeSize + START_SIZE];
        for (int i = 0; i < collection.length; i++) {
            newCollection[i] = collection[i];
        }

        collection = newCollection;
    }

    private int searchIndex(String toSearch) {
        for (int i = 0; i < typeSize; i++) {
            if (collection[i].type.equals(toSearch)) {
                return i;
            }
        }

        return -1;
    }


    // Private Classes
    // ----------------------------------------------------------
    private class ForneymonType {
        String type;
        int count;

        ForneymonType(String t, int c) {
            type = t;
            count = c;
        }

        public String toString() {
            return (" \"" + type + "\": " + count );
        }
    }

}
