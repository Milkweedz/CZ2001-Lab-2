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



    /*
        It is actually much faster to find something in the hashmap without traversing the linked lists.
        The following function uses the integer array of keys as a direct lookup table.
        The searcher looks through the key array to see if there is a matching key.
        If there is, the index of the matching key will be the same as the object correlating the key.

        This method may not in fact be faster if the linked list is still traversed within the implementation of the
        get() method of the LinkedList class.
     */

    int read(int key){
        int i;
        String[] data_entry;
        for (i=0; i<this.keyCount; i++){
            if(this.key[i] == key){
                data_entry = this.data.get(i);
                System.out.println("Data Entry " + i);
                for (int j=0; j<11; j++){
                    System.out.println(data_entry[j]);
                }
            }
        }
        System.out.println("Number of key comparsions : " + i);
        return i;
    }


    /*
        If the above method indeed does not traverse the linked list and is faster, we may want a read method that is
        slower, to exaggerate the time difference caused by actually iterating through the string.

        This method searches each linked list for a key match, but we are not sure if it is slower.
     */

    int slowread(int key){
        String[] data_entry;
        String keyString = Integer.toString(key);
        int i;
        for (i=0; i<this.data.size(); i++){
            data_entry = this.data.get(i);
            if (data_entry[6].contains(keyString)){             //if finds matching postal code
                System.out.println("Data Entry " + i);          //data entry number

                for (int j=0; j<11; j++){                       //prints out entire data entry line by line
                    System.out.println(data_entry[j]);
                }
            }
        }
        System.out.println("Number of key comparsions : " + i);
        return i;
    }
}
