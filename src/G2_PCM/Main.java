package G2_PCM;

import static G2_PCM.MergeSort.mergesort;
import static G2_PCM.Utils.*;

public class Main {
    private static final int SIZE = 10000000;
    private static final int MAX = 10000;

    public static void main(String[] args){
        int[] array = random(SIZE, MAX);

        // sequential implementation
        long start = System.currentTimeMillis();

        mergesort(array);

        long end = System.currentTimeMillis();
        System.out.println("Type: Sequantial MergeSort");
        System.out.println("\t Amount of elements: " + SIZE);
        System.out.println("\t Total time in milliseconds: " + (end - start));

        // // reset array to random values
        // array=random(SIZE);
        // System.out.println(" "); // improve output readability

        // // parallel implementation 2 cores
        // long pstart = System.currentTimeMillis();
        // int cores = Runtime.getRuntime().availableProcessors()-14; // 16 total

        // final ForkJoinPool forkJoinPool=new ForkJoinPool(cores);
        // forkJoinPool.invoke(new ParallelMergeSort(array,0,array.length-1));
        // Arrays.parallelSort(array);

        // long pend = System.currentTimeMillis();
        // System.out.println("Type: Parallel MergeSort");
        // System.out.println("\t Amount of cores: " + cores);
        // System.out.println("\t Amount of elements: " + SIZE);
        // System.out.println("\t Total time in milliseconds: " + (pend - pstart));

        // // reset array to random values
        // array=random(SIZE);
        // System.out.println(" "); // improve output readability

        // // parallel implementation 3 core
        // long p1start = System.currentTimeMillis();
        // int p1cores = Runtime.getRuntime().availableProcessors()-13; // 16 total

        // final ForkJoinPool C3forkJoinPool=new ForkJoinPool(cores);
        // C3forkJoinPool.invoke(new ParallelMergeSort(array,0,array.length-1));
        // Arrays.parallelSort(array);

        // long p1end = System.currentTimeMillis();
        // System.out.println("Type: Parallel MergeSort");
        // System.out.println("\t Amount of cores: " + p1cores);
        // System.out.println("\t Amount of elements: " + SIZE);
        // System.out.println("\t Total time in milliseconds: " + (p1end - p1start));

        // // reset array to random values
        // array=random(SIZE);
        // System.out.println(" "); // improve output readability

        // // parallel implementation 4 core
        // long p2start = System.currentTimeMillis();
        // int p2cores = Runtime.getRuntime().availableProcessors()-12; // 16 total

        // final ForkJoinPool C4forkJoinPool=new ForkJoinPool(p2cores);
        // C4forkJoinPool.invoke(new ParallelMergeSort(array,0,array.length-1));
        // Arrays.parallelSort(array);

        // long p2end = System.currentTimeMillis();
        // System.out.println("Type: Parallel MergeSort");
        // System.out.println("\t Amount of cores: " + p2cores);
        // System.out.println("\t Amount of elements: " + SIZE);
        // System.out.println("\t Total time in milliseconds: " + (p2end - p2start));
    }
}