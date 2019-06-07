package G2_PCM;

import java.util.LinkedList;
import java.util.List;

import static G2_PCM.TL_MergeSort.splitLists;
import static G2_PCM.TL_MergeSort.array;
import static G2_PCM.TL_MergeSort.MAX;

public class TL_splitThread implements Runnable {
    List<Integer> tempLow = new LinkedList<>();
    List<Integer> tempHigh = new LinkedList<>();

    public void run() {
        if(splitLists.get(4).isEmpty()) {
            System.out.println("splitting into 2");
            for(int i=0;i<array.length;i++) {
                if (array[i] < (MAX/2)) {
                    tempLow.add(array[i]);
                } else {
                    tempHigh.add(array[i]);
                }
            }
            splitLists.set(0, tempLow);
            splitLists.set(4, tempHigh);

        } else if(splitLists.get(2).isEmpty()) {
            System.out.println("splitting into 4 part 1");
            for(int i=0;i<splitLists.get(0).size();i++) {
                int number = splitLists.get(0).get(i);
                if (number < (MAX/4)) {
                    tempLow.add(number);
                } else {
                    tempHigh.add(number);
                }
            }
            splitLists.set(0, tempLow);
            splitLists.set(2, tempHigh);

        } else if(splitLists.get(1).isEmpty()) {
            System.out.println("splitting into 8 part 1 on number : " + ((MAX/4)+(MAX/2)));
            for(int i=0;i<splitLists.get(0).size();i++) {
                int number = splitLists.get(0).get(i);
                if (number > ((MAX/4)+(MAX/2))) {
                    tempHigh.add(number);
                } else {
                    tempLow.add(number);
                }
            }

            splitLists.set(0, tempLow);
            splitLists.set(1, tempHigh);

        }  else if(splitLists.get(3).isEmpty()) {
            System.out.println("splitting into 8 part 2 on number : " + ((MAX/4)+(MAX/2)));
            for(int i=0;i<splitLists.get(2).size();i++) {
                int number = splitLists.get(2).get(i);
                if (number > ((MAX/4)+(MAX/2))) {
                    tempHigh.add(number);
                } else {
                    tempLow.add(number);
                }
            }

            splitLists.set(2, tempLow);
            splitLists.set(3, tempHigh);
        }

    }
}