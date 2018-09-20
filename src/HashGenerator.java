/*
This class takes a json file as input and returns a hash map of the contents of the json file.
The input file in question contains a list of buildings in singapore ordered by their postal codes.
This class will use the postal codes as keys in the hash map, and the rest of the input data will be stored in
linked lists pointed to by the hash map.

Closed addressing is used.
Linked lists have variable length, so no probing is required.
 */


import java.io.*;
import java.util.Scanner;                           //read user input
import java.util.regex.Matcher;                     //Matcher and Pattern are used to extract the 6 digit postcode
import java.util.regex.Pattern;


public class HashGenerator {
    private static final int TABLESIZE = 1000;       //manually modify hash table size here
    private static HashBucket[] HashMap = new HashBucket[TABLESIZE];
    //A hashbucket class is implemented to manage linked lists
    //the HashMap array stores multiple hashbuckets, its indices are the hashcodes produced from the hashFunction

    public static void main(String[] args) {
        System.out.println("Reading input...");
        File file = new File(".\\postal_codes_singapore.json");


        System.out.println("Hashing...");
        long start = System.nanoTime();             //To get the start time to compute duration taken for hashing
        int success = Hash(file);                   //call hash function, returns 1 for success and 0 for hash failure
                                                    //remnant of debugging
        long end = System.nanoTime();               //To get the end time to compute duration taken for hashing.
        if(success==1) System.out.println("Finished hashing data.");
        double avgCPU = (double)((end - start)/TABLESIZE)/1E9;    //To get the average CPU time taken for hashing
        System.out.println("Average CPU time taken : " + (avgCPU*1E9) + " nanoseconds");
        System.out.println("Average CPU time taken : " + avgCPU + " seconds");
        Scanner scan = new Scanner(System.in);      //scanner to read user input
        int key;                                    //key is postal code the user is searching for
        int noOfSearch = 0;                         // To keep track of the number of search the user did
        int totalComp = 0;                          // To keep track of the total number of comparisons did 
        while(true){
            System.out.print("Input postal code to search: ");
            key = scan.nextInt();
            if (key == -1) break;                   //user can type -1 to exit loop
            noOfSearch++;
            totalComp += search(key);
        }
        System.out.println("Average number of key comparisons : " + (totalComp)/noOfSearch);
    }


    private static int Hash(File file){
        //implement hash (use the constant TABLESIZE declared in class!)
        int success = 1;                                //checks if hashing is successful


        String keyString;                               //key in string format
        int key;                                        //key in this example is the postcode
        int hashcode;                                   //hashcode is the output of the hash function,
                                                        // and the index where the list is stored

        Pattern regex = Pattern.compile("(\\d{6})");    //6-digit pattern, to extract postcode from data entry
        Matcher matcher;                                //create object to store substring that match postcode pattern
        //as a friend pointed out, we could have just used a JSON parser, but what's the fun in that?



        //create reader for input file: outside the loop to keep track of cursor position in file
        BufferedReader inputStream = null;              //initialized here to be visible outside of try block

        try {
            inputStream = new BufferedReader(new FileReader(file));
            //open input file and get ready to read
            //FileReader always assumes default encoding is OK!
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
            //necessary error catching, purely syntactic
        }



        //fill the hash table
        for (int i = 0; i < 1000; i++) {                     //i is an arbitrary limit to reduce program runtime in debugging
                                                            //make i very large (>100000) when reading entire file.
            //iterate through entries of input file
            if (inputStream == null) {                      //exit condition when i is extremely large
                break;
            }


            //getContents takes a JSON object and converts it into a string array
            String[] data_entry = getContents(inputStream);
            //getContents is called once for each object, the cursor's position in file is retained after each call
            //because the BufferedReader inputStream is passed as argument


            matcher = regex.matcher(data_entry[6]);         //create matcher object to store matched postcode
                                                            //index 6 is where postal code is stored in the JSON file

            if (matcher.find()) {
                keyString = matcher.group(0);
                key = Integer.parseInt(keyString);          //group() finds postcode, parseInt() turns it to integer
                
                hashcode = hashFunction(key);

                if (HashMap[hashcode] == null) {
                    HashMap[hashcode] = new HashBucket(key, data_entry);
                }
                else {
                    HashMap[hashcode].append(key, data_entry);
                }

            }
        }


        return success;                                     //if hashing is successful, return 1, else 0
    }

    private static int hashFunction(int key){
        //algorithm takes key as input and returns hashcode using modulus
        //modulus based on constant TABLESIZE
        int hashcode = 0;
        //hashcode = key % TABLESIZE;     //First hashing algorithm - Mod method
        while (key > 0) {                 //Second hashing algorithm - Folding method
            hashcode += key % 100; 
            key/=100;
        }
        hashcode/=TABLESIZE;
        //intentionally bad hash function to demonstrate key clumping in certain table sizes

        return hashcode;
    }

    private static String[] getContents (BufferedReader input){
        //takes a buffered input stream, crops out json objects, returns them in string arrays


        String[] data_entry = new String[100000];                   //arbitrarily large string buffer



        try {
            String line; //not declared within while loop
            /*
             * readLine is a bit quirky :
             * it returns the content of a line MINUS the newline.
             * it returns null only for the END of the stream.
             * it returns an empty String if two newlines appear in a row.
             */


            boolean isContent;
            line = input.readLine();                            //enables the first comparison in while loop

            while (line != null) {
                if (line.trim().equals("},")) break;                     //break out of loop if at end of data_entry

                isContent = !line.trim().equals("{") && !line.trim().equals("[");
                //this line makes sure we only put actual content in string array. it removes json formatting symbols

                if (isContent) {
                    for(int i=0; i<11; i++) {
                        data_entry[i] = line;                   //write to string array
                        line = input.readLine();                //read next line
                    }
                }
                else line = input.readLine();                   //go to next line if there is nothing to store to array
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (input == null) {
                    //flush and close both "input" and its underlying FileReader
                    input.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return data_entry;                                      //data entry is a string array
    }


    private static int search(int key){
        int hashcode = hashFunction(key);
        int comparison;
        if (HashMap[hashcode] == null){                         //this is important to avoid NullPointerException
            System.out.println("Record not found!");
            comparison = 1;
        }
        else {
            comparison = HashMap[hashcode].slowread(key);                        //read is a method of HashBucket
        }
        return comparison;
    }

}
