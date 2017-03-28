package assignmentfour;

import java.io.*;
import java.util.*;

/**
 *
 * @author kimchan
 */
public class Client extends MyLinkedList {

    /**
     * The main compares the words in a text file to a dictionary made up of
     * linked lists and then prints out the average comparison for words not
     * found and words found.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        long wordsNotFound = 0;
        long compsFound = 0;
        int[] count = new int[1];
        long wordsFound = 0;
        long compsNotFound = 0;

        MyLinkedList[] dictionary = new MyLinkedList[26];
        for (int i = 0; i < dictionary.length; i++) {
            dictionary[i] = new MyLinkedList<String>();
        }//for

        File rDict = new File("random_dictionary.txt");

        Scanner input = new Scanner(rDict);

        while (input.hasNextLine()) {
            String dictWord = input.nextLine();
            dictWord = dictWord.toLowerCase();
            dictionary[dictWord.charAt(0) - 97].add(dictWord);
        }//while

        File book = new File("oliver.txt");
        Scanner bookScan = new Scanner(book);

        while (bookScan.hasNext()) {
            try {
                String wordToBeSearched = bookScan.next();
                wordToBeSearched = wordToBeSearched.toLowerCase();
                wordToBeSearched = wordToBeSearched.replaceAll("[^a-z]", "");
                int searchIndex = (wordToBeSearched.charAt(0) - 97);

                if (dictionary[searchIndex].contains(wordToBeSearched, count)) {
                    wordsFound++;
                    compsFound += count[0];

                } // if
                else {
                    wordsNotFound++;
                    compsNotFound += count[0];

                } //else
            } //try
            catch (StringIndexOutOfBoundsException ex) {
                bookScan.next();
            } //catch
        }//while

        double avgCompsWordsNotFound = compsNotFound / wordsNotFound;
        System.out.println("Average for word not found: " + avgCompsWordsNotFound);

        double avgCompsWordsFound = compsFound / wordsFound;
        System.out.println("Average for words found: " + avgCompsWordsFound);
    } //main

} // class
