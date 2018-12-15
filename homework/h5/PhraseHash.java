package homework.h5;

public class PhraseHash implements PhraseHashInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------

    private final static int BUCKET_START = 1000;
    private final static double LOAD_MAX = 0.7;
    private int size, longest;
    private String[] buckets;


    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------

    PhraseHash () {
        size = 0;
        longest = 0;
        buckets = new String[BUCKET_START];
    }


    // -----------------------------------------------------------
    // Public Methods
    // -----------------------------------------------------------

    public int size () {
        return size;
    }

    public boolean isEmpty () {
        return (size == 0);
    }

    public void put (String s) {
        s = s.toLowerCase().trim();

        if (s.equals("")) {
            throw new IllegalArgumentException();
        }

        int hashCalc = hash(s);

        if (buckets[hashCalc] != null && buckets[hashCalc].equals(s)) {
            return;
        }

        size++;
        if (checkAndGrow()) {
            hashCalc = hash(s);
        }

        buckets[hashCalc] = s;

        String[] wordArray = s.split(" ");
        if (longest < wordArray.length) {
            longest = wordArray.length;
        }
    }

    public String get (String s) {
        s = s.toLowerCase().trim();
        if (buckets[hash(s)] != null) {
            return s;
        }

        return null;
    }

    public int longestLength () {
        return longest;
    }


    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------

    private int hash (String s) {
        s = s.toLowerCase().trim();
        int hashCalc = 0;
        for (int i = 0; i < s.length(); i++) {
            // If space, add (hashCalc * 17) + 27, otherwise add (hashCalc * 17) + (number letter in alphabet)
            if (s.charAt(i) == 32) {
                hashCalc = (hashCalc * 17) + 27;
            } else {
                hashCalc = (hashCalc * 17) + (s.charAt(i) - 96);
            }

            // Account for Integer Overflow
            if (hashCalc < 0) {
                hashCalc = hashCalc - Integer.MIN_VALUE;
            }
        }

        // Account for collisions
        hashCalc = hashCalc % buckets.length;
        while ( (buckets[hashCalc] != null) && (!buckets[hashCalc].equals(s)) ) {
            hashCalc++;
            if (hashCalc == buckets.length) {
                hashCalc = 0;
            }
        }

        return hashCalc;

    }

    private boolean checkAndGrow () {
        if ((double)size / buckets.length <= LOAD_MAX) {
            return false;
        }

        String[] oldBuckets = buckets;
        buckets = new String[oldBuckets.length + 300];

        for (int i = 0; i < oldBuckets.length; i++) {
            if (oldBuckets[i] != null) {
                buckets[hash(oldBuckets[i])] = oldBuckets[i];
            }
        }

        return true;
    }

}
