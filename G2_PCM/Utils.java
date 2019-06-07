package G2_PCM;

import java.util.Random;

public interface Utils {
    // create a random number with a maximum of n.
    static int[] random(final int n, final int m) {
        final int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = new Random().nextInt((m -0) + 1);
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

    static void print(final int[] arr) {
        for (int anArr : arr) System.out.print(anArr + " ");
        System.out.println();
    }
}