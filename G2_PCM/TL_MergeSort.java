package G2_PCM;

import java.util.LinkedList;
import java.util.List;

import static G2_PCM.Utils.merge;
import static G2_PCM.Utils.print;
import static java.lang.Thread.sleep;

public class TL_MergeSort {

    static List<List<Integer>> splitLists = new LinkedList<>();
    static List<List<Integer>> mergeLists = new LinkedList<>();
    static int[] array;
    static int MAX;
    static int CORES;

    public static void main(int provCores, int highestValue, int[] anArray) throws InterruptedException{
        // unsorted array 
        MAX = highestValue;
        CORES = provCores; // provided cores
        array = new int[anArray.length]; array = anArray;

        // create subarrays
        for(int subs=0;subs<CORES;subs++) {
            List<Integer> todo = new LinkedList<>();
            splitLists.add(todo);
        }

        for (int j=0; j<CORES; j++) {
            new TL_splitThread().run();
        }

        for (int j=0; j<CORES; j++) {
            new TL_mergeThread().run();
        }

        System.out.println("printing :");
        for (List<Integer> list : splitLists) {
            System.out.print("list number : " + splitLists.indexOf(list) + "  ");
            print(list.stream().mapToInt(Integer::intValue).toArray());
        }
        
        // Iterator it = subarrays.entrySet().iterator();
        // while (it.hasNext()) {
        //     Map.Entry pair = (Map.Entry)it.next();
        //     System.out.println(pair.getKey() + " = " + pair.getValue());
        //     it.remove(); // avoids a ConcurrentModificationException
        // }
        
    }
}