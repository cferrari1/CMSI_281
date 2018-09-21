package classwork.c1;

// Christopher Ferrari

import java.util.Scanner;

public class UniqueWords {

    private static Scanner input = new Scanner(System.in);

    public static void main( String[] args ) {

        System.out.println("Enter a sentence: ");
        String sentence = input.nextLine();
        String arr[] = sentence.split(" ");
        String unique = "";

        for (int i = 0; i < arr.length; i++) {
            if ( !( unique.contains( arr[i] ) ) ) {
                unique += arr[i] + " ";
            }
        }

        String uniqueArr[] = unique.split(" ");
        System.out.println(uniqueArr.length);

    }

}
