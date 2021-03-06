package G2_PCM;

import java.util.List;
import java.util.Random;

import static G2_PCM.TL_MergeSort.splitLists;


public interface Utils {
    // create a random number with a maximum of n.
    static int[] random(final int n, final int m) {
        final int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = new Random().nextInt(m + 1);
        }

        return a;
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

    static void split(List<Integer> tempLow, List<Integer> tempHigh, int head, int pivot) {
        for(int i = 0; i< splitLists.get(head).size(); i++) {
            int number = splitLists.get(head).get(i);
            if (number < pivot) {
                tempLow.add(number);
            } else {
                tempHigh.add(number);
            }
        }
    }

    static void print(final int[] arr) {
        for (int anArr : arr) System.out.print(anArr + " ");
        System.out.println();
    }
}