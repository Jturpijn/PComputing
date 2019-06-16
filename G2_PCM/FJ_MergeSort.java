package G2_PCM;

import G2_PCM.Utils.*;

import java.util.ArrayList;
import java.util.List;

public class FJ_MergeSort {

    private static int MAX;
    private static int[] array;
    private static List<Integer> Low = new ArrayList<>();
    private static List<Integer> High = new ArrayList<>();

    public static void main(int maxSize, int[] anArray) throws InterruptedException {
        array = anArray;
        MAX = maxSize;

        //split array into two lists
        for(int i=0;i<array.length;i++) {
            if (array[i] < (MAX/2)) {
                Low.add(array[i]);
            } else {
                High.add(array[i]);
            }
        }

        FJ_MergeThread FJmergeLow = new FJ_MergeThread(Low, (MAX /4));
        FJ_MergeThread FJmergeHigh = new FJ_MergeThread(High, (MAX - (MAX /4)));

        FJmergeLow.start();
        FJmergeHigh.start();

        try {
            FJmergeLow.join();
            FJmergeHigh.join();
        } catch(InterruptedException ex) {
            System.out.println("Interrupted in main.");
            System.out.println(ex.getMessage());
        }

//        Utils.print(FJprint(Low, High));
    }

    public static int[] FJprint(List<Integer> left, List<Integer> right) {
        List<Integer> temp = new ArrayList<>();
        temp.addAll(left);
        temp.addAll(right);
        int[] test = temp.stream().mapToInt(Integer::intValue).toArray();
        MergeSort.mergesort(test);
        return test;

    }
}