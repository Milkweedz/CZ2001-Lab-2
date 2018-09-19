import java.util.LinkedList;

class HashBucket {
    private int[] key;                        //key is the original key before hashing
    private int keyCount =0;
    LinkedList<String[]> data;

    HashBucket(int key, LinkedList<String[]> data){
        this.key = new int[100];
        this.key[0] = key;
        this.data = data;
    }

    void append(int key, String[] data_entry){
        //this method is used to append a datum to the hash bucket
        this.key[keyCount] = key;
        keyCount++;
        this.data.push(data_entry);
        System.out.println("DATASIZE" + this.data.size());
        System.out.println(this.key[0]);
    }

    void read(int key){
        String[] data_entry = new String[11];
        for (int i=0; i<this.key.length; i++){
            System.out.println("readkey" + key);
            System.out.println(this.key[i]);
            if(this.key[i] == key){
                System.out.println("DEBUG i = " + i);
                data_entry = this.data.get(i);
                System.out.println("Data Entry " + i);
                for (int j=0; j<11; j++){
                    System.out.println(data_entry[j]);
                }
            }
        }
    }
}
