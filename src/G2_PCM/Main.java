package G2_PCM;


import static G2_PCM.MergeSort.mergesort;
import static G2_PCM.Utils.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
     static final int SIZE = 75;
     static final int MAX = 75;
     static final int CORES = 2;
    static int[] array = random(SIZE, MAX);

    public static void main(String[] args) throws InterruptedException{
        // sequential implementation
        benchSequential();

        // threads and locks implementation
       // benchTLMerge();
    }

    public static void benchSequential() {
        array=random(SIZE, MAX);
        System.out.println(" "); // improve output readability
        long start = System.currentTimeMillis();

        mergesort(array);

        for (int value : array) {
            System.out.println(value);
        }
        long end = System.currentTimeMillis();
        System.out.println("Type: Sequantial MergeSort");
        System.out.println("\t Amount of elements: " + SIZE);
        System.out.println("\t Total time in milliseconds: " + (end - start));
    }

    public static void benchTLMerge() throws InterruptedException{
        array=random(SIZE, MAX);
        System.out.println(" "); // improve output readability

        // parallel implementation 4 core
        long TL2start = System.currentTimeMillis();

        TL_MergeSort.main(CORES, MAX, array);

        long TL2end = System.currentTimeMillis();
        System.out.println("Type: Threads & Locks MergeSort");
        System.out.println("\t Amount of elements: " + SIZE);
        System.out.println("\t Total time in milliseconds: " + (TL2end - TL2start));
    }
}