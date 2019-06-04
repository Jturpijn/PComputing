package G2_PCM;

import java.util.*;
import java.util.regex.*;

public class mergeThread implements Runnable {
    private int[] anArray;
    
    static HashMap<String, List<Integer>> subarrays = TL_MergeSort.subarrays;

    public void run() {
        Pattern p = Pattern.compile("todo[0-9]");
        Matcher m; 
        Boolean grabbed = false; 

        Iterator it = subarrays.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            m = p.matcher(pair.getKey().toString());
            if(m.matches()) {
                grabbed = true; 
                System.out.println("I'm a thread and I grabbed " + pair.getKey());

                anArray = subarrays.get(pair.getKey()).stream().mapToInt(Integer::intValue).toArray();

                MergeSort.mergesort(anArray);
                pair.setValue(anArray);

                // print function
//                for(Integer i : anArray) {
//                    System.out.print(i + " ");
//                }
            }
            it.remove(); // avoids a ConcurrentModificationException
        }

        if(grabbed = false) {
            System.out.println("There are no more arrays to sort.");
        }
    }
}