import java.util.LinkedList;

public class HashBucket {
    private int key;
    private LinkedList<String> data;
    
    public HashBucket (int k, LinkedList<String> d) {
        key = k;
        data = d;
    }
    public int getKey() {
        return key;
    }
    
    public LinkedList<String> getData() {
        return data;
    }
}
