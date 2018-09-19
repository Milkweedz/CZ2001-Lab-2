import java.util.LinkedList;

class HashBucket {
    int[] key;                        //key is the original key before hashing
    int keyCount =0;
    LinkedList<String[]> data;

    HashBucket(int key, String[] data_entry){
        data = new LinkedList<>();
        this.key = new int[100];
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

    void read(int key){
        String[] data_entry = new String[11];
        for (int i=0; i<this.keyCount; i++){
            if(this.key[i] == key){
                data_entry = this.data.get(i);
                System.out.println("Data Entry " + i);
                for (int j=0; j<11; j++){
                    System.out.println(data_entry[j]);
                }
            }
        }
    }
}
