import java.util.LinkedList;

/*
The hash bucket is a slot in the hash table where linked lists can be pointed to.
The hashcode will lead any search method to the matching hash bucket, and then read() is called to find the exact data entry.
An integer array containing keys is included in this implementation to allow for a potentially faster way to search the
linked list.
 */


class HashBucket {
    private int[] key;                          //key is the original key before hashing
                                                //this int array stores all keys of objects allocated to this hashbucket
                                                //this allows for a faster way to search, without iterating through llist
    private int keyCount =0;                    //keyCount = total number of keys
    private LinkedList<String[]> data;          //this is a linked list where data entries are stored


    //Constructor for the hashbucket, called when the hashbucket is not yet created/occupied
    HashBucket(int key, String[] data_entry){
        data = new LinkedList<>();
        this.key = new int[1000];                //the maximum number of objects this bucket can store is 10000
        this.key[0] = key;
        this.keyCount++;
        this.data.push(data_entry);
    }

    void append(int key, String[] data_entry){
        //this method is used to append a datum to the hash bucket
        this.key[keyCount] = key;
        keyCount++;
        this.data.push(data_entry);
    }

   int read(int key){
        String[] data_entry;
        String keyString = Integer.toString(key);
        int i;
        boolean found = false;
        for (i=0; i<this.data.size(); i++){
            data_entry = this.data.get(i);
            if (data_entry[6].contains(keyString)){             //if finds matching postal code
                System.out.println("Data Entry " + i);          //data entry number

                for (int j=0; j<11; j++){                       //prints out entire data entry line by line
                    System.out.println(data_entry[j]);
                }
                found = true;
            }
        }
        if (!found)
            System.out.println("Record not found !");
        System.out.println("Number of key comparsions : " + i);
        return i;
    }
}
