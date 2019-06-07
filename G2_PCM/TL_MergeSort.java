package G2_PCM;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class TL_MergeSort {

    private static int[] array;
    static HashMap<String, List<Integer>> subarrays = new HashMap<String, List<Integer>>();
    static int CORES;
    static int MAX;
    
    public static void main(int provCores, int highestValue, int[] anArray) throws InterruptedException{
        // unsorted array 
        MAX = highestValue;
        CORES = provCores;
        array = new int[anArray.length]; array = anArray;

        // create subarrays
        for(int subs=0;subs<CORES;subs++) {
            subarrays.put("todo"+subs, new LinkedList<Integer>());
        }

        for(int i =0; i < array.length; i++ ) {
            if(i > MAX/2) {
                subarrays.get("todo0").add(array[i]);
            } else {
                subarrays.get("todo1").add(array[i]);
            }
        }

        for (int j=0; j<CORES; j++) {
            new mergeThread().run();
        }
        
        // Iterator it = subarrays.entrySet().iterator();
        // while (it.hasNext()) {
        //     Map.Entry pair = (Map.Entry)it.next();
        //     System.out.println(pair.getKey() + " = " + pair.getValue());
        //     it.remove(); // avoids a ConcurrentModificationException
        // }
        
    }
}