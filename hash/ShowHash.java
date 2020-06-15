package cs2.hash;

import java.util.Scanner;

public class ShowHash {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println ("Enter a String");
        String str = reader.nextLine();
        while (!str.equals("q")) {
            System.out.println ("Hash Code for " + str + " is " +
                    str.hashCode() % 13);
            str = reader.nextLine();
        }
    }

}
