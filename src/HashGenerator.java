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
    private static HashBucket[] HashMap;
    private static final int TABLESIZE = 128;       //manually modify hash table size here

    public static void main(String[] args) throws IOException {
        System.out.println("Reading input...");
        File file = new File(".\\postal_codes_singapore.json");
        Scanner scan = new Scanner(file);

        System.out.println("Hashing...");
        int success = Hash(file);

        if(success==1) System.out.println("Finished hashing data.");

        int key;
        while(true){
            key = scan.nextInt();
            dataType result = search(key);
            print result;
            //this is incomplete code
        }
    }

    public static int Hash(File file){
        //implement hash (use the constant TABLESIZE declared in class!)


        int success = 1;    //if hash is successful. otherwise return 0
        return success;
    }

    public static int hashFunction(int key){
        //implement hash function to convert keys into a hashcode using modulus
        //modulus is constant TABLESIZE!

        return hashcode;
    }
}
