package G2_PCM;

import static G2_PCM.TL_MergeSort.main;
import static G2_PCM.MergeSort.mergesort;
import static G2_PCM.Utils.*;

public class Main {
    private static final int SIZE = 20_000_000;
    private static final int MAX = 100;
    private static final int CORES = 3;
    private static int[] array = random(SIZE, MAX);

    public static void main(String[] args) throws InterruptedException{
        // sequential implementation
        //benchSequential();

        // threads and locks implementation
        //benchTLMerge();

        // fork join pool implementation
        benchFJMerge();
    }

    private static void benchSequential() {
        array=random(SIZE, MAX);
        System.out.println(" "); // improve output readability

        long start = System.currentTimeMillis();

        mergesort(array);

        long end = System.currentTimeMillis();
        System.out.println("Type: Sequantial MergeSort");
        System.out.println("\t Amount of elements: " + SIZE);
        System.out.println("\t Total time in milliseconds: " + (end - start));
    }

    private static void benchTLMerge() throws InterruptedException{
        array=random(SIZE, MAX);
        System.out.println(" "); // improve output readability

        // parallel implementation 4 core
        long TL2start = System.currentTimeMillis();

        TL_MergeSort.main(MAX, array);

        long TL2end = System.currentTimeMillis();
        System.out.println("Type: Threads & Locks MergeSort");
        System.out.println("\t Amount of elements: " + SIZE);
        System.out.println("\t Total time in milliseconds: " + (TL2end - TL2start));
    }

    private static void benchFJMerge() throws InterruptedException{
        array=random(SIZE, MAX);
        System.out.println(" "); // improve output readability

        // parallel implementation 4 core
        long FJ2start = System.currentTimeMillis();

        FJ_MergeSort.main(MAX,array);

        long FJ2end = System.currentTimeMillis();
        System.out.println("Type: Fork Join MergeSort");
        System.out.println("\t Amount of elements: " + SIZE);
        System.out.println("\t Total time in milliseconds: " + (FJ2end - FJ2start));
    }
}