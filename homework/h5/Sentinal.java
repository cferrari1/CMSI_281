package homework.h5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sentinal implements SentinalInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------

    private PhraseHash posHash, negHash;


    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------

    Sentinal (String posFile, String negFile) throws FileNotFoundException {
        Scanner posScan = new Scanner(new File(posFile));
        Scanner negScan = new Scanner(new File(negFile));

        posHash = new PhraseHash();
        negHash = new PhraseHash();

        while (posScan.hasNextLine()) {
            posHash.put(posScan.nextLine());
        }

        while (negScan.hasNextLine()) {
            negHash.put(negScan.nextLine());
        }
    }


    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------

    public void loadSentiment (String phrase, boolean positive) {
        if (positive) {
            posHash.put(phrase);
        }

        negHash.put(phrase);
    }

    public void loadSentimentFile (String filename, boolean positive) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(filename));

        while (scan.hasNextLine()) {
            if (positive)
                posHash.put(scan.nextLine());
            else
                negHash.put(scan.nextLine());
        }

    }

    public String sentinalyze (String filename) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(filename));
        int posCount = 0;
        int negCount = 0;
        int longest = 0;
        String currLine;
        String[] wordArray;
        String[] phraseArray;

        if (posHash.longestLength() > negHash.longestLength())
            longest = posHash.longestLength();
        else
            longest = negHash.longestLength();


        while (scan.hasNextLine()) {
            currLine = scan.nextLine();
            currLine = currLine.toLowerCase().trim();

            wordArray = currLine.split(" ");

            for (int i = 1; i <= longest; i++) {
                phraseArray = new String[wordArray.length - i + 1];

                for (int j = 0; j <= wordArray.length - i; j++) {
                    phraseArray[j]  = wordArray[j];

                    for (int k = 1; k < i; k++) {
                        phraseArray[j]  += " ";
                        phraseArray[j]  += wordArray[j + k];
                    }
                }

                for (int j = 0; j < phraseArray.length; j++) {
                    if (posHash.get(phraseArray[j]) != null) {
                        posCount++;
                    }

                    if (negHash.get(phraseArray[j]) != null) {
                        negCount++;
                    }
                }
            }
        }

        int totalCount = posCount - negCount;
        if (totalCount > 0) {
            return "positive";
        } else if (totalCount < 0) {
            return "negative";
        }

        return "neutral";

    }
}
