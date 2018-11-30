package homework.h4;

import java.util.ArrayList;

public interface DictreenaryInterface {

    boolean isEmpty();
    void addWord(String toAdd);
    boolean hasWord(String query);
    String spellCheck(String query);
    ArrayList<String> getSortedWords();

}