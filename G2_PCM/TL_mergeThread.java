package G2_PCM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static G2_PCM.MergeSort.mergesort;
import static G2_PCM.TL_MergeSort.*;
import static java.lang.Thread.sleep;

public class TL_mergeThread implements Runnable {
    int[] temparr;
    List<Integer> templ = new ArrayList<>();
    int threadcount;

    public TL_mergeThread(int count) {
        threadcount = count;
        new Thread(this).start();
    }

    public void run() {
        //System.out.println("mergethread : " + threadcount);
//            for (List<Integer> list : splitLists) {
//                if (list.isEmpty()) {
//                    System.out.println("this is empty");
//                } else {
//                    temparr = list.stream().mapToInt(Integer::intValue).toArray();
//                    mergesort(temparr);
//                    templ = Arrays.stream(temparr).boxed().collect(Collectors.toList());
//
//                    splitLists.set(splitLists.indexOf(list), templ);
//                }
//            }
            try {
                    List<Integer> list = splitLists.get(threadcount);

                    if (list.isEmpty()) {
                        System.out.println("I'm thread : " + threadcount + " and I found an empty");
                    } else {
                        temparr = list.stream().mapToInt(Integer::intValue).toArray();
                        mergesort(temparr);
                        templ = Arrays.stream(temparr).boxed().collect(Collectors.toList());

                        splitLists.set(splitLists.indexOf(list), templ);
                        finished_merging++;
                    }
            } catch(IndexOutOfBoundsException ex) {
                System.out.println(ex.getMessage());
            }
    }
}
