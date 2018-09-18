import java.util.LinkedList;

class HashBucket {
    int key;                        //key is the original key before hashing
    LinkedList<String[]> data;

    HashBucket(int key, LinkedList<String[]> data){
        this.key = key;
        this.data = data;
    }
}
