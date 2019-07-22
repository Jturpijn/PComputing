package G2_PCM;

import G2_PCM.Utils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

import static G2_PCM.MergeSort.mergesort;

public class FJ_MergeSort {

    private static int MAX;
    static int[] array;

    public static void main(int maxSize, int[] anArray) throws InterruptedException {
        array = anArray;
        MAX = maxSize;

        FJ_MergeThread FJmerge = new FJ_MergeThread(array, MAX);

        FJmerge.start();

        try {
            FJmerge.join();
        } catch(InterruptedException ex) {
            System.out.println("Interrupted in main.");
            System.out.println(ex.getMessage());
        }
    }

}