package G2_PCM;


import javax.rmi.CORBA.Util;

public class FJ_MergeThread extends Thread {

    private int[] list;
    private int countLeft = 0;
    private int countRight = 0;
    private int pivot;

    public FJ_MergeThread(int[] Head, int popivot) {
        list = Head; pivot = popivot;
    }

    @Override
    public void run() {
        int[] left = new int[list.length];
        int[] right = new int[list.length];
            if (pivot >= 8) {
                for (int i = 0; i < list.length; i++) {
                        if (list[i] >= (pivot / 2)) {
                            right[countRight] = list[i];
                            countRight++;
                        } else {
                            left[countLeft] = list[i];
                            countLeft++;
                        }
                }
            }

            int[] newleft = new int[countLeft];
            System.arraycopy(left, 0, newleft, 0, countLeft);
            int[] newright= new int[countRight];
            System.arraycopy(right, 0, newright, 0, countRight);


            // create next threads
            if(pivot >= 8) {
                Thread leftSort = new FJ_MergeThread(newleft, pivot / 8);
                Thread rightSort = new FJ_MergeThread(newright, (pivot - (pivot / 8)));

                //start both threads
                leftSort.start();
                rightSort.start();

                // try to join
                try {
                    leftSort.join();
                } catch(InterruptedException ex) { }

                try {
                    rightSort.join();
                } catch(InterruptedException ex) { }
            } else { MergeSort.mergesort(list); }
            if(countLeft > 0 || countRight > 0) {
                merge(newleft, newright);
            }
    }

    public void merge(int[] left, int[] right) {
        int[] output = new int[list.length];
        System.arraycopy(output, 0, left, 0, countLeft);
        System.arraycopy(output, 0, right, countLeft, countRight);

        MergeSort.mergesort(output);
        if(output.length == FJ_MergeSort.array.length) {
            FJ_MergeSort.array = output;
        }
    }
}