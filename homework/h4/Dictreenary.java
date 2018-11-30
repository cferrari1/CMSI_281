package homework.h4;

import java.util.ArrayList;

public class Dictreenary implements DictreenaryInterface {

    // Fields
    // -----------------------------------------------------------
    TTNode root;
    
    
    // Constructor
    // -----------------------------------------------------------
    Dictreenary () {}
    
    
    // Methods
    // -----------------------------------------------------------
    
    public boolean isEmpty () {
        return (root == null);
    }
    
    public void addWord (String toAdd) {
        toAdd = normalizeWord(toAdd);
        recurseAdd(toAdd,root,0);
    }
    
    public boolean hasWord (String query) {
        query = normalizeWord(query);
        throw new UnsupportedOperationException();
    }
    
    public String spellCheck (String query) {
        throw new UnsupportedOperationException();
    }

    public ArrayList<String> getSortedWords () {
        throw new UnsupportedOperationException();
    }

    public void preorderPrint (TTNode n) {
        if (n == null) {return;}
        System.out.println(root.mid.letter);

    }


    // Helper Methods
    // -----------------------------------------------------------
    
    private String normalizeWord (String s) {
        // Edge case handling: empty Strings illegal
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException();
        }
        return s.trim().toLowerCase();
    }
    
    /*
     * Returns:
     *   int less than 0 if c1 is alphabetically less than c2
     *   0 if c1 is equal to c2
     *   int greater than 0 if c1 is alphabetically greater than c2
     */
    private int compareChars (char c1, char c2) {
        return Character.toLowerCase(c1) - Character.toLowerCase(c2);
    }
    
    // [!] Add your own helper methods here!

    private TTNode searchPrefix(char searched, TTNode start) {

        if (start == null) {
            return null;
        }

        if (start.letter == searched) {
            return start;
        }

        if (searched < start.letter) {
            return searchPrefix(searched, start.left);
        }

        return searchPrefix(searched, start.right);
    }

    private void recurseAdd (String toAdd, TTNode start, int currIndex) {
        char currLetter = toAdd.charAt(currIndex);

        if (root == null) {
            root = new TTNode(toAdd.charAt(0), false);
            if (toAdd.length() == 1) {
                root.wordEnd = true;
                return;
            } else {
                recurseAdd (toAdd, root, 1);
            }
        }

        TTNode node = searchPrefix(currLetter, start);

        if (node != null) {
            recurseAdd (toAdd, node, currIndex + 1);
        }

        if (start.mid == null) {
            start.mid = new TTNode(currLetter, false);
            if (currIndex == toAdd.length() - 1) {
                start.mid.wordEnd = true;
                return;
            } else {
                recurseAdd (toAdd, start.mid, currIndex + 1);
            }
        }

        if (currLetter < start.mid.letter) {
            recurseAdd (toAdd, start.left, currIndex);
        }

        if (currLetter > start.mid.letter) {
            recurseAdd (toAdd, start.right, currIndex);
        }


/*        if (node != null) {
            if (currIndex == toAdd.length() - 1) {
                node.wordEnd = true;
                return;
            } else {
                recurseAdd(toAdd, node.mid, currIndex + 1);
            }
        }



        if (start == null) {
            start = new TTNode(currLetter, false);
            System.out.println(start == root.mid);

            if (currIndex == toAdd.length() - 1) {
                start.wordEnd = true;
                System.out.println("TRUE?");
                return;
            } else {
                recurseAdd(toAdd, start.mid, currIndex + 1);
            }
        } else if (currLetter < start.letter) {
            recurseAdd(toAdd, start.left, currIndex);
        } else {
            recurseAdd(toAdd, start.right, currIndex);
        }*/
    }


    
    // TTNode Internal Storage
    // -----------------------------------------------------------
    
    /*
     * Internal storage of Dictreenary words
     * as represented using a Ternary Tree with TTNodes
     */
    private class TTNode {
        
        boolean wordEnd;
        char letter;
        TTNode left, mid, right;
        
        TTNode (char c, boolean w) {
            letter  = c;
            wordEnd = w;
        }
        
    }
    
}
