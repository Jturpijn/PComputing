package G2_PCM;

import java.sql.SQLOutput;
import java.util.*;


public class TL_MergeSort {

    private static int[] array;
    static ArrayList<List<Integer>> allLists = new ArrayList<>();
    static int CORES;
    static int MAX;
    static int[][] blueprint= new int[CORES][];
    
    public static void main(int provCores, int highestValue, int[] anArray) throws InterruptedException{
        // unsorted array 
        MAX = highestValue;
        CORES = provCores;
        array = new int[anArray.length]; array = anArray;

        // create subarrays
        for(int subs=0;subs<CORES;subs++) {
            allLists.add(subs, new ArrayList<>());
        }

        blueprint = Utils.splitIntoCores(MAX, CORES, array);

        for (int i=0; i<CORES; i++) {
            for (int j=0; j<CORES; j++) {
                new mergeThread(j, blueprint[i], blueprint[i][j], array).run();
            }
        }

//        for (List<Integer> list : allLists) {
//            System.out.print("list number : " + allLists.indexOf(list) + "  ");
//            Utils.print(list.stream().mapToInt(Integer::intValue).toArray());
//        }
    }
}