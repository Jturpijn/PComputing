package G2_PCM;

import java.util.ArrayList;
import java.util.List;

import static G2_PCM.MergeSort.mergesort;

public class FJ_MergeThread extends Thread {

    private List<Integer> list = new ArrayList<>();
    private List<Integer> left = new ArrayList<>();
    private List<Integer> right = new ArrayList<>();
    private int pivot;

    public FJ_MergeThread(List<Integer> Head, int popivot) {
        list = Head; pivot = popivot;
    }

    @Override
    public void run() {
        System.out.println("started thread with pivot : " + pivot + " and listsize : " + list.size());
        mergesort(list.stream().mapToInt(Integer::intValue).toArray());

        if(list.size() > 8 && pivot > 0) {
//            for (Integer element : list) {
//                if(element > pivot) {
//                    left.add(element);
//                } else { right.add(element); }
//            }

            for(int i =0; i < list.size(); i++) {
                int element = list.get(i);
                if(element > pivot) {
                    left.add(element);
                } else { right.add(element); }
            }

            // create next threads
            if(left.size() > 8 && right.size() > 8) {
                Thread leftSort = new FJ_MergeThread(left, pivot / 2);

                Thread rightSort = new FJ_MergeThread(right, pivot - (pivot / 2));
                //start both threads
                leftSort.start(); rightSort.start();

                // try to join
                try {
                    leftSort.join();
                } catch(InterruptedException ex) {
                    System.out.println("Leftjoin failed.");
                    System.out.println(ex.getMessage());
                }

                try {
                    rightSort.join();
                } catch(InterruptedException ex) {
                    System.out.println("Rightjoin failed.");
                    System.out.println(ex.getMessage());
                }
            }

            merge(left, right);

        } else {
            if(list.size()>0){
                merge(left, right);
            }
        }
    }

    public void merge(List<Integer> left, List<Integer> right) {
        List<Integer> temp = new ArrayList<>();
        temp.addAll(left);
        temp.addAll(right);
        int[] test = temp.stream().mapToInt(Integer::intValue).toArray();
        mergesort(test);
    }
}