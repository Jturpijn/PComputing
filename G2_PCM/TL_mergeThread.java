package G2_PCM;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static G2_PCM.MergeSort.mergesort;
import static G2_PCM.TL_MergeSort.*;
import static java.lang.Thread.sleep;

public class TL_mergeThread implements Runnable {
    int[] temparr;
    List<Integer> templ = new LinkedList<>();
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
                for(int i=0; i<splitLists.size();i++) {
                    List<Integer> list = splitLists.get(i);

                    if (list.isEmpty()) {
                        System.out.println("this is empty");
                    } else {
                        temparr = list.stream().mapToInt(Integer::intValue).toArray();
                        mergesort(temparr);
                        templ = Arrays.stream(temparr).boxed().collect(Collectors.toList());

                        splitLists.set(splitLists.indexOf(list), templ);
                    }
                }
            } catch(IndexOutOfBoundsException ex) {
                finished_merging = true;
                System.out.println(ex.getMessage());
            }
    }
}
