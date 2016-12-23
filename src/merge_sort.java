import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by mgoo on 10/12/16.
 */
public class merge_sort<E extends Comparable> {
    protected List<E> unsorted = new ArrayList<>();

    public merge_sort(Collection<E> data){
        this.unsorted .addAll(data);

        // Clone the passed list
        List<E> origonal = new ArrayList<E>(this.unsorted.size());
        origonal.addAll(this.unsorted);

        // Test Collections.sort
        long start_time = System.currentTimeMillis();
        Collections.sort(origonal);
        long end_time = System.currentTimeMillis();
        this.print_results(origonal);
        long collection_sort_time = end_time - start_time;
        // Print results.
        System.out.println("Collections.sort: " + collection_sort_time);


        int n = unsorted.size();
         start_time = System.currentTimeMillis();
         // Loop throught the sizes of the arrays to merge.
        for (int size = 1; size <= n/2; size*=2){
            int i;
            List<sort> threads = new ArrayList<sort>();
            // Loop through the list and split it into groups of two lists with size() = size.
            for (i = 0; i < (n/size)-1; i+=2){
                try {
                    int end2 = ((i + 2) * size) - 1;
                    // Get of the arry is not properly divisible
                    if (unsorted.size() % (size*2) != 0){
                        //System.out.println("There is a problem: " + size);
                        // Take into account when the array is not evenly divisible.
                        //Thread t  = new sort();
                        //t.start();
                        //while(t.isAlive()){}
                    }
                    // Start Threads to merge the sets of two arrays.
                    //System.out.println(size);
                    threads.add(new sort(i * size, ((i + 1) * size) - 1, (i + 1) * size, end2));
                    threads.get(threads.size() - 1).start();

                } catch (SectionsNotAdjacentException e){
                    e.printStackTrace();
                }
            }
            boolean finished = true;
            // Wait till all the Threads of a peticular size are finished.
            while (finished){
                finished = false;
                for (int count = 0; count < threads.size(); count++){
                    if (threads.get(count).isAlive())finished = true;
                }
            }
        }
        end_time = System.currentTimeMillis();
        long threads_sort_time = (end_time - start_time);
        // Rrport Results.
        System.out.println("Threads: " + threads_sort_time);
        if (collection_sort_time != 0) {
            System.out.println("Collection is " + threads_sort_time / collection_sort_time + " faster");
        }
        this.print_results(unsorted);
    }

    /**
     * Checks if the passes list is sorted
     * @param sorted
     */
    public void print_results(List<E> sorted){
        boolean sorted_correct = true;
        for (int i = 0; i < sorted.size(); i++){
            if (i != 0 && sorted.get(i-1).compareTo(sorted.get(i)) > 0){
                sorted_correct = false;
            }
        }
        if (sorted_correct){
            System.out.println("the array was correctly sorted");
        } else {
            System.out.println("The array was not sorted correctly");
        }
    }

    class sort extends Thread{
        private int start,end,start2,end2;

        public sort(int start, int end, int start2, int end2) throws SectionsNotAdjacentException{
            this.start = start;
            this.end = end;
            this.start2 = start2;
            this.end2 = end2;
            if (end+1 != start2){
                throw new SectionsNotAdjacentException();
            }
            //System.out.println("Start1: " + this.start + " End1: " + this.end + " Start2: " + this.start2 + " End2: " + this.end2);
        }

        public void run(){
            int c1 = this.start;
            int c2 = this.start2;
            List<E> sorted = new ArrayList<E>(this.end2 - this.start);
            while (sorted.size() <= (end2-start)){
                if (c1 == -1 && c2 == -1){
                    break;
                }
                if (c2 != -1 && (c1 == -1 || unsorted.get(c1).compareTo(unsorted.get(c2)) > 0)){
                    sorted.add(unsorted.get(c2));
                    c2++;
                    if (c2 > end2)c2 = -1;
                } else {
                    sorted.add(unsorted.get(c1));
                    c1++;
                    if (c1 > end)c1 = -1;
                }
            }
            for (int i = 0; i < sorted.size(); i++){
                unsorted.set(start+i, sorted.get(i));
                //System.out.println(sorted.get(i));
            }
            for(int i = this.start+1; i <= this.end2; i++){
                if (unsorted.get(i-1).compareTo(unsorted.get(i)) > 0){
                    System.out.println("THIS IS NOT SORTED STUPID!!!" + "   Start1: " + this.start + " End1: " + this.end + " Start2: " + this.start2 + " End2: " + this.end2);
                    for(int j = this.start; j <= this.end2; j++){
                        System.out.println("" + unsorted.get(j));
                    }
                }
            }
        }
    }
}
