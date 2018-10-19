package homework.h2;

import java.util.NoSuchElementException;

public class LinkedForneymonegerie implements LinkedForneymonegerieInterface {

    // Fields
    // -----------------------------------------------------------
    private ForneymonType head;
    private ForneymonType tail;
    private Iterator it;
    private int size, typeSize, modCount;

    
    // Constructor
    // -----------------------------------------------------------
    LinkedForneymonegerie () {
        head = null;
        tail = null;

        size = 0;
        typeSize = 0;
        modCount = 0;
    }
    
    
    // Methods
    // -----------------------------------------------------------
    public boolean empty () {
        return (size == 0);
    }
    
    public int size () {
        return size;
    }
    
    public int typeSize () {
        return typeSize;
    }
    
    public boolean collect (String toAdd) {
        modCount++;
        size++;

        if (empty()) {
            head = new ForneymonType(toAdd, 1);
            tail = head;
            it = new Iterator(this);
            return true;
        }

        ForneymonType check = doesExist(toAdd);

        if (check == null) {
            ForneymonType currentTail = tail;
            tail = new ForneymonType(toAdd, 1);
            currentTail.next = tail;
            tail.prev = currentTail;
            typeSize++;
            return true;
        }

        check.count++;
        return false;

    }
    
    public boolean release (String toRemove) {
        throw new UnsupportedOperationException();
    }
    
    public void releaseType (String toNuke) {
        throw new UnsupportedOperationException();
    }
    
    public int countType (String toCount) {
        throw new UnsupportedOperationException();
    }
    
    public boolean contains (String toCheck) {
        throw new UnsupportedOperationException();
    }
    
    public String rarestType () {
        throw new UnsupportedOperationException();
    }
    
    public LinkedForneymonegerie clone () {
        throw new UnsupportedOperationException();
    }
    
    public void trade (LinkedForneymonegerie other) {
        throw new UnsupportedOperationException();
    }
    
    public LinkedForneymonegerie.Iterator getIterator () {
        if (empty()) {
            throw new IllegalStateException();
        }

        return it;
    }
    
    
    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------
    
    public static LinkedForneymonegerie diffMon (LinkedForneymonegerie y1, LinkedForneymonegerie y2) {
        throw new UnsupportedOperationException();
    }
    
    public static boolean sameCollection (LinkedForneymonegerie y1, LinkedForneymonegerie y2) {
        throw new UnsupportedOperationException();
    }
    
    // Private helper methods
    // -----------------------------------------------------------

    private ForneymonType doesExist (String typeToSearch) {
        while (it.hasNext()) {
            if (it.getType().equals(typeToSearch)) {
                return it.current;
            } else {
                it.next();
            }

        }

        return null;
    }
    
    // Inner Classes
    // -----------------------------------------------------------
    
    public class Iterator implements LinkedForneymonegerieIteratorInterface {
        LinkedForneymonegerie owner;
        ForneymonType current;
        int currentNumber;
        int itModCount;
        
        Iterator (LinkedForneymonegerie y) {
            owner = y;
            current = head;
            currentNumber = 1;
            itModCount = owner.modCount;
        }

        public boolean hasNext () {
            return (!isValid() || currentNumber < current.count || current != tail);
        }
        
        public boolean hasPrev () {
            return (!isValid() || currentNumber > current.count || current != head);
        }
        
        public boolean isValid () {
            return (itModCount == owner.modCount);
        }
        
        public String getType () {
            if (!isValid()) {
                return null;
            }

            return current.type;
        }

        public void next () {
            if (!isValid()) {
                throw new IllegalStateException();
            } else if (!hasNext()) {
                throw new NoSuchElementException();
            }

            if (currentNumber < current.count) {
                currentNumber++;
            } else {
                current = current.next;
                currentNumber = 1;
            }
        }
        
        public void prev () {
            if (!isValid()) {
                throw new IllegalStateException();
            } else if (!hasPrev()) {
                throw new NoSuchElementException();
            }

            if (currentNumber > current.count) {
                currentNumber--;
            } else {
                current = current.prev;
                currentNumber = current.count;
            }
        }
        
        public void replaceAll (String toReplaceWith) {
            throw new UnsupportedOperationException();
        }
        
    }
    
    private class ForneymonType {
        ForneymonType next, prev;
        String type;
        int count;
        
        ForneymonType (String t, int c) {
            type = t;
            count = c;
            next = null;
            prev = null;
        }
    }
    
}