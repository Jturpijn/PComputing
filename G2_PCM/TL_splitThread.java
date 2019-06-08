package G2_PCM;

import java.util.LinkedList;
import java.util.List;

import static G2_PCM.TL_MergeSort.*;
import static G2_PCM.TL_MergeSort.array;
import static G2_PCM.Utils.split;

public class TL_splitThread implements Runnable {
    List<Integer> tempLow = new LinkedList<>();
    List<Integer> tempHigh = new LinkedList<>();
    int threadcount;

    public TL_splitThread(int count) {
        threadcount = count;
        new Thread(this).start();
    }

    public void run() {
//        System.out.println("splitthread : " + threadcount);
        if(splitLists.get(4).isEmpty()) {
//            System.out.println("splitting into 2");
            for(int i=0;i<array.length;i++) {
                if (array[i] < (MAX/2)) {
                    tempLow.add(array[i]);
                } else {
                    tempHigh.add(array[i]);
                }
            }
            splitLists.set(0, tempLow);
            splitLists.set(4, tempHigh);
            first_layer = true;

        }
        //second layer
        else if(first_layer && !second_layer && splitLists.get(2).isEmpty()) {
            splitLists.get(2).add(1);
//            System.out.println("splitting into 4 part 1");
            split(tempLow, tempHigh, 0, (MAX/4));
            splitLists.set(0, tempLow);
            splitLists.set(2, tempHigh);
        } else if(first_layer && !second_layer && splitLists.get(6).isEmpty()) {
//            System.out.println("Splitting into 4 part 2");
            split(tempLow, tempHigh,4, (MAX-(MAX/4)));
            splitLists.set(4, tempLow);
            splitLists.set(6, tempHigh);
            second_layer = true;
        }
        //third layer
        else if(first_layer && second_layer && splitLists.get(1).isEmpty()) {
            splitLists.get(1).add(1);
//            System.out.println("splitting into 8 part 1 on number : " + (MAX/8));
            split(tempLow, tempHigh, 0, (MAX/8));
            splitLists.set(0, tempLow);
            splitLists.set(1, tempHigh);
        } else if(first_layer && second_layer && splitLists.get(3).isEmpty()) {
            splitLists.get(3).add(1);
//            System.out.println("splitting into 8 part 2 on number : " + ((MAX/2)-(MAX/8)));
            split(tempLow, tempHigh, 2, ((MAX/2)-(MAX/8)));
            splitLists.set(2, tempLow);
            splitLists.set(3, tempHigh);
        } else if(first_layer && second_layer && splitLists.get(5).isEmpty()) {
            splitLists.get(5).add(1);
//            System.out.println("Splitting into 8 part 1 on number : " + ((MAX/2)+(MAX/8)));
            split(tempLow, tempHigh,4, ((MAX/2)+(MAX/8)));
            splitLists.set(4, tempLow);
            splitLists.set(5, tempHigh);
        } else if(first_layer && second_layer && splitLists.get(7).isEmpty()) {
//            System.out.println("Splitting into 8 part 2 on number : " + ((MAX)-(MAX/8)));
            split(tempLow, tempHigh,6, ((MAX)-(MAX/8)));
            splitLists.set(6, tempLow);
            splitLists.set(7, tempHigh);
            third_layer = true;
        }
    }
}