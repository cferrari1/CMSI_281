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
        root = recurseAdd(toAdd,root,0);
    }
    
    public boolean hasWord (String query) {
        query = normalizeWord(query);
        TTNode node = search(query,root);

        return (node != null && node.wordEnd);
    }
    
    public String spellCheck (String query) {
        query = normalizeWord(query);

        if (hasWord(query)) {
            return query;
        }

        int failPoint = -1;
        int prevFailPoint;
        String newQuery1 = null;
        String newQuery2 = null;

        while (true) {
            prevFailPoint = failPoint;
            failPoint = findFail(query, root);

            if (failPoint != query.length() - 1) {
                newQuery1 = preSwap(failPoint, query);
            }

            if (failPoint != 0) {
                newQuery2 = postSwap(failPoint, query);
            }

            if (newQuery1 != null) {
                query = newQuery1;
            } else if (newQuery2 != null) {
                query = newQuery2;
            } else {
                return null;
            }



            if (hasWord(query)) {
                return query;
            } else {
                continue;
            }
        }
    }

    public ArrayList<String> getSortedWords () {
        throw new UnsupportedOperationException();
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

    private TTNode recurseAdd (String toAdd, TTNode node, int currIndex) {
        // Check if done
        if (currIndex == toAdd.length() ) {
            return node;
        }

        char currLetter = toAdd.charAt(currIndex);

        // Check if char is already part of tree
        if (currIndex + 1 == toAdd.length() && node != null && node.letter == currLetter) {
            node.wordEnd = true;
        }

        if (node == null) {
            node = new TTNode(currLetter, currIndex == toAdd.length() - 1);
        }

        int compare = compareChars(currLetter, node.letter);

        // Same char, move on to next index
        if (compare == 0) {
            node.mid = recurseAdd(toAdd, node.mid, currIndex + 1);
        }

        // Not the same char, less than, move left, same index
        if (compare < 0) {
            node.left = recurseAdd(toAdd, node.left, currIndex);
        }

        // Not the same char, greater than, move right, same index
        if (compare > 0) {
            node.right = recurseAdd(toAdd, node.right, currIndex);
        }

        return node;
    }

    private TTNode recurseSearch(char searched, TTNode start) {

        if (start == null) {
            return null;
        }

        int compare = compareChars(searched,start.letter);

        if (compare == 0) {
            return start;
        }

        if (compare < 0) {
            return recurseSearch(searched, start.left);
        }

        return recurseSearch(searched, start.right);
    }

    private TTNode search(String searched, TTNode node) {
        char currLetter;

        for (int i = 0; i < searched.length(); i++) {
            if (i != 0) {
                node = node.mid;
            }

            if (node == null) {
                break;
            }

            currLetter = searched.charAt(i);
            node = recurseSearch(currLetter, node);

            if (node == null) {
                break;
            }

        }

        return node;
    }

    private int findFail(String searched, TTNode node) {
        char currLetter;
        int failPoint = -1;

        for (int i = 0; i < searched.length(); i++) {
            if (i != 0) {
                node = node.mid;
            }

            if (node == null) {
                break;
            }

            currLetter = searched.charAt(i);
            node = recurseSearch(currLetter, node);

            if (node == null) {
                failPoint = i;
                break;
            }

        }

        return failPoint;
    }

    private String preSwap(int prevFailPoint, String query) {
        if (query.length() - 1 < failPoint + 2) {
            query = query.substring(0, failPoint) + query.charAt(failPoint + 1) + query.charAt(failPoint);
        } else {
            query = query.substring(0, failPoint) + query.charAt(failPoint + 1) + query.charAt(failPoint) + query.substring(failPoint + 2);
        }


        if (failPoint <= prevFailPoint) {
            return null;
        }
        return query;

    }

    private String postSwap(int prevFailPoint, String query) {



        if (failPoint <= prevFailPoint) {
            return null;
        }
        return query;
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
