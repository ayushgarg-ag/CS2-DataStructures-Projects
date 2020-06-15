package cs2.hash;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MorseTester {

    public static void main(String[] args) throws FileNotFoundException {
        CS2HashMap<String,String> map = new CS2HashMap<String,String>(20);

        // Load translation table

        Scanner scanner = new Scanner(new File("/Volumes/GoogleDrive/My Drive/IDEA Projects/cs2-ayushgarg2/src/cs2/hash/MorseCode.txt"));

        while(scanner.hasNext()) {
            String character = scanner.next();
            String morse = scanner.next();
            map.put(character, morse);
        }
        map.put (" ", " ");
        scanner.close();

        // prompt user for phrase and translate it

        Scanner rdr = new Scanner (System.in);
        System.out.println ("Enter a phrase: ");
        String phrase = rdr.nextLine();
        rdr.close();

        System.out.print ("In Morse Code, that's: ");
        for (int i=0; i<phrase.length(); i++) {
            System.out.print(map.get(phrase.substring(i, i+1).toUpperCase()) + " ");
        }

    }

}