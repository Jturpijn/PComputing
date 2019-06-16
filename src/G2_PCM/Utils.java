package G2_PCM; 

import java.util.List;
import java.util.Random;

public class Utils {
    // create a random number with a maximum of n.
    static int[] random(final int n, final int m) {
        final int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = new Random().nextInt((m -0) + 1);
        }

        return a;
    }

    // Split array into buckets
    static void splitIntoCores(int max, int cores, int[] array) {
        List<List<Integer>> allLists = TL_MergeSort.allLists;

        if(cores == 2) {
            for(int i =0; i < array.length; i++ ) {
                if(array[i] < max/2) {
                    allLists.get(0).add(array[i]);
                } else { allLists.get(1).add(array[i]); }
            }
        } else if (cores == 3 ) {
            for(int i =0; i < array.length; i++ ) {
                if(array[i] < max/2) {
                    if(array[i] < max/4) {
                        allLists.get(0).add(array[i]);
                    } else { allLists.get(1).add(array[i]); }
                } else { allLists.get(2).add(array[i]); }
            }
        } else if (cores == 4) {
            for(int i =0; i < array.length; i++ ) {
                if(array[i] < max/2) {
                    if(array[i] < max/4) {
                        allLists.get(0).add(array[i]);
                    } else { allLists.get(1).add(array[i]); }
                } else {
                    if(array[i] < (max - (max/4))) {
                        allLists.get(2).add(array[i]);
                    } else { allLists.get(3).add(array[i]); }
                }
            }
        }
    }

    // Merge of the mergesort
    static void merge(final int[] array, final int[] helper, final int low, final int middle, final int high) {
        for (int i = low; i <= high; i++) {
            helper[i] = array[i];
        }

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        while (helperLeft <= middle && helperRight <= high) {
            if (helper[helperLeft] <= helper[helperRight]) {
                array[current] = helper[helperLeft++];
            } else {
                array[current] = helper[helperRight++];
            }
            current++;
        }

        while (helperLeft <= middle) {
            array[current++] = helper[helperLeft++];
        }
    }

    static void print(final int[] arr) {
        for (int anArr : arr) System.out.print(anArr + " ");
        System.out.println();
    }
}
