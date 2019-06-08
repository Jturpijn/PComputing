package G2_PCM;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static G2_PCM.MergeSort.mergesort;
import static G2_PCM.TL_MergeSort.splitLists;

public class TL_mergeThread implements Runnable {
    int[] temparr;
    List<Integer> templ = new LinkedList<>();
    int threadcount;

    public TL_mergeThread(int count) {
        threadcount = count;
    }

    public void run() {
        System.out.println("mergethread : " + threadcount);
        for (List<Integer> list : splitLists) {
            if (list.isEmpty()) {
                System.out.println("this is empty");
            } else {
                temparr = list.stream().mapToInt(Integer::intValue).toArray();
                mergesort(temparr);
                templ = Arrays.stream(temparr).boxed().collect(Collectors.toList());

                splitLists.set(splitLists.indexOf(list), templ);
            }
        }
    }
}
