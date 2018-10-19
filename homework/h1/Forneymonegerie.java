//Christopher Ferrari
package homework.h1;

public class Forneymonegerie implements ForneymonegerieInterface {

    // Fields
    // ----------------------------------------------------------
    private ForneymonType[] collection;
    private int size;
    private int typeSize;
    private static final int START_SIZE = 16;


    // Constructor
    // ----------------------------------------------------------
    Forneymonegerie() {
        collection = new ForneymonType[START_SIZE];
        size = 0;
        typeSize = 0;
    }


    // Methods
    // ----------------------------------------------------------
    public boolean empty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public int typeSize() {
        return typeSize;
    }

    public boolean collect(String toAdd) {
        checkAndGrow();
        // >> [AF] IE3
        if (searchIndex(toAdd) < 0) {
            collection[typeSize] = new ForneymonType(toAdd, 1);
            typeSize++;
            size++;
            return true;
        }

        // >> [AF] IE3
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
        return (searchIndex(toCheck) >= 0);
    }

    public String nth(int n) {
        if (n >= size) {
            throw new IllegalArgumentException("Argument is beyond size of your collection");
        }

        int sum = -1;
        for (int i = 0; i < typeSize; i++) {
            sum += collection[i].count;
            if (sum >= n) {
                return collection[i].type;
            }
        }

        // >> [AF] Not the cleanest code if you have to write this :)
        // See solution for restructuring of logic
        return "Shouldn't get here";
    }

    public String rarestType() {
        if (size == 0) {
            return null;
        }
        // >> [AF] SE1: multiple spaces suddenly


        int low = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < typeSize; i++) {
            if (collection[i].count <= low) {
                low = collection[i].count;
                index = i;
            }
        }

        return collection[index].type;
    }

    public Forneymonegerie clone() {
        Forneymonegerie copy = new Forneymonegerie();

        // >> [AF] Great!
        copy.collection = new ForneymonType[this.collection.length];
        for (int i = 0; i < collection.length; i++) {
            copy.collection[i] = this.collection[i];
        }

        copy.size = this.size;
        copy.typeSize = this.typeSize;

        return copy;

    }

    public void trade(Forneymonegerie other) {
        Forneymonegerie temp = new Forneymonegerie();
        temp.collection = other.collection;
        temp.size = other.size;
        temp.typeSize = other.typeSize;

        other.collection = this.collection;
        other.size = this.size;
        other.typeSize = this.typeSize;

        this.collection = temp.collection;
        this.size = temp.size;
        this.typeSize = temp.typeSize;

    }

    public String toString() {
        if (typeSize == 0) {
            return "[ ]";
        }

        String types = "[ ";

        for (int i = 0; i < collection.length; i++) {
            types += collection[i].toString();
            if (collection[i + 1] != null) {
                types += ", ";
            } else {
                break;
            }
        }

        types += " ]";

        return types;
    }

    // Static methods
    // ----------------------------------------------------------
    public static Forneymonegerie diffMon(Forneymonegerie y1, Forneymonegerie y2) {
        if (y2.typeSize == 0) {
            // >> [AF] Whoops! You were meant to return a NEW Forneymonegerie; this is
            // just a reference! (-1)
            return y1;
        }

        Forneymonegerie y3 = new Forneymonegerie();

        for (int i = 0; i < y1.typeSize; i++) {

            if (!y2.contains(y1.collection[i].type)) {
                for (int j = 0; j < y1.collection[i].count; j++) {
                    // >> [AF] IE1
                    y3.collect(y1.collection[i].type);
                }
                continue;
            }

            if (y2.collection[i].count < y1.collection[i].count) {
                for (int j = 0; j < y1.collection[i].count - y2.collection[i].count; j++) {
                    // >> [AF] IE1
                    y3.collect(y1.collection[i].type);
                }
            }

        }

        return y3;
    }

    public static boolean sameCollection(Forneymonegerie y1, Forneymonegerie y2) {
        if (y1.typeSize != y2.typeSize) {
            return false;
        }

        for (int i = 0; i < y1.typeSize; i++) {
            if ( ( !y2.contains(y1.collection[i].type) ) || ( y2.collection[y2.searchIndex(y1.collection[i].type)].count != y1.collection[i].count) ) {
                return false;
            }
        }

        return true;
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
            return ("\"" + type + "\": " + count);
        }
    }

}

// ===================================================
// >> [AF] Summary
// ---------------------------------------------------
// Correctness:         79 / 75
// Unit tests:          10 / 10
// Style:               13 / 15
// Total:               102 / 100
// ---------------------------------------------------
// Bottom line:
// Excellent work! A few opportunities to keep
// efficiency in mind, since you have some overly-
// repetitive parts where some better logic would have
// served you well, but overall a superb effort.
// You DID however name your package incorrectly (well
// you spelled it correctly but not the way the spec
// requested). No points off this time, but in the
// future, do what the spec says.
// ===================================================
