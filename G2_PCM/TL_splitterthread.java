package G2_PCM;

import java.util.List;

import static G2_PCM.Utils.print;


public class TL_splitterthread implements Runnable {
    private List<Integer> list1;
    private List<Integer> list2;
    private int[] remoteArray1;
    private int[] remoteArray2;

    public TL_splitterthread(List<Integer> list, int[] arr1, int[] arr2) {
        remoteArray1 = arr1; remoteArray2 = arr2;
        for (int i=0;i<list.size();i++) {
            if(list.get(i) <= (list.get(list.size()/2))) { list1.add(list.get(i)); } 
            else { list2.add(list.get(i)); }
        }
    }

    public void run() {
        remoteArray1 = new int[list1.size()];
        for(int l1 = 0; l1 <list1.size(); l1++) {
            remoteArray1[l1] = list1.get(l1);
        }

        remoteArray2 = new int[list2.size()];
        for(int l2 = 0; l2 <list2.size(); l2++) {
            remoteArray2[l2] = list2.get(l2);
        }
        print(remoteArray1);
    }
}