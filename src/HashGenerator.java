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
        while(true){
            key = scan.nextInt();
            dataType result = search(key);
            print result;
            //this is incomplete code
        }
    }

    public static int Hash(File file){
        //implement hash (use the constant TABLESIZE declared in class!)

        LinkedList<String> temp = new LinkedList();     //list to store data entries
        int j = 0;
        int key = 0;
        //this is the size of the hash table - a prime number is best


        //create the hash table
        HashMap = new HashBucket[TABLESIZE];
        for (int i = 0; i < TABLESIZE; i++)
            HashMap[i].hashcode = i;


        //fill the hash table so that every slot contains a space
        for (int i = 0; i < 104; i++) {
            //iterate through entries of input file

            String[] data_entry = getContents(file);
            if (!data_entry.trim().equals("{") && !data_entry[i].trim().equals("},")) {
                if (data_entry[i].trim().substring(1,7).equals("POSTAL")) {
                    key = Integer.parseInt(data_entry[i].trim().substring(11,17));
                } else {
                    temp.add(data_entry[i].trim());                   
                }
            } else if (data_entry[i].trim().equals("},")) {
                HashMap[j] = new HashBucket(key,temp);
                j++;                  
            }                   
        }
        int i = 0;
        int first = 0;
        int last = 10;
        while (HashMap[i] != null) {
            System.out.println(HashMap[i].getKey());
            System.out.println(HashMap[i].getData().subList(first, last));
            i++;
            first+=10;
            last+=10;
        }

        int success = 1;
        return success; //if hashing is successful, return 1, else 0
    }

    public static int hashFunction(int key){
        //algorithm takes key as input and returns hashcode using modulus
        //modulus based on constant TABLESIZE
        int hashcode;

        return hashcode;
    }

    public static String[] getContents (File aFile){
        //...checks on aFile are elided
        //StringBuffer contents = new StringBuffer();


        String[] data_entry = new String[216555];


        //declared here only to make visible to finally clause
        BufferedReader input = null;
        try {
            //use buffering, reading one line at a time
            //FileReader always assumes default encoding is OK!
            input = new BufferedReader(new FileReader(aFile));
            String line = null; //not declared within while loop
            /*
             * readLine is a bit quirky :
             * it returns the content of a line MINUS the newline.
             * it returns null only for the END of the stream.
             * it returns an empty String if two newlines appear in a row.
             */
            int i = 0;
            while ((line = input.readLine()) != null) {
                data_entry[i] = line;
                i++;
                //contents.append(System.getProperty("line.separator"));
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
