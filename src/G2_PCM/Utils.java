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
    static int[][] splitIntoCores(int max, int cores, int[] array) {
        int [] countAmount = new int[cores];
        int[] blueprint = new int[cores];
        //create the buckets
        int right = (int) Math.ceil(max / cores);
        for(int j = 0; j < cores; j++) {
                blueprint[j] = right;
                right += (int) Math.ceil(max / cores);
        }

        //count per bucket
        for(int i =0; i< array.length;i++) {
            int left = -1;
            for(int j = 0; j < cores; j++) {
//                System.out.println("the start  " + blueprint[j]);
                if(array[i] >= left && array[i] <= blueprint[j]) {
                    countAmount[j]++;
                    break;
                } else {
                    left = blueprint[j];
                }
            }
        }
        // prepare output
        int[][] output = new int[cores][];
        for(int i= 0; i<cores; i++) {
            output[i][i] = output[blueprint[i]][countAmount[i]];
        }
        return output;
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
