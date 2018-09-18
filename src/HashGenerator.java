/*
This class takes a json file as input and returns a hash map of the contents of the json file.
The input file in question contains a list of buildings in singapore ordered by their postal codes.
This class will use the postal codes as keys in the hash map, and the rest of the input data will be stored in
linked lists pointed to by the hash map.

Closed addressing is used.
Linked lists have variable length, so no probing is required.
 */


import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HashGenerator {
    private static HashBucket[] HashMap;
    private static final int TABLESIZE = 11;       //manually modify hash table size here

    public static void main(String[] args) throws IOException {
        System.out.println("Reading input...");
        File file = new File(".\\postal_codes_singapore.json");
        Scanner scan = new Scanner(System.in);

        System.out.println("Hashing...");
        int success = Hash(file);

        if(success==1) System.out.println("Finished hashing data.");

        int key;


        /*while(true){
            key = scan.nextInt();
            dataType result = search(key);
            print result;
            //this is incomplete code
        }*/
    }


    private static int Hash(File file){
        //implement hash (use the constant TABLESIZE declared in class!)

        LinkedList<String[]> temp = new LinkedList();     //list to store data entries
        int key;                                        //key in this example is the postcode
        int hashcode;                                   //hashcode is the output of the hash function
        Pattern regex = Pattern.compile("\\d{6}");      //pattern to extract postcode from data entry
        Matcher matcher;                                //create object to store substring that match postcode pattern


        //create reader for input file: outside the loop to keep track of cursor position in file
        BufferedReader inputStream = null;                    //initialized here to be visible outside of try block

        try {
            inputStream = new BufferedReader(new FileReader(file)); //FileReader always assumes default encoding is OK!
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        }


        //create the hash table
        HashMap = new HashBucket[TABLESIZE];


        //fill the hash table
        for (int i = 0; i < 104; i++) {
            //iterate through entries of input file
            String[] data_entry = getContents(inputStream);


            matcher = regex.matcher(data_entry[6]);         //create matcher object to store matched postcode
                                                            //index 6 is where postal code is stored

            key = Integer.parseInt(matcher.group());        //group() finds postcode, parseInt() turns it to integer

            hashcode = hashFunction(key);

            HashMap[hashcode].key = key;
            HashMap[hashcode].data.add(data_entry);
          
        }

        int success = 1;
        return success; //if hashing is successful, return 1, else 0
    }

    private static int hashFunction(int key){
        //algorithm takes key as input and returns hashcode using modulus
        //modulus based on constant TABLESIZE
        int hashcode;


        hashcode = key % TABLESIZE;
        //intentionally bad hash function to demonstrate key clumping in certain table sizes

        return hashcode;
    }

    private static String[] getContents (BufferedReader input){
        //...checks on aFile are elided
        //StringBuffer contents = new StringBuffer();


        String[] data_entry = new String[216555];



        try {
            String line; //not declared within while loop
            /*
             * readLine is a bit quirky :
             * it returns the content of a line MINUS the newline.
             * it returns null only for the END of the stream.
             * it returns an empty String if two newlines appear in a row.
             */
            int i = 0;
            while ((line = input.readLine()) != null) {
                if (!line.trim().equals("{") && !line.trim().equals("},")) {
                    data_entry[i] = line;
                    i++;
                    //contents.append(System.getProperty("line.separator"));
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    //flush and close both "input" and its underlying FileReader
                    input.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return data_entry;
    }
}
