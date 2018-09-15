/*
This class takes a json file as input and returns a hash map of the contents of the json file.
The input file in question contains a list of buildings in singapore ordered by their postal codes.
This class will use the postal codes as keys in the hash map, and the rest of the input data will be stored in
linked lists pointed to by the hash map.

Closed addressing is used.
Linked lists have variable length, so no probing is required.
 */


import java.io.File;
import java.io.IOException;
import java.util.Scanner;



public class HashGenerator {
    private static String[] test;
    private static HashBucket[] HashMap;

    public static void main(String[] args) throws IOException {
        System.out.println("Reading input...");
        File file = new File(".\\postal_codes_singapore.json");
        Scanner scan = new Scanner(file);

        System.out.println("Hashing...");
        Hash(file);
    }

    public static void Hash(File file){

    }
}
